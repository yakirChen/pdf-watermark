package io.github.yakirchen.watermark.swing.panel;

import io.github.yakirchen.watermark.core.PDFFont;
import io.github.yakirchen.watermark.swing.entity.WatermarkConf;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Optional;

/**
 * WatermarkConfPanel
 *
 * @author yakir on 2021/07/19 21:15.
 */
public class WatermarkConfPanel extends JPanel {

    private final int DEFAULT_ALPHA = 20;

    private JTextField textInput;
    private JSlider    alphaSlider;
    private JSpinner   fontSizeSpinner;

    public WatermarkConfPanel() {
        super();

        this.setPreferredSize(new Dimension(500, 250));
        this.setMaximumSize(new Dimension(500, 250));

        var lineSize         = new Dimension(480, 40);
        var labelSize        = new Dimension(80, 40);
        var inputSize        = new Dimension(120, 40);
        var colorChooserSize = new Dimension(480, 50);

        var line0 = Box.createHorizontalBox();
        line0.setPreferredSize(lineSize);
        var line1 = Box.createHorizontalBox();
        line1.setPreferredSize(lineSize);
        var line2 = Box.createHorizontalBox();
        line2.setPreferredSize(lineSize);
        var line3 = Box.createHorizontalBox();
        line3.setPreferredSize(colorChooserSize);
        var root = Box.createVerticalBox();

        var textLabel = new JLabel("文字:");
        textLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        textLabel.setPreferredSize(labelSize);
        this.textInput = new JTextField();
        textInput.setPreferredSize(inputSize);

        line0.add(textLabel);
        line0.add(Box.createHorizontalStrut(20));
        line0.add(textInput);

        var alphaLabel = new JLabel("透明度:");
        alphaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        alphaLabel.setPreferredSize(labelSize);
        // 透明度值
        var alphaValueLabel = new JLabel(String.valueOf(DEFAULT_ALPHA));
        alphaValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alphaValueLabel.setPreferredSize(labelSize);
        this.alphaSlider = new JSlider(1, 255, 2);
        alphaSlider.setMajorTickSpacing(5);
        alphaSlider.setMinorTickSpacing(1);
        alphaSlider.setPaintTicks(true);
        alphaSlider.addChangeListener((ChangeEvent _event) -> {
            var value = alphaSlider.getValue();
            alphaValueLabel.setText(String.valueOf(value));
        });

        line1.add(alphaLabel);
        line1.add(Box.createHorizontalStrut(20));
        line1.add(alphaSlider);
        line1.add(Box.createHorizontalStrut(10));
        line1.add(alphaValueLabel);

        var fontSizeLabel = new JLabel("字体大小:");
        fontSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        fontSizeLabel.setPreferredSize(labelSize);
        this.fontSizeSpinner = new JSpinner(new SpinnerNumberModel(130, 0, Integer.MAX_VALUE, 10));

        line2.add(fontSizeLabel);
        line2.add(Box.createHorizontalStrut(20));
        line2.add(fontSizeSpinner);

        var defaultColor      = new Color(225, 0, 0, DEFAULT_ALPHA);
        var colorPreviewLabel = new JLabel("颜色预览Preview");
        colorPreviewLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        colorPreviewLabel.setPreferredSize(colorPreviewLabel.getPreferredSize());
        colorPreviewLabel.setMaximumSize(colorPreviewLabel.getPreferredSize());
        colorPreviewLabel.setForeground(defaultColor);

        var colorChooserBtn = new JButton("选择字体颜色");
        colorChooserBtn.setHorizontalAlignment(SwingConstants.CENTER);
        colorChooserBtn.setVerticalAlignment(SwingConstants.CENTER);
        colorChooserBtn.setPreferredSize(new Dimension(120, 45));
        colorChooserBtn.setMinimumSize(colorChooserBtn.getPreferredSize());
        colorChooserBtn.setMaximumSize(colorChooserBtn.getPreferredSize());
        colorChooserBtn.addActionListener(_event -> {
            var color = JColorChooser.showDialog(this, "选择字体颜色", defaultColor);
            colorPreviewLabel.setForeground(color);
        });

        var colorBtnBox = Box.createVerticalBox();
        colorBtnBox.setPreferredSize(new Dimension(120, 50));
        colorBtnBox.setMaximumSize(colorBtnBox.getPreferredSize());
        colorBtnBox.setMinimumSize(colorBtnBox.getPreferredSize());
        colorBtnBox.add(Box.createVerticalGlue());
        colorBtnBox.add(colorChooserBtn);
        colorBtnBox.add(Box.createVerticalGlue());

        var colorPreviewBox = Box.createVerticalBox();
        colorPreviewBox.setPreferredSize(new Dimension(180, 50));
        colorPreviewBox.setMinimumSize(colorPreviewBox.getPreferredSize());
        colorPreviewBox.setMaximumSize(colorPreviewBox.getPreferredSize());
        colorPreviewBox.add(colorPreviewLabel);

        line3.add(colorBtnBox);
        line3.add(Box.createHorizontalStrut(50));
        line3.add(colorPreviewBox);

        root.add(Box.createVerticalStrut(10));
        root.add(line0);
        root.add(Box.createVerticalStrut(10));
        root.add(line1);
        root.add(Box.createVerticalStrut(10));
        root.add(line2);
        root.add(Box.createVerticalStrut(10));
        root.add(line3);
        root.add(Box.createVerticalStrut(10));

        this.add(root);
    }

    public WatermarkConf action() {
        var cfg = new WatermarkConf();
        cfg.setText(this.textInput.getText());
        cfg.setAlpha(this.alphaSlider.getValue());
        cfg.setFontSize(Optional.of(this.fontSizeSpinner.getValue()).map(String::valueOf).map(Integer::valueOf).orElse(130));
        return cfg;
    }
}
