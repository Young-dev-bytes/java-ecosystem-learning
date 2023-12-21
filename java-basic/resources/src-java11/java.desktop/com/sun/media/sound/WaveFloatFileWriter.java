/*
 * Copyright (c) 2008, 2017, Oracle and/or its affiliates. All rights reserved.
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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.spi.AudioFileWriter;

/**
 * Floating-point encoded (format 3) WAVE file writer.
 *
 * @author Karl Helgason
 */
public final class WaveFloatFileWriter extends AudioFileWriter {

    @Override
    public Type[] getAudioFileTypes() {
        return new Type[]{Type.WAVE};
    }

    @Override
    public Type[] getAudioFileTypes(AudioInputStream stream) {

        if (!stream.getFormat().getEncoding().equals(Encoding.PCM_FLOAT))
            return new Type[0];
        return new Type[] { Type.WAVE };
    }

    private void checkFormat(AudioFileFormat.Type type, AudioInputStream stream) {
        if (!Type.WAVE.equals(type))
            throw new IllegalArgumentException("File type " + type
                    + " not supported.");
        if (!stream.getFormat().getEncoding().equals(Encoding.PCM_FLOAT))
            throw new IllegalArgumentException("File format "
                    + stream.getFormat() + " not supported.");
    }

    public void write(AudioInputStream stream, RIFFWriter writer)
            throws IOException {
        try (final RIFFWriter fmt_chunk = writer.writeChunk("fmt ")) {
            AudioFormat format = stream.getFormat();
            fmt_chunk.writeUnsignedShort(3); // WAVE_FORMAT_IEEE_FLOAT
            fmt_chunk.writeUnsignedShort(format.getChannels());
            fmt_chunk.writeUnsignedInt((int) format.getSampleRate());
            fmt_chunk.writeUnsignedInt(((int) format.getFrameRate())
                                               * format.getFrameSize());
            fmt_chunk.writeUnsignedShort(format.getFrameSize());
            fmt_chunk.writeUnsignedShort(format.getSampleSizeInBits());
        }
        try (RIFFWriter data_chunk = writer.writeChunk("data")) {
            byte[] buff = new byte[1024];
            int len;
            while ((len = stream.read(buff, 0, buff.length)) != -1) {
                data_chunk.write(buff, 0, len);
            }
        }
    }

    private static final class NoCloseOutputStream extends OutputStream {
        final OutputStream out;

        NoCloseOutputStream(OutputStream out) {
            this.out = out;
        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);
        }

        @Override
        public void flush() throws IOException {
            out.flush();
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
        }

        @Override
        public void write(byte[] b) throws IOException {
            out.write(b);
        }
    }

    private AudioInputStream toLittleEndian(AudioInputStream ais) {
        AudioFormat format = ais.getFormat();
        AudioFormat targetFormat = new AudioFormat(format.getEncoding(), format
                .getSampleRate(), format.getSampleSizeInBits(), format
                .getChannels(), format.getFrameSize(), format.getFrameRate(),
                false);
        return AudioSystem.getAudioInputStream(targetFormat, ais);
    }

    @Override
    public int write(AudioInputStream stream, Type fileType, OutputStream out)
            throws IOException {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(fileType);
        Objects.requireNonNull(out);

        checkFormat(fileType, stream);
        if (stream.getFormat().isBigEndian())
            stream = toLittleEndian(stream);
        try (final RIFFWriter writer = new RIFFWriter(
                new NoCloseOutputStream(out), "WAVE")) {
            write(stream, writer);
            return (int) writer.getFilePointer();
        }
    }

    @Override
    public int write(AudioInputStream stream, Type fileType, File out)
            throws IOException {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(fileType);
        Objects.requireNonNull(out);

        checkFormat(fileType, stream);
        if (stream.getFormat().isBigEndian())
            stream = toLittleEndian(stream);
        try (final RIFFWriter writer = new RIFFWriter(out, "WAVE")) {
            write(stream, writer);
            return (int) writer.getFilePointer();
        }
    }
}
