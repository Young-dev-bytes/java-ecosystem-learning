/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvmstat.perfdata.monitor.protocol.rmi;

import sun.jvmstat.monitor.*;
import sun.jvmstat.monitor.remote.*;
import sun.jvmstat.perfdata.monitor.*;
import java.io.*;
import java.rmi.RemoteException;
import java.nio.ByteBuffer;

/**
 * The concrete PerfDataBuffer implementation for the <em>rmi:</em>
 * protocol for the HotSpot PerfData monitoring implementation.
 * <p>
 * This class is responsible for acquiring the instrumentation buffer
 * data for a remote target HotSpot Java Virtual Machine.
 *
 * @author Brian Doherty
 * @since 1.5
 */
public class PerfDataBuffer extends AbstractPerfDataBuffer {

    private RemoteVm rvm;

    /**
     * Create a PerfDataBuffer instance for accessing the specified
     * instrumentation buffer.
     *
     * @param rvm the proxy to the remote MonitredVm object
     * @param lvmid the local Java Virtual Machine Identifier of the
     *              remote target.
     *
     * @throws MonitorException
     */
    public PerfDataBuffer(RemoteVm rvm, int lvmid) throws MonitorException {

        this.rvm = rvm;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(rvm.getCapacity());
            sample(buffer);
            createPerfDataBuffer(buffer, lvmid);

        } catch (RemoteException e) {
            throw new MonitorException("Could not read data for remote JVM "
                                       + lvmid, e);
        }
    }

    /**
     * Get a copy of the remote instrumentation buffer.
     *<p>
     * The data in the remote instrumentation buffer is copied into
     * the local byte buffer.
     *
     * @param buffer the buffer to receive the copy of the remote
     *               instrumentation buffer.
     * @throws RemoteException Thrown on any communications errors with
     *                         the remote system.
     */
    public void sample(ByteBuffer buffer) throws RemoteException {
        assert buffer != null;
        assert rvm != null;
        synchronized(buffer) {
            buffer.clear();
            buffer.put(rvm.getBytes());
        }
    }
}
