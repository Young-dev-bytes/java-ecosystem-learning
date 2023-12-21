/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
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

package java.io;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

// Maps Class instances to values of type T. Under memory pressure, the
// mapping is released (under soft references GC policy) and would be
// recomputed the next time it is queried. The mapping is bound to the
// lifetime of the class: when the class is unloaded, the mapping is
// removed too.
abstract class ClassCache<T> {

    private static class CacheRef<T> extends SoftReference<T> {
        private final Class<?> type;

        CacheRef(T referent, ReferenceQueue<T> queue, Class<?> type) {
            super(referent, queue);
            this.type = type;
        }

        Class<?> getType() {
            return type;
        }
    }

    private final ReferenceQueue<T> queue;
    private final ClassValue<SoftReference<T>> map;

    protected abstract T computeValue(Class<?> cl);

    protected ClassCache() {
        queue = new ReferenceQueue<>();
        map = new ClassValue<>() {
            @Override
            protected SoftReference<T> computeValue(Class<?> type) {
                return new CacheRef<>(ClassCache.this.computeValue(type), queue, type);
            }
        };
    }

    T get(Class<?> cl) {
        processQueue();
        T val;
        do {
            SoftReference<T> ref = map.get(cl);
            val = ref.get();
            if (val == null) {
                map.remove(cl);
            }
        } while (val == null);
        return val;
    }

    private void processQueue() {
        Reference<? extends T> ref;
        while((ref = queue.poll()) != null) {
            CacheRef<? extends T> cacheRef = (CacheRef<? extends T>)ref;
            map.remove(cacheRef.getType());
        }
    }
}
