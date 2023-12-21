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

package jdk.tools.jlink.internal.plugins;

/**
 * Plugin to set the vendor bug URL
 */
public final class VendorVMBugURLPlugin extends VersionPropsPlugin {

    public VendorVMBugURLPlugin() {
        super("VENDOR_URL_VM_BUG", "vendor-vm-bug-url");
    }

}
