/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
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
package java.net;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import sun.net.ext.ExtendedSocketOptions;
import static sun.net.ext.ExtendedSocketOptions.SOCK_DGRAM;

/*
 * On Unix systems we simply delegate to native methods.
 *
 * @author Chris Hegarty
 */

class PlainDatagramSocketImpl extends AbstractPlainDatagramSocketImpl
{
    static {
        init();
    }

    static final ExtendedSocketOptions extendedOptions =
            ExtendedSocketOptions.getInstance();

    protected <T> void setOption(SocketOption<T> name, T value) throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket closed");
        }
        if (supportedOptions().contains(name)) {
            if (extendedOptions.isOptionSupported(name)) {
                extendedOptions.setOption(fd, name, value);
            } else {
                super.setOption(name, value);
            }
        } else {
            throw new UnsupportedOperationException("unsupported option");
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T getOption(SocketOption<T> name) throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket closed");
        }
        if (supportedOptions().contains(name)) {
            if (extendedOptions.isOptionSupported(name)) {
                return (T) extendedOptions.getOption(fd, name);
            } else {
                return super.getOption(name);
            }
        } else {
            throw new UnsupportedOperationException("unsupported option");
        }
    }

    protected Set<SocketOption<?>> supportedOptions() {
        HashSet<SocketOption<?>> options = new HashSet<>(super.supportedOptions());
        options.addAll(ExtendedSocketOptions.options(SOCK_DGRAM));
        return options;
    }

    protected void socketSetOption(int opt, Object val) throws SocketException {
        if (opt == SocketOptions.SO_REUSEPORT &&
            !supportedOptions().contains(StandardSocketOptions.SO_REUSEPORT)) {
            throw new UnsupportedOperationException("unsupported option");
        }
        try {
            socketSetOption0(opt, val);
        } catch (SocketException se) {
            if (!connected)
                throw se;
        }
    }

    protected synchronized native void bind0(int lport, InetAddress laddr)
        throws SocketException;

    protected native void send0(DatagramPacket p) throws IOException;

    protected synchronized native int peek(InetAddress i) throws IOException;

    protected synchronized native int peekData(DatagramPacket p) throws IOException;

    protected synchronized native void receive0(DatagramPacket p)
        throws IOException;

    protected native void setTimeToLive(int ttl) throws IOException;

    protected native int getTimeToLive() throws IOException;

    @Deprecated
    protected native void setTTL(byte ttl) throws IOException;

    @Deprecated
    protected native byte getTTL() throws IOException;

    protected native void join(InetAddress inetaddr, NetworkInterface netIf)
        throws IOException;

    protected native void leave(InetAddress inetaddr, NetworkInterface netIf)
        throws IOException;

    protected native void datagramSocketCreate() throws SocketException;

    protected native void datagramSocketClose();

    protected native void socketSetOption0(int opt, Object val)
        throws SocketException;

    protected native Object socketGetOption(int opt) throws SocketException;

    protected native void connect0(InetAddress address, int port) throws SocketException;

    protected native void disconnect0(int family);

    native int dataAvailable();

    /**
     * Perform class load-time initializations.
     */
    private static native void init();
}
