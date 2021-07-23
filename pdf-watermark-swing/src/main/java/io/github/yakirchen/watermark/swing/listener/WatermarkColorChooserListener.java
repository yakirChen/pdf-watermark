package io.github.yakirchen.watermark.swing.listener;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;

/**
 * WatermarkColorChooserListener
 *
 * @author yakir on 2021/07/22 22:10.
 */
public class WatermarkColorChooserListener implements ChangeListener {

    private JLabel  colorPreviewLabel;
    private JLabel  alphaValueLabel;
    private Color   color;
    private JSlider alphaSlider;

    private WatermarkColorChooserListener(JLabel colorPreviewLabel,
                                          JLabel alphaValueLabel,
                                          JSlider alphaSlider,
                                          Color color) {
        this.colorPreviewLabel = colorPreviewLabel;
        this.alphaValueLabel   = alphaValueLabel;
        this.alphaSlider       = alphaSlider;
        this.color             = color;
    }

    public static WatermarkColorChooserListener bind(JLabel colorPreviewLabel,
                                                     JLabel alphaValueLabel,
                                                     JSlider alphaSlider,
                                                     Color color) {

        return new WatermarkColorChooserListener(colorPreviewLabel, alphaValueLabel, alphaSlider, color);
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        var value = alphaSlider.getValue();
        alphaValueLabel.setText(String.valueOf(value));
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaSlider.getValue());
        colorPreviewLabel.setForeground(color);
    }
}
