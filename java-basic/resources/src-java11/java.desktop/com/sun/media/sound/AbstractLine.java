/*
 * Copyright (c) 1999, 2016, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.media.sound;

import java.util.Map;
import java.util.Vector;
import java.util.WeakHashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/**
 * AbstractLine
 *
 * @author Kara Kytle
 */
abstract class AbstractLine implements Line {

    protected final Line.Info info;
    protected Control[] controls;
    AbstractMixer mixer;
    private volatile boolean open;
    private final Vector<Object> listeners = new Vector<>();

    /**
     * Contains event dispatcher per thread group.
     */
    private static final Map<ThreadGroup, EventDispatcher> dispatchers =
            new WeakHashMap<>();

    /**
     * Constructs a new AbstractLine.
     * @param mixer the mixer with which this line is associated
     * @param controls set of supported controls
     */
    protected AbstractLine(Line.Info info, AbstractMixer mixer, Control[] controls) {

        if (controls == null) {
            controls = new Control[0];
        }

        this.info = info;
        this.mixer = mixer;
        this.controls = controls;
    }

    // LINE METHODS

    @Override
    public final Line.Info getLineInfo() {
        return info;
    }

    @Override
    public final boolean isOpen() {
        return open;
    }

    @Override
    public final void addLineListener(LineListener listener) {
        synchronized(listeners) {
            if ( ! (listeners.contains(listener)) ) {
                listeners.addElement(listener);
            }
        }
    }

    /**
     * Removes an audio listener.
     * @param listener listener to remove
     */
    @Override
    public final void removeLineListener(LineListener listener) {
        listeners.removeElement(listener);
    }

    /**
     * Obtains the set of controls supported by the
     * line.  If no controls are supported, returns an
     * array of length 0.
     * @return control set
     */
    @Override
    public final Control[] getControls() {
        Control[] returnedArray = new Control[controls.length];

        for (int i = 0; i < controls.length; i++) {
            returnedArray[i] = controls[i];
        }

        return returnedArray;
    }

    @Override
    public final boolean isControlSupported(Control.Type controlType) {
        // protect against a NullPointerException
        if (controlType == null) {
            return false;
        }

        for (int i = 0; i < controls.length; i++) {
            if (controlType == controls[i].getType()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public final Control getControl(Control.Type controlType) {
        // protect against a NullPointerException
        if (controlType != null) {

            for (int i = 0; i < controls.length; i++) {
                if (controlType == controls[i].getType()) {
                    return controls[i];
                }
            }
        }

        throw new IllegalArgumentException("Unsupported control type: " + controlType);
    }

    // HELPER METHODS

    /**
     * This method sets the open state and generates
     * events if it changes.
     */
    final void setOpen(boolean open) {

        if (Printer.trace) Printer.trace("> "+getClass().getName()+" (AbstractLine): setOpen(" + open + ")  this.open: " + this.open);

        boolean sendEvents = false;
        long position = getLongFramePosition();

        synchronized (this) {
            if (this.open != open) {
                this.open = open;
                sendEvents = true;
            }
        }

        if (sendEvents) {
            if (open) {
                sendEvents(new LineEvent(this, LineEvent.Type.OPEN, position));
            } else {
                sendEvents(new LineEvent(this, LineEvent.Type.CLOSE, position));
            }
        }
        if (Printer.trace) Printer.trace("< "+getClass().getName()+" (AbstractLine): setOpen(" + open + ")  this.open: " + this.open);
    }

    /**
     * Send line events.
     */
    final void sendEvents(LineEvent event) {
        getEventDispatcher().sendAudioEvents(event, listeners);
    }

    /**
     * This is an error in the API: getFramePosition
     * should return a long value. At CD quality,
     * the int value wraps around after 13 hours.
     */
    public final int getFramePosition() {
        return (int) getLongFramePosition();
    }

    /**
     * Return the frame position in a long value
     * This implementation returns AudioSystem.NOT_SPECIFIED.
     */
    public long getLongFramePosition() {
        return AudioSystem.NOT_SPECIFIED;
    }

    // $$kk: 06.03.99: returns the mixer used in construction.
    // this is a hold-over from when there was a public method like
    // this on line and should be fixed!!
    final AbstractMixer getMixer() {
        return mixer;
    }

    final EventDispatcher getEventDispatcher() {
        // create and start the global event thread
        //TODO  need a way to stop this thread when the engine is done
        final ThreadGroup tg = Thread.currentThread().getThreadGroup();
        synchronized (dispatchers) {
            EventDispatcher eventDispatcher = dispatchers.get(tg);
            if (eventDispatcher == null) {
                eventDispatcher = new EventDispatcher();
                dispatchers.put(tg, eventDispatcher);
                eventDispatcher.start();
            }
            return eventDispatcher;
        }
    }

    @Override
    public abstract void open() throws LineUnavailableException;
    @Override
    public abstract void close();
}
