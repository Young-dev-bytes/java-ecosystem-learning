/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
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

package javax.print.attribute.standard;

import java.awt.Window;
import javax.print.attribute.Attribute;
import javax.print.attribute.PrintRequestAttribute;
import sun.print.DialogOwnerAccessor;

/**
 * An attribute class used to support requesting a print or page setup dialog
 * be kept displayed on top of all windows or some specific window.
 * <p>
 * Constructed without any arguments it will request that a print or page
 * setup dialog be configured as if the application directly was to specify
 * {@code java.awt.Window.setAlwaysOnTop(true)}, subject to permission checks.
 * <p>
 * Constructed with a {@link java.awt.Window} parameter, it requests that
 * the dialog be owned by the specified window.
 *
 * @since 11
 */
public final class DialogOwner implements PrintRequestAttribute {

    private static class Accessor extends DialogOwnerAccessor {

         public long getOwnerID(DialogOwner owner) {
             return owner.getID();
         }
    }

    static private Accessor accessor = new Accessor();
    static {
             DialogOwnerAccessor.setAccessor(accessor);
    }

    private static final long serialVersionUID = -1901909867156076547L;

    private Window owner;
    private transient long id;

    /**
     * Constructs an instance which can be used to request
     * {@code java.awt.Window.setAlwaysOnTop(true)} behaviour.
     * This should be used where there is no application preferred owner window.
     * Whether this has any effect depends on if always on top is supported
     * for this platform and the particular dialog to be displayed.
     */
    public DialogOwner() {
    }

    /**
     * Constructs an instance which can be used to request that the
     * specified {@link java.awt.Window} be the owner of the dialog.
     * @param owner window.
     */
    public DialogOwner(Window owner) {
        this.owner = owner;
    }

    /**
     * Constructs an instance which requests that the dialog be displayed
     * as if it were a child of a native platform window, specified
     * using its opqaue platform identifier or handle.
     * This is useful mainly for the case where the id represents a window
     * which may not be an AWT {@code Window}, but instead was created by
     * another UI toolkit, such as OpenJFX.
     * Any effect is platform dependent.
     * @param id a native window identifier or handle
     */
    DialogOwner(long id) {
        this.id = id;
    }

    /**
     * Returns a native platform id or handle, if one was specified,
     * otherwise, zero.
     * @return a native platform id.
     */
    long getID() {
        return id;
    }

    /**
     * Returns a {@code Window owner}, if one was specified,
     * otherwise {@code null}.
     * @return an owner window.
     */
    public Window getOwner() {
        return owner;
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code DialogOwner}, the category is class
     * {@code DialogOwner} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return DialogOwner.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code DialogOwner}, the category name is
     * {@code "dialog-owner"}.
     *
     */
    public final String getName() {
        return "dialog-owner";

    }
}
