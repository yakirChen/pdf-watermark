package io.github.yakirchen.watermark.swing.listener;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;

import static io.github.yakirchen.watermark.swing.panel.WatermarkConfPanel.color;

/**
 * ColorChooserListener
 *
 * @author yakir on 2021/07/22 22:10.
 */
public record ColorChooserListener(JLabel colorPreviewLabel, JLabel alphaValueLabel, JSlider alphaSlider) implements ChangeListener {

    public static ColorChooserListener bind(JLabel colorPreviewLabel, JLabel alphaValueLabel, JSlider alphaSlider) {

        return new ColorChooserListener(colorPreviewLabel, alphaValueLabel, alphaSlider);
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        var value = alphaSlider.getValue();
        alphaValueLabel.setText(String.valueOf(value));
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaSlider.getValue());
        colorPreviewLabel.setForeground(color);
    }
}
