package io.github.yakirchen.watermark.swing.listener;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static io.github.yakirchen.watermark.swing.panel.WatermarkConfPanel.color;

/**
 * ColorChooserListener
 *
 * @author yakir on 2021/07/22 22:10.
 */
public record ColorAlphaListener(Component component, JLabel colorPreviewLabel, String colorChooserTitle, JSlider alphaSlider) implements ActionListener {

    public static ColorAlphaListener bind(Component component, JLabel colorPreviewLabel, String colorChooserTitle, JSlider alphaSlider) {

        return new ColorAlphaListener(component, colorPreviewLabel, colorChooserTitle, alphaSlider);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        color = JColorChooser.showDialog(component, colorChooserTitle, color);
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaSlider.getValue());
        colorPreviewLabel.setForeground(color);
    }

}
