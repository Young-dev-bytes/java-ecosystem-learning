/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
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

package sun.font;

public final class CCompositeGlyphMapper extends CompositeGlyphMapper {

    private CompositeFont font;
    private CharToGlyphMapper slotMappers[];

    public CCompositeGlyphMapper(CompositeFont compFont) {
        super(compFont);
        font = compFont;
        slotMappers = new CharToGlyphMapper[font.numSlots];
        missingGlyph = 0;
    }

    private CharToGlyphMapper getSlotMapper(int slot) {
        CharToGlyphMapper mapper = slotMappers[slot];
        if (mapper == null) {
            mapper = font.getSlotFont(slot).getMapper();
            slotMappers[slot] = mapper;
        }
        return mapper;
    }

    public boolean canDisplay(char ch) {
        int glyph = charToGlyph(ch);
        return glyph != missingGlyph;
    }

    private int convertToGlyph(int unicode) {
        for (int slot = 0; slot < font.numSlots; slot++) {
            CharToGlyphMapper mapper = getSlotMapper(slot);
            int glyphCode = mapper.charToGlyph(unicode);
            // The CFont Mappers will return a negative code
            // for fonts that will fill the glyph from fallbacks
            // - cascading font in OSX-speak. But we need to be
            //  know here that only the codes > 0 are really present.
            if (glyphCode > 0) {
                glyphCode = compositeGlyphCode(slot, glyphCode);
                return glyphCode;
            }
        }
        return missingGlyph;
    }

    public int getNumGlyphs() {
        int numGlyphs = 0;
        for (int slot=0; slot<1 /*font.numSlots*/; slot++) {
           CharToGlyphMapper mapper = slotMappers[slot];
           if (mapper == null) {
               mapper = font.getSlotFont(slot).getMapper();
               slotMappers[slot] = mapper;
           }
           numGlyphs += mapper.getNumGlyphs();
        }
        return numGlyphs;
    }

    public int charToGlyph(int unicode) {
        return convertToGlyph(unicode);
    }

    public int charToGlyph(char unicode) {
        return convertToGlyph(unicode);
    }

    public boolean charsToGlyphsNS(int count, char[] unicodes, int[] glyphs) {

        for (int i=0; i<count; i++) {
            int code = unicodes[i]; // char is unsigned.

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                char low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;
                    glyphs[i + 1] = INVISIBLE_GLYPH_ID;
                }
            }

            glyphs[i] = convertToGlyph(code);

            if (code < FontUtilities.MIN_LAYOUT_CHARCODE) {
                continue;
            }
            else if (FontUtilities.isComplexCharCode(code)) {
                return true;
            }
            else if (code >= 0x10000) {
                i += 1; // Empty glyph slot after surrogate
                continue;
            }
        }

        return false;
    }

    public void charsToGlyphs(int count, char[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
            int code = unicodes[i]; // char is unsigned.

            if (code >= HI_SURROGATE_START &&
                code <= HI_SURROGATE_END && i < count - 1) {
                char low = unicodes[i + 1];

                if (low >= LO_SURROGATE_START &&
                    low <= LO_SURROGATE_END) {
                    code = (code - HI_SURROGATE_START) *
                        0x400 + low - LO_SURROGATE_START + 0x10000;

                    glyphs[i] = convertToGlyph(code);
                    i += 1; // Empty glyph slot after surrogate
                    glyphs[i] = INVISIBLE_GLYPH_ID;
                    continue;
                }
            }

            glyphs[i] = convertToGlyph(code);
        }
    }

    public void charsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
             glyphs[i] = convertToGlyph(unicodes[i]);
        }
    }

}
