package io.github.yakirchen.watermark.core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * PDFFont
 *
 * @author yakir on 2021/07/20 15:46.
 */
public class PDFFont {

    public static final String FONT_A = "HanaMinA.ttf";
//    public static final String FONT_B = "HanaMinB.ttf";

    private final static ClassLoader classloader = PDFFont.class.getClassLoader();

    public static InputStream load(String fontFile) {
        return classloader.getResourceAsStream(fontFile);
    }

    public static Optional<Font> loadFont(String fontFile) {
        var fontIS = load(fontFile);
        try {
            return Optional.of(Font.createFont(Font.TRUETYPE_FONT, fontIS));
        } catch (FontFormatException | IOException exp) {
            return Optional.empty();
        }
    }
}
