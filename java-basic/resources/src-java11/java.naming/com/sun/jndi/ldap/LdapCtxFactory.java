/*
 * Copyright (c) 1999, 2020, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jndi.ldap;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.spi.ObjectFactory;
import javax.naming.spi.InitialContextFactory;
import javax.naming.ldap.Control;

import com.sun.jndi.url.ldap.ldapURLContextFactory;

final public class LdapCtxFactory implements ObjectFactory, InitialContextFactory {
    /**
     * The type of each address in an LDAP reference.
     */
    public final static String ADDRESS_TYPE = "URL";

    // ----------------- ObjectFactory interface --------------------

    public Object getObjectInstance(Object ref, Name name, Context nameCtx,
        Hashtable<?,?> env) throws Exception {

        if (!isLdapRef(ref)) {
            return null;
        }
        ObjectFactory factory = new ldapURLContextFactory();
        String[] urls = getURLs((Reference)ref);
        return factory.getObjectInstance(urls, name, nameCtx, env);
    }

    // ----------------- InitialContext interface  --------------------

    public Context getInitialContext(Hashtable<?,?> envprops)
        throws NamingException {

        try {
            String providerUrl = (envprops != null) ?
                (String)envprops.get(Context.PROVIDER_URL) : null;

            // If URL not in environment, use defaults
            if (providerUrl == null) {
                return new LdapCtx("", LdapCtx.DEFAULT_HOST,
                    LdapCtx.DEFAULT_PORT, envprops, false);
            }

            // Extract URL(s)
            String[] urls = LdapURL.fromList(providerUrl);

            if (urls.length == 0) {
                throw new ConfigurationException(Context.PROVIDER_URL +
                    " property does not contain a URL");
            }

            // Generate an LDAP context
            return getLdapCtxInstance(urls, envprops);

        } catch (LdapReferralException e) {

            if (envprops != null &&
                "throw".equals(envprops.get(Context.REFERRAL))) {
                throw e;
            }

            Control[] bindCtls = (envprops != null)?
                (Control[])envprops.get(LdapCtx.BIND_CONTROLS) : null;

            return (LdapCtx)e.getReferralContext(envprops, bindCtls);
        }
    }

    /**
     * Returns true if argument is an LDAP reference.
     */
    private static boolean isLdapRef(Object obj) {

        if (!(obj instanceof Reference)) {
            return false;
        }
        String thisClassName = LdapCtxFactory.class.getName();
        Reference ref = (Reference)obj;

        return thisClassName.equals(ref.getFactoryClassName());
    }

    /**
     * Returns the URLs contained within an LDAP reference.
     */
    private static String[] getURLs(Reference ref) throws NamingException {

        int size = 0;   // number of URLs
        String[] urls = new String[ref.size()];

        Enumeration<RefAddr> addrs = ref.getAll();
        while (addrs.hasMoreElements()) {
            RefAddr addr = addrs.nextElement();

            if ((addr instanceof StringRefAddr) &&
                addr.getType().equals(ADDRESS_TYPE)) {

                urls[size++] = (String)addr.getContent();
            }
        }
        if (size == 0) {
            throw (new ConfigurationException(
                    "Reference contains no valid addresses"));
        }

        // Trim URL array down to size.
        if (size == ref.size()) {
            return urls;
        }
        String[] urls2 = new String[size];
        System.arraycopy(urls, 0, urls2, 0, size);
        return urls2;
    }

    // ------------ Utilities used by other classes ----------------

    public static DirContext getLdapCtxInstance(Object urlInfo, Hashtable<?,?> env)
            throws NamingException {

        if (urlInfo instanceof String) {
            return getUsingURL((String)urlInfo, env);
        } else if (urlInfo instanceof String[]) {
            return getUsingURLs((String[])urlInfo, env);
        } else {
            throw new IllegalArgumentException(
                "argument must be an LDAP URL String or array of them");
        }
    }

    private static volatile LdapDnsProviderService dnsProviderService;
    private static final Object providerLock = new Object();

    static {
        try {
            Class<?> ldpsClass = Class.forName(
                    "com.sun.jndi.ldap.dns.LdapDnsProviderServiceImpl");
        } catch (ClassNotFoundException e) {}
    }

    public static void register(LdapDnsProviderService dnsProviderSvc) {
        dnsProviderService = dnsProviderSvc;
    }

    private static DirContext getContextFromEndpoints(String url,
                                                      Hashtable<?, ?> env)
              throws ClassNotFoundException, NamingException
    {
        LdapDnsProviderService provider = dnsProviderService;
        if (provider == null)
            throw new NamingException("Could not resolve a valid ldap host");
        return dnsProviderService.getContextFromEndpoints(url, env);
    }

    private static DirContext getUsingURL(String url, Hashtable<?,?> env)
            throws NamingException
    {
        try {
            return AccessController.doPrivilegedWithCombiner((PrivilegedExceptionAction<DirContext>) () -> {
                try {
                    return getContextFromEndpoints(url, env);
                } catch (Exception e) {
                    // getContextFromEndpoints(url, env) may throw a NamingException,
                    // which there is no need to wrap.
                    if (e instanceof NamingException) {
                        throw e;
                    }
                    NamingException ex = new NamingException();
                    ex.setRootCause(e);
                    throw ex;
                }
            });
        } catch (PrivilegedActionException pae) {
            throw (NamingException) pae.getException();
        }
    }

    private static LdapCtx getLdapCtxFromUrl(String domain,
                                             String url,
                                             LdapURL u,
                                             Hashtable<?,?> env)
            throws NamingException
    {
        String dn = u.getDN();
        String host = u.getHost();
        int port = u.getPort();
        LdapCtx ctx = new LdapCtx(dn, host, port, env, u.useSsl());
        ctx.setDomainName(domain);
        // Record the URL that created the context
        ctx.setProviderUrl(url);
        return ctx;
    }

    /*
     * Try each URL until one of them succeeds.
     * If all URLs fail, throw one of the exceptions arbitrarily.
     * Not pretty, but potentially more informative than returning null.
     */
    private static DirContext getUsingURLs(String[] urls, Hashtable<?,?> env)
            throws NamingException
    {
        NamingException ex = null;
        for (String u : urls) {
            try {
                return getUsingURL(u, env);
            } catch (NamingException e) {
                ex = e;
            }
        }
        throw ex;
    }

    /**
     * Used by Obj and obj/RemoteToAttrs too so must be public
     */
    public static Attribute createTypeNameAttr(Class<?> cl) {
        Vector<String> v = new Vector<>(10);
        String[] types = getTypeNames(cl, v);
        if (types.length > 0) {
            BasicAttribute tAttr =
                new BasicAttribute(Obj.JAVA_ATTRIBUTES[Obj.TYPENAME]);
            for (int i = 0; i < types.length; i++) {
                tAttr.add(types[i]);
            }
            return tAttr;
        }
        return null;
    }

    private static String[] getTypeNames(Class<?> currentClass, Vector<String> v) {

        getClassesAux(currentClass, v);
        Class<?>[] members = currentClass.getInterfaces();
        for (int i = 0; i < members.length; i++) {
            getClassesAux(members[i], v);
        }
        String[] ret = new String[v.size()];
        int i = 0;

        for (String name : v) {
            ret[i++] = name;
        }
        return ret;
    }

    private static void getClassesAux(Class<?> currentClass, Vector<String> v) {
        if (!v.contains(currentClass.getName())) {
            v.addElement(currentClass.getName());
        }
        currentClass = currentClass.getSuperclass();

        while (currentClass != null) {
            getTypeNames(currentClass, v);
            currentClass = currentClass.getSuperclass();
        }
    }
}
