/*
 * Copyright (c) 1997, 2015, Oracle and/or its affiliates. All rights reserved.
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

package javax.swing.plaf;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * Pluggable look and feel interface for <code>JFileChooser</code>.
 *
 * @author Jeff Dinkins
 */

public abstract class FileChooserUI extends ComponentUI
{
    /**
     * Returns an accept-all file filter.
     * @param fc the file chooser
     * @return an accept-all file filter
     */
    public abstract FileFilter getAcceptAllFileFilter(JFileChooser fc);
    /**
     * Returns a file view.
     * @param fc the file chooser
     * @return a file view
     */
    public abstract FileView getFileView(JFileChooser fc);

    /**
     * Returns approve button text.
     * @param fc the file chooser
     * @return approve button text.
     */
    public abstract String getApproveButtonText(JFileChooser fc);
    /**
     * Returns the dialog title.
     * @param fc the file chooser
     * @return the dialog title.
     */
    public abstract String getDialogTitle(JFileChooser fc);

    /**
     * Rescan the current directory.
     * @param fc the file chooser
     */
    public abstract void rescanCurrentDirectory(JFileChooser fc);
    /**
     * Ensure the file in question is visible.
     * @param fc the file chooser
     * @param f the file
     */
    public abstract void ensureFileIsVisible(JFileChooser fc, File f);

    /**
     * Returns default button for current <code>LookAndFeel</code>.
     * <code>JFileChooser</code> will use this button as default button
     * for dialog windows.
     *
     * @param fc the {@code JFileChooser} whose default button is requested
     * @return the default JButton for current look and feel
     * @since 1.7
     */
    public JButton getDefaultButton(JFileChooser fc) {
        return null;
    }
}
