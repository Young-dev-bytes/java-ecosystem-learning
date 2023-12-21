/*
 * Copyright (c) 2016, 2022, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.internal.instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdk.internal.access.SharedSecrets;
import jdk.jfr.Event;
import jdk.jfr.events.ActiveRecordingEvent;
import jdk.jfr.events.ActiveSettingEvent;
import jdk.jfr.events.ErrorThrownEvent;
import jdk.jfr.events.ExceptionStatisticsEvent;
import jdk.jfr.events.ExceptionThrownEvent;
import jdk.jfr.events.FileForceEvent;
import jdk.jfr.events.FileReadEvent;
import jdk.jfr.events.FileWriteEvent;
import jdk.jfr.events.DeserializationEvent;
import jdk.jfr.events.InitialSecurityPropertyEvent;
import jdk.jfr.events.SecurityPropertyModificationEvent;
import jdk.jfr.events.SecurityProviderServiceEvent;
import jdk.jfr.events.SocketReadEvent;
import jdk.jfr.events.SocketWriteEvent;
import jdk.jfr.events.TLSHandshakeEvent;
import jdk.jfr.events.X509CertificateEvent;
import jdk.jfr.events.X509ValidationEvent;
import jdk.jfr.internal.JVM;
import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import jdk.jfr.internal.RequestEngine;
import jdk.jfr.internal.SecuritySupport;
import jdk.jfr.internal.Utils;

public final class JDKEvents {

    private static final Class<?>[] mirrorEventClasses = {
        DeserializationEvent.class,
        SecurityPropertyModificationEvent.class,
        SecurityProviderServiceEvent.class,
        TLSHandshakeEvent.class,
        X509CertificateEvent.class,
        X509ValidationEvent.class
    };

    private static final Class<?>[] eventClasses = {
        FileForceEvent.class,
        FileReadEvent.class,
        FileWriteEvent.class,
        SocketReadEvent.class,
        SocketWriteEvent.class,
        ExceptionThrownEvent.class,
        ExceptionStatisticsEvent.class,
        ErrorThrownEvent.class,
        ActiveSettingEvent.class,
        ActiveRecordingEvent.class,
        jdk.internal.event.DeserializationEvent.class,
        jdk.internal.event.SecurityPropertyModificationEvent.class,
        jdk.internal.event.SecurityProviderServiceEvent.class,
        jdk.internal.event.TLSHandshakeEvent.class,
        jdk.internal.event.X509CertificateEvent.class,
        jdk.internal.event.X509ValidationEvent.class,
        InitialSecurityPropertyEvent.class
    };

    // This is a list of the classes with instrumentation code that should be applied.
    private static final Class<?>[] instrumentationClasses = new Class<?>[] {
        FileInputStreamInstrumentor.class,
        FileOutputStreamInstrumentor.class,
        RandomAccessFileInstrumentor.class,
        FileChannelImplInstrumentor.class,
        SocketInputStreamInstrumentor.class,
        SocketOutputStreamInstrumentor.class,
        SocketChannelImplInstrumentor.class
    };

    private static final Class<?>[] targetClasses = new Class<?>[instrumentationClasses.length];
    private static final JVM jvm = JVM.getJVM();
    private static final Runnable emitExceptionStatistics = JDKEvents::emitExceptionStatistics;
    private static final Runnable emitInitialSecurityProperties = JDKEvents::emitInitialSecurityProperties;
    private static boolean initializationTriggered;

    @SuppressWarnings("unchecked")
    public synchronized static void initialize() {
        try {
            if (initializationTriggered == false) {
                for (Class<?> mirrorEventClass : mirrorEventClasses) {
                    SecuritySupport.registerMirror(((Class<? extends Event>)mirrorEventClass));
                }
                for (Class<?> eventClass : eventClasses) {
                    SecuritySupport.registerEvent((Class<? extends Event>) eventClass);
                }
                initializationTriggered = true;
                RequestEngine.addTrustedJDKHook(ExceptionStatisticsEvent.class, emitExceptionStatistics);
                RequestEngine.addTrustedJDKHook(InitialSecurityPropertyEvent.class, emitInitialSecurityProperties);
            }
        } catch (Exception e) {
            Logger.log(LogTag.JFR_SYSTEM, LogLevel.WARN, "Could not initialize JDK events. " + e.getMessage());
        }
    }

    public static void addInstrumentation() {
        try {
            List<Class<?>> list = new ArrayList<>();
            for (int i = 0; i < instrumentationClasses.length; i++) {
                JIInstrumentationTarget tgt = instrumentationClasses[i].getAnnotation(JIInstrumentationTarget.class);
                Class<?> clazz = Class.forName(tgt.value());
                targetClasses[i] = clazz;
                list.add(clazz);
            }
            list.add(java.lang.Throwable.class);
            list.add(java.lang.Error.class);
            Logger.log(LogTag.JFR_SYSTEM, LogLevel.INFO, "Retransformed JDK classes");
            jvm.retransformClasses(list.toArray(new Class<?>[list.size()]));
        } catch (Exception e) {
            Logger.log(LogTag.JFR_SYSTEM, LogLevel.WARN, "Could not add instrumentation for JDK events. " + e.getMessage());
        }
    }

    private static void emitExceptionStatistics() {
        ExceptionStatisticsEvent t = new ExceptionStatisticsEvent();
        t.throwables = ThrowableTracer.numThrowables();
        t.commit();
    }

    @SuppressWarnings("deprecation")
    public static byte[] retransformCallback(Class<?> klass, byte[] oldBytes) throws Throwable {
        if (java.lang.Throwable.class == klass) {
            Logger.log(LogTag.JFR_SYSTEM, LogLevel.TRACE, "Instrumenting java.lang.Throwable");
            return ConstructorTracerWriter.generateBytes(java.lang.Throwable.class, oldBytes);
        }

        if (java.lang.Error.class == klass) {
            Logger.log(LogTag.JFR_SYSTEM, LogLevel.TRACE, "Instrumenting java.lang.Error");
            return ConstructorTracerWriter.generateBytes(java.lang.Error.class, oldBytes);
        }

        for (int i = 0; i < targetClasses.length; i++) {
            if (targetClasses[i].equals(klass)) {
                Class<?> c = instrumentationClasses[i];
                Logger.log(LogTag.JFR_SYSTEM, LogLevel.TRACE, () -> "Processing instrumentation class: " + c);
                return new JIClassInstrumentation(instrumentationClasses[i], klass, oldBytes).getNewBytes();
            }
        }
        return oldBytes;
    }

    public static void remove() {
        RequestEngine.removeHook(JDKEvents::emitExceptionStatistics);
        RequestEngine.removeHook(emitInitialSecurityProperties);
    }

    private static void emitInitialSecurityProperties() {
        Properties p = SharedSecrets.getJavaSecurityPropertiesAccess().getInitialProperties();
        if (p != null) {
            for (String key : p.stringPropertyNames()) {
                InitialSecurityPropertyEvent e = new InitialSecurityPropertyEvent();
                e.key = key;
                e.value = p.getProperty(key);
                e.commit();
            }
        }
    }
}
