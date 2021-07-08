package io.github.yakirchen.watermark.core;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;

/**
 * Watermark
 *
 * <p>origin: 源PDF文件
 * <p>override: 是否覆盖源文件
 * <p>suffix: 不覆盖源文件的情况下使用的文件后缀
 * <p>alpha: 水印透明度
 * <p>color: 水印颜色
 * <p>fontSize: 水印文字大小
 * <p>fontFamily: 字体
 * <p>text: 文本水印
 *
 * @author yakir on 2021/07/08 22:03.
 */
public class Watermark implements Serializable {

    @Serial
    private static final long serialVersionUID = -7140946722905459506L;

    public static final String DEFAULT_SUFFIX = "watermark";

    private String  origin;
    private Boolean override = false;
    private String  suffix;
    private float   alpha;
    private Color   colorRGB;
    private int     fontSize;
    private String  fontFamily;
    private String  text;

    public String getOrigin() {
        return origin;
    }

    public Watermark setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public Boolean getOverride() {
        return override;
    }

    public Watermark setOverride(Boolean override) {
        this.override = override;
        return this;
    }

    public String getSuffix() {
        return suffix;
    }

    public Watermark setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public float getAlpha() {
        return alpha;
    }

    public Watermark setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public Color getColorRGB() {
        return colorRGB;
    }

    public Watermark setColorRGB(int r, int g, int b) {
        this.colorRGB = new Color(r, g, b);
        return this;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Watermark setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public Watermark setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public String getText() {
        return text;
    }

    public Watermark setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "Watermark{" +
                "origin='" + origin + '\'' +
                ", override=" + override +
                ", suffix='" + suffix + '\'' +
                ", alpha=" + alpha +
                ", color='" + colorRGB + '\'' +
                ", fontSize=" + fontSize +
                ", fontFamily='" + fontFamily + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
