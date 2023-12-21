/*
 * Copyright (c) 2000, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.ui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.utilities.*;

/** Uses {@link sun.jvm.hotspot.utilities.PointerFinder} to provide a
    graphical user interface to the VM's debugging utility "find". */

public class FindPanel extends JPanel {
  // UI widgets we need permanent handles to
  private JTextField addressField;
  private JTextArea  textArea;
  private JLabel statusLabel;

  public FindPanel() {
    super();

    setLayout(new BorderLayout());
    Box hbox = Box.createHorizontalBox();
    hbox.add(new JLabel("Address: "));
    addressField = new JTextField(20);
    hbox.add(addressField);
    statusLabel = new JLabel();
    hbox.add(statusLabel);
    add(hbox, BorderLayout.NORTH);

    JScrollPane scroller = new JScrollPane();
    textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    scroller.getViewport().add(textArea);
    add(scroller, BorderLayout.CENTER);

    addressField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            Address a = VM.getVM().getDebugger().parseAddress(addressField.getText());
            PointerLocation loc = PointerFinder.find(a);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            loc.printOn(new PrintStream(bos));
            clear();
            textArea.append(bos.toString());
            statusLabel.setText("");
          }
          catch (NumberFormatException ex) {
            statusLabel.setText("<parse error>");
          }
          catch (AddressException ex) {
            statusLabel.setText("<bad address>");
          }
          catch (Exception ex) {
            ex.printStackTrace();
            statusLabel.setText("<error during find>");
          }
        }
      });
  }

  private void clear() {
    Document doc = textArea.getDocument();
    if (doc.getLength() > 0) {
      try {
        doc.remove(0, doc.getLength());
      }
      catch (BadLocationException e) {
      }
    }
  }
}
