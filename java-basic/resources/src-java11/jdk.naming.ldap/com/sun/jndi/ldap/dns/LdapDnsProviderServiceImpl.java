/*
 * Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jndi.ldap.dns;

import com.sun.jndi.ldap.LdapCtx;
import com.sun.jndi.ldap.LdapURL;
import com.sun.jndi.ldap.spi.LdapDnsProvider;
import com.sun.jndi.ldap.spi.LdapDnsProviderResult;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import javax.naming.NamingException;
import sun.security.util.SecurityConstants;

/**
 * The {@code LdapDnsProviderServiceImpl} is responsible for creating and providing
 * access to the registered {@code LdapDnsProvider}s. The {@link ServiceLoader}
 * is used to find and register any implementations of {@link LdapDnsProvider}.
 */
final class LdapDnsProviderServiceImpl
    implements com.sun.jndi.ldap.LdapDnsProviderService
{

    private static final LdapDnsProviderServiceImpl INSTANCE =
            new LdapDnsProviderServiceImpl();
    private static final Object LOCK = new int[0];
    private final ServiceLoader<LdapDnsProvider> providers;

    static {
        com.sun.jndi.ldap.LdapCtxFactory.register(getInstance());
    }

    /**
     * Creates a new instance of LdapDnsProviderServiceImpl
     */
    private LdapDnsProviderServiceImpl() {
        SecurityManager sm = System.getSecurityManager();
        if (sm == null) {
            providers = ServiceLoader.load(
                    LdapDnsProvider.class,
                    ClassLoader.getSystemClassLoader());
        } else {
            final PrivilegedAction<ServiceLoader<LdapDnsProvider>> pa =
                    () -> ServiceLoader.load(
                            LdapDnsProvider.class,
                            ClassLoader.getSystemClassLoader());

            providers = AccessController.doPrivileged(
                pa,
                null,
                new RuntimePermission("ldapDnsProvider"),
                SecurityConstants.GET_CLASSLOADER_PERMISSION);
        }
    }

    /**
     * Retrieve the singleton static instance of LdapDnsProviderServiceImpl.
     */
    static LdapDnsProviderServiceImpl getInstance() {
        return INSTANCE;
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

    public LdapCtx getContextFromEndpoints(String url, Hashtable<?,?> env)
            throws NamingException
    {
        LdapDnsProviderResult r =
            LdapDnsProviderServiceImpl.getInstance().lookupEndpoints(url, env);
        LdapCtx ctx;
        NamingException lastException = null;

        /*
         * Prior to this change we had been assuming that the url.getDN()
         * should be converted to a domain name via
         * ServiceLocator.mapDnToDomainName(url.getDN())
         *
         * However this is incorrect as we can't assume that the supplied
         * url.getDN() is the same as the dns domain for the directory
         * server.
         *
         * This means that we depend on the dnsProvider to return both
         * the list of urls of individual hosts from which we attempt to
         * create an LdapCtx from *AND* the domain name that they serve
         *
         * In order to do this the dnsProvider must return an
         * {@link LdapDnsProviderResult}.
         *
         */
        for (String u : r.getEndpoints()) {
            try {
                ctx = getLdapCtxFromUrl(
                        r.getDomainName(), url, new LdapURL(u), env);
                return ctx;
            } catch (NamingException e) {
                // try the next element
                lastException = e;
            }
        }

        if (lastException != null) {
            throw lastException;
        }

        // lookupEndpoints returned an LdapDnsProviderResult with an empty
        // list of endpoints
        throw new NamingException("Could not resolve a valid ldap host");
    }

    /**
     * Retrieve result from the first provider that successfully resolves
     * the endpoints. If no results are found when calling installed
     * subclasses of {@code LdapDnsProvider} then this method will fall back
     * to the {@code DefaultLdapDnsProvider}.
     *
     * @throws NamingException if the {@code url} in not valid or an error
     *                         occurred while performing the lookup.
     */
    LdapDnsProviderResult lookupEndpoints(String url, Hashtable<?,?> env)
        throws NamingException
    {
        Hashtable<?, ?> envCopy = new Hashtable<>(env);
        LdapDnsProviderResult result = null;

        synchronized (LOCK) {
            Iterator<LdapDnsProvider> iterator = providers.iterator();
            while (result == null && iterator.hasNext()) {
                result = iterator.next().lookupEndpoints(url, envCopy)
                        .filter(r -> r.getEndpoints().size() > 0)
                        .orElse(null);
            }
        }

        if (result == null) {
            return new DefaultLdapDnsProvider().lookupEndpoints(url, env)
                .orElse(new LdapDnsProviderResult("", List.of()));
        }
        return result;
    }

}
