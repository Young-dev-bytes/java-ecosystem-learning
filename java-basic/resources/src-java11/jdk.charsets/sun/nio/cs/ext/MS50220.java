/*
 * Copyright (c) 2005, 2012, Oracle and/or its affiliates. All rights reserved.
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

package sun.nio.cs.ext;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import sun.nio.cs.DoubleByte;
import sun.nio.cs.*;

public class MS50220 extends ISO2022_JP
{
    public MS50220() {
        super("x-windows-50220",
              ExtendedCharsets.aliasesFor("x-windows-50220"));
    }

    protected MS50220(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    public String historicalName() {
        return "MS50220";
    }

    public boolean contains(Charset cs) {
      return super.contains(cs) ||
             (cs instanceof JIS_X_0212) ||
             (cs instanceof MS50220);
    }

    public CharsetDecoder newDecoder() {
        return new Decoder(this, DEC0208, DEC0212);
    }

    public CharsetEncoder newEncoder() {
        return new Encoder(this, ENC0208, ENC0212, doSBKANA());
    }

    private final static DoubleByte.Decoder DEC0208 =
        (DoubleByte.Decoder)new JIS_X_0208_MS5022X().newDecoder();

    private final static DoubleByte.Decoder DEC0212 =
        (DoubleByte.Decoder)new JIS_X_0212_MS5022X().newDecoder();

    private final static DoubleByte.Encoder ENC0208 =
        (DoubleByte.Encoder)new JIS_X_0208_MS5022X().newEncoder();

    private final static DoubleByte.Encoder ENC0212 =
        (DoubleByte.Encoder)new JIS_X_0212_MS5022X().newEncoder();

    protected boolean doSBKANA() {
        return false;
    }
}
