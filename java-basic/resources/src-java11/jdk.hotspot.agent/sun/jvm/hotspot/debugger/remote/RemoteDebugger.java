/*
 * Copyright (c) 2002, 2009, Oracle and/or its affiliates. All rights reserved.
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
 */

package sun.jvm.hotspot.debugger.remote;

import java.rmi.*;

import sun.jvm.hotspot.debugger.*;

/** <P> This interface describes the methods which are used in a
    remote debugging scenario. It is only necessary because RMI
    requires that all such methods throw RemoteException, which is a
    checked (i.e., not a Runtime) exception. Since we already have a
    suitable runtime exception (DebuggerException) present in the
    signatures for all of the debugger-related methods, we would like
    to repurpose this to wrap RemoteExceptions. This is done by
    wrapping the actual remote debugger object
    </P>

    <P> NOTE that this interface currently assumes that the debugger
    on the remote machine has already been attached to the target
    process or has opened the desired core file. This implies that the
    machine hosting the user interface can not effect
    attaching/detaching. Currently this restriction has been enforced
    to make the user interface less confusing, but there will also be
    security concerns with allowing clients to attach to arbitrary
    remote processes. </P>
*/

public interface RemoteDebugger extends Remote {
  public String    getOS() throws RemoteException;
  public String    getCPU() throws RemoteException;
  public MachineDescription getMachineDescription() throws RemoteException;
  public long      lookupInProcess(String objectName, String symbol) throws RemoteException;
  public ReadResult readBytesFromProcess(long address, long numBytes) throws RemoteException;
  public boolean   hasConsole() throws RemoteException;
  public String    getConsolePrompt() throws RemoteException;
  public String    consoleExecuteCommand(String cmd) throws RemoteException;
  public long      getJBooleanSize() throws RemoteException;
  public long      getJByteSize() throws RemoteException;
  public long      getJCharSize() throws RemoteException;
  public long      getJDoubleSize() throws RemoteException;
  public long      getJFloatSize() throws RemoteException;
  public long      getJIntSize() throws RemoteException;
  public long      getJLongSize() throws RemoteException;
  public long      getJShortSize() throws RemoteException;
  public long      getHeapOopSize() throws RemoteException;
  public long      getNarrowOopBase() throws RemoteException;
  public int       getNarrowOopShift() throws RemoteException;
  public long      getKlassPtrSize() throws RemoteException;
  public long      getNarrowKlassBase() throws RemoteException;
  public int       getNarrowKlassShift() throws RemoteException;

  public boolean   areThreadsEqual(long addrOrId1, boolean isAddress1,
                                   long addrOrId2, boolean isAddress2) throws RemoteException;
  public int       getThreadHashCode(long addrOrId, boolean isAddress) throws RemoteException;
  public long[]    getThreadIntegerRegisterSet(long addrOrId, boolean isAddress) throws RemoteException;
}
