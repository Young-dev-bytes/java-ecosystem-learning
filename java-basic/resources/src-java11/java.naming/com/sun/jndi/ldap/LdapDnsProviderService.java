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
 */

package com.sun.jndi.ldap;

import java.util.Hashtable;
import javax.naming.NamingException;

public interface LdapDnsProviderService {

    LdapCtx getContextFromEndpoints(String url, Hashtable<?,?> env)
        throws NamingException;

}
