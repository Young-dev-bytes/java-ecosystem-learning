/*
 * Copyright (c) 2000, 2022, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jndi.dns;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.StringTokenizer;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.jndi.toolkit.url.UrlUtil;


/**
 * A DnsUrl represents a DNS pseudo-URL of the form
 * <pre>
 *   dns://[host][:port][/[domain]]
 * or
 *   dns:[/][domain]
 * </pre>
 * The host names a DNS server.  If the host is not provided, it
 * indicates that the underlying platform's DNS server(s) should be
 * used if possible, or that "localhost" should be used otherwise.  If
 * the port is not provided, the DNS default port 53 will be used.
 * The domain indicates the domain name of the context, and is not
 * necessarily related to the domain of the server; if it is not
 * provided, the root domain "." is used.  Special characters in
 * the domain name must be %-escaped as described in RFC 2396.
 *
 * @author Scott Seligman
 */


public class DnsUrl extends Uri {

    private static final String PARSE_MODE_PROP = "com.sun.jndi.dnsURLParsing";
    private static final ParseMode DEFAULT_PARSE_MODE = ParseMode.COMPAT;

    public static final ParseMode PARSE_MODE;
    static {
        PrivilegedAction<String> action = () ->
                System.getProperty(PARSE_MODE_PROP, DEFAULT_PARSE_MODE.toString());
        ParseMode parseMode = DEFAULT_PARSE_MODE;
        try {
            @SuppressWarnings("removal")
            String mode = AccessController.doPrivileged(action);
            parseMode = ParseMode.valueOf(mode.toUpperCase(Locale.ROOT));
        } catch (Throwable t) {
            parseMode = DEFAULT_PARSE_MODE;
        } finally {
            PARSE_MODE = parseMode;
        }
    }
    private String domain;      // domain name of the context


    /**
     * Given a space-separated list of DNS URLs, returns an array of DnsUrl
     * objects.
     */
    public static DnsUrl[] fromList(String urlList)
            throws MalformedURLException {

        DnsUrl[] urls = new DnsUrl[(urlList.length() + 1) / 2];
        int i = 0;              // next available index in urls
        StringTokenizer st = new StringTokenizer(urlList, " ");

        while (st.hasMoreTokens()) {
            try {
                urls[i++] = new DnsUrl(validateURI(st.nextToken()));
            } catch (URISyntaxException e) {
                MalformedURLException mue = new MalformedURLException(e.getMessage());
                mue.initCause(e);
                throw mue;
            }
        }
        DnsUrl[] trimmed = new DnsUrl[i];
        System.arraycopy(urls, 0, trimmed, 0, i);
        return trimmed;
    }

    @Override
    protected ParseMode parseMode() {
        return PARSE_MODE;
    }

    @Override
    protected final boolean isSchemeOnly(String uri) {
        return isDnsSchemeOnly(uri);
    }

    @Override
    protected boolean checkSchemeOnly(String uri, String scheme) {
        return uri.equals(scheme + ":") || uri.equals(scheme + "://");
    }

    @Override
    protected final MalformedURLException newInvalidURISchemeException(String uri) {
        return new MalformedURLException(
                uri + " is not a valid DNS pseudo-URL");
    }

    private static boolean isDnsSchemeOnly(String uri) {
        return "dns:".equals(uri) || "dns://".equals(uri);
    }

    private static String validateURI(String uri) throws URISyntaxException {
        // no validation in legacy parsing mode
        if (PARSE_MODE == ParseMode.LEGACY) return uri;
        // special case of scheme-only URIs
        if (isDnsSchemeOnly(uri)) return uri;
        // use java.net.URI to validate the uri syntax
        return new URI(uri).toString();
    }

    public DnsUrl(String url) throws MalformedURLException {
        super(url);

        if (!scheme.equals("dns")) {
            throw newInvalidURISchemeException(url);
        }

        domain = path.startsWith("/")
            ? path.substring(1)
            : path;
        domain = domain.equals("")
            ? "."
            : UrlUtil.decode(domain);

        // Debug
        // System.out.println("host=" + host + " port=" + port +
        //                    " domain=" + domain);
    }

    /**
     * Returns the domain of this URL, or "." if none is provided.
     * Never null.
     */
    public String getDomain() {
        return domain;
    }


/*
    // Debug
    public static void main(String args[]) throws MalformedURLException {
        DnsUrl[] urls = fromList(args[0]);
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toString());
        }
    }
*/
}
