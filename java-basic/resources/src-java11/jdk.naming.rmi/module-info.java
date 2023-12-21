/*
 * Copyright (c) 2014, 2015, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Provides the implementation of the RMI Java Naming provider.
 *
 * @provides javax.naming.spi.InitialContextFactory
 * @moduleGraph
 * @since 9
 */
module jdk.naming.rmi {
    requires java.naming;
    requires java.rmi;

    // temporary export until NamingManager.getURLContext uses services
    exports com.sun.jndi.url.rmi to java.naming;
    exports com.sun.jndi.rmi.registry to java.rmi;

    provides javax.naming.spi.InitialContextFactory with
        com.sun.jndi.rmi.registry.RegistryContextFactory;

}
