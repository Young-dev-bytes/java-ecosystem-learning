/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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

package sun.tools.common;

import java.lang.reflect.Method;

/**
 * A helper class to retrieve the main class name for a running
 * Java process.
 */

public interface ProcessHelper {

    /**
     * Returns an instance of the ProcessHelper class.
     *
     * @return ProcessHelper object or null if not supported on this platform.
     */
    public static ProcessHelper platformProcessHelper() {
        try {
            Class<?> c = Class.forName("sun.tools.ProcessHelper");
            @SuppressWarnings("unchecked")
            Method m = c.getMethod("getInstance");
            return (ProcessHelper) m.invoke(null);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (ReflectiveOperationException e) {
            throw new InternalError(e);
        }
    }


    /**
     * Returns the main class name for the given Java process
     *
     * @param pid - process ID (pid)
     * @return main class name or null if the main class could not be retrieved
     */

    String getMainClass(String pid);
}
