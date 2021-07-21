package io.github.yakirchen.watermark.swing.entity;

import java.awt.Color;
import java.io.Serial;
import java.io.Serializable;

/**
 * WatermarkConf
 *
 * @author yakir on 2021/07/19 21:11.
 */
public class WatermarkConf implements Serializable {

    @Serial
    private static final long serialVersionUID = -5139950915902792066L;

    private Color  color;
    private String text;
    private float  alpha;
    private int    fontSize;

    public Color getColor() {
        return color;
    }

    public WatermarkConf setColor(Color color) {
        this.color = color;
        return this;
    }

    public String getText() {
        return text;
    }

    public WatermarkConf setText(String text) {
        this.text = text;
        return this;
    }

    public float getAlpha() {
        return alpha;
    }

    public WatermarkConf setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public int getFontSize() {
        return fontSize;
    }

    public WatermarkConf setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    @Override
    public String toString() {
        return "WatermarkConf{" +
                "color=" + color +
                ", text='" + text + '\'' +
                ", alpha=" + alpha +
                ", fontSize=" + fontSize +
                '}';
    }
}
