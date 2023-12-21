/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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
 * @uses com.sun.jndi.ldap.spi.LdapDnsProvider;
 *
 * @moduleGraph
 * @since 11
 */
module jdk.naming.ldap {
    requires transitive java.naming;

    exports com.sun.jndi.ldap.spi;

    opens com.sun.jndi.ldap.dns to java.naming;

    uses com.sun.jndi.ldap.spi.LdapDnsProvider;
}

