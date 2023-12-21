/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
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

package sun.awt.X11;

import java.awt.Toolkit;
import java.awt.Component;

import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.dnd.DragGestureListener;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import sun.awt.dnd.SunDragSourceContextPeer;

/**
 * <p>
 * This subclass of MouseDragGestureRecognizer defines a DragGestureRecognizer
 * for Mouse based gestures on OSF/Motif.
 * </p>
 *
 * @author Laurence P. G. Cable
 *
 * @see java.awt.dnd.DragGestureListener
 * @see java.awt.dnd.DragGestureEvent
 * @see java.awt.dnd.DragSource
 */

class XMouseDragGestureRecognizer extends MouseDragGestureRecognizer {

    private static final long serialVersionUID = -841711780352520383L;

    /*
     * constant for number of pixels hysterisis before drag is determined
     * to have started
     */

    protected static int motionThreshold;


    protected static final int ButtonMask = InputEvent.BUTTON1_DOWN_MASK |
                                            InputEvent.BUTTON2_DOWN_MASK |
                                            InputEvent.BUTTON3_DOWN_MASK;

    /**
     * construct a new XMouseDragGestureRecognizer
     *
     * @param ds  The DragSource for the Component c
     * @param c   The Component to observe
     * @param act The actions permitted for this Drag
     * @param dgl The DragGestureRecognizer to notify when a gesture is detected
     *
     */

    protected XMouseDragGestureRecognizer(DragSource ds, Component c, int act, DragGestureListener dgl) {
        super(ds, c, act, dgl);
    }

    /**
     * construct a new XMouseDragGestureRecognizer
     *
     * @param ds  The DragSource for the Component c
     * @param c   The Component to observe
     * @param act The actions permitted for this Drag
     */

    protected XMouseDragGestureRecognizer(DragSource ds, Component c, int act) {
        this(ds, c, act, null);
    }

    /**
     * construct a new XMouseDragGestureRecognizer
     *
     * @param ds  The DragSource for the Component c
     * @param c   The Component to observe
     */

    protected XMouseDragGestureRecognizer(DragSource ds, Component c) {
        this(ds, c, DnDConstants.ACTION_NONE);
    }

    /**
     * construct a new XMouseDragGestureRecognizer
     *
     * @param ds  The DragSource for the Component c
     */

    protected XMouseDragGestureRecognizer(DragSource ds) {
        this(ds, null);
    }

    /**
     * determine the drop action from the event
     */

    protected int mapDragOperationFromModifiers(MouseEvent e) {
        int mods = e.getModifiersEx();
        int btns = mods & ButtonMask;

        // Do not allow right mouse button drag since Motif DnD does not
        // terminate drag operation on right mouse button release.
        if (!(btns == InputEvent.BUTTON1_DOWN_MASK ||
              btns == InputEvent.BUTTON2_DOWN_MASK)) {
            return DnDConstants.ACTION_NONE;
        }

        return
            SunDragSourceContextPeer.convertModifiersToDropAction(mods,
                                                                  getSourceActions());
    }

    /**
     * Invoked when the mouse has been clicked on a component.
     */

    public void mouseClicked(MouseEvent e) {
        // do nothing
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */

    public void mousePressed(MouseEvent e) {
        events.clear();

        if (mapDragOperationFromModifiers(e) != DnDConstants.ACTION_NONE) {
            try {
                motionThreshold = DragSource.getDragThreshold();
            } catch (Exception exc) {
                motionThreshold = 5;
            }
            appendEvent(e);
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     */

    public void mouseReleased(MouseEvent e) {
        events.clear();
    }

    /**
     * Invoked when the mouse enters a component.
     */

    public void mouseEntered(MouseEvent e) {
        events.clear();
    }

    /**
     * Invoked when the mouse exits a component.
     */

    public void mouseExited(MouseEvent e) {
        if (!events.isEmpty()) { // gesture pending
            int dragAction = mapDragOperationFromModifiers(e);

            if (dragAction == DnDConstants.ACTION_NONE) {
                events.clear();
            }
        }
    }

    /**
     * Invoked when a mouse button is pressed on a component.
     */

    public void mouseDragged(MouseEvent e) {
        if (!events.isEmpty()) { // gesture pending
            int dop = mapDragOperationFromModifiers(e);


            if (dop == DnDConstants.ACTION_NONE) {
                return;
            }

            MouseEvent trigger = (MouseEvent)events.get(0);

            Point      origin  = trigger.getPoint();
            Point      current = e.getPoint();

            int        dx      = Math.abs(origin.x - current.x);
            int        dy      = Math.abs(origin.y - current.y);

            if (dx > motionThreshold || dy > motionThreshold) {
                fireDragGestureRecognized(dop, ((MouseEvent)getTriggerEvent()).getPoint());
            } else
                appendEvent(e);
        }
    }

    /**
     * Invoked when the mouse button has been moved on a component
     * (with no buttons no down).
     */

    public void mouseMoved(MouseEvent e) {
        // do nothing
    }
}
