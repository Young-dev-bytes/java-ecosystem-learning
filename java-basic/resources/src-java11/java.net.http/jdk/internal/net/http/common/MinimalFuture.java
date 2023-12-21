/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.net.http.common;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.requireNonNull;

/*
 * A CompletableFuture which does not allow any obtrusion logic.
 * All methods of CompletionStage return instances of this class.
 */
public final class MinimalFuture<T> extends CompletableFuture<T> {

    @FunctionalInterface
    public interface ExceptionalSupplier<U> {
        U get() throws Throwable;
    }

    private final static AtomicLong TOKENS = new AtomicLong();
    private final long id;

    public static <U> MinimalFuture<U> completedFuture(U value) {
        MinimalFuture<U> f = new MinimalFuture<>();
        f.complete(value);
        return f;
    }

    public static <U> CompletableFuture<U> failedFuture(Throwable ex) {
        requireNonNull(ex);
        MinimalFuture<U> f = new MinimalFuture<>();
        f.completeExceptionally(ex);
        return f;
    }

    public static <U> CompletableFuture<U> supply(ExceptionalSupplier<U> supplier) {
        CompletableFuture<U> cf = new MinimalFuture<>();
        try {
            U value = supplier.get();
            cf.complete(value);
        } catch (Throwable t) {
            cf.completeExceptionally(t);
        }
        return cf;
    }

    public MinimalFuture() {
        super();
        this.id = TOKENS.incrementAndGet();
    }

    @Override
    public <U> MinimalFuture<U> newIncompleteFuture() {
        return new MinimalFuture<>();
    }

    @Override
    public void obtrudeValue(T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void obtrudeException(Throwable ex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return super.toString() + " (id=" + id +")";
    }

    public static <U> MinimalFuture<U> of(CompletionStage<U> stage) {
        MinimalFuture<U> cf = new MinimalFuture<>();
        stage.whenComplete((r,t) -> complete(cf, r, t));
        return cf;
    }

    private static <U> void complete(CompletableFuture<U> cf, U result, Throwable t) {
        if (t == null) {
            cf.complete(result);
        } else {
            cf.completeExceptionally(t);
        }
    }
}
