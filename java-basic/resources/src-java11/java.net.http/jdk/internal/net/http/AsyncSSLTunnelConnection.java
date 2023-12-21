/*
 * Copyright (c) 2015, 2020, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.internal.net.http;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CompletableFuture;
import java.net.http.HttpHeaders;
import java.util.function.Function;
import jdk.internal.net.http.common.MinimalFuture;
import jdk.internal.net.http.common.SSLTube;
import jdk.internal.net.http.common.Utils;

/**
 * An SSL tunnel built on a Plain (CONNECT) TCP tunnel.
 */
class AsyncSSLTunnelConnection extends AbstractAsyncSSLConnection {

    final PlainTunnelingConnection plainConnection;
    final PlainHttpPublisher writePublisher;
    volatile SSLTube flow;

    AsyncSSLTunnelConnection(InetSocketAddress addr,
                             HttpClientImpl client,
                             String[] alpn,
                             InetSocketAddress proxy,
                             HttpHeaders proxyHeaders)
    {
        super(addr, client, Utils.getServerName(addr), addr.getPort(), alpn);
        this.plainConnection = new PlainTunnelingConnection(addr, proxy, client, proxyHeaders);
        this.writePublisher = new PlainHttpPublisher();
    }

    @Override
    public CompletableFuture<Void> connectAsync(Exchange<?> exchange) {
        if (debug.on()) debug.log("Connecting plain tunnel connection");
        // This will connect the PlainHttpConnection flow, so that
        // its HttpSubscriber and HttpPublisher are subscribed to the
        // SocketTube
        return plainConnection
                .connectAsync(exchange)
                .thenApply( unused -> {
                    if (debug.on()) debug.log("creating SSLTube");
                    // create the SSLTube wrapping the SocketTube, with the given engine
                    flow = new SSLTube(engine,
                                       client().theExecutor(),
                                       client().getSSLBufferSupplier()::recycle,
                                       plainConnection.getConnectionFlow());
                    return null;} );
    }

    @Override
    public CompletableFuture<Void> finishConnect() {
        // The actual ALPN value, which may be the empty string, is not
        // interesting at this point, only that the handshake has completed.
        return getALPN()
                .handle((String unused, Throwable ex) -> {
                    if (ex == null) {
                        return plainConnection.finishConnect();
                    } else {
                        plainConnection.close();
                        return MinimalFuture.<Void>failedFuture(ex);
                    } })
                .thenCompose(Function.identity());
    }

    @Override
    boolean isTunnel() { return true; }

    @Override
    boolean connected() {
        return plainConnection.connected(); // && sslDelegate.connected();
    }

    @Override
    HttpPublisher publisher() { return writePublisher; }

    @Override
    public String toString() {
        return "AsyncSSLTunnelConnection: " + super.toString();
    }

    @Override
    ConnectionPool.CacheKey cacheKey() {
        return ConnectionPool.cacheKey(true, address, plainConnection.proxyAddr);
    }

    @Override
    public void close() {
        plainConnection.close();
    }

    @Override
    SocketChannel channel() {
        return plainConnection.channel();
    }

    @Override
    boolean isProxied() {
        return true;
    }

    @Override
    InetSocketAddress proxy() {
        return plainConnection.proxyAddr;
    }

    @Override
    SSLTube getConnectionFlow() {
       return flow;
   }
}
