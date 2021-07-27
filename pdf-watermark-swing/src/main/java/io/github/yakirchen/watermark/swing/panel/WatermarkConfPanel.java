package io.github.yakirchen.watermark.swing.panel;

import io.github.yakirchen.watermark.swing.BoxBuilder;
import io.github.yakirchen.watermark.swing.entity.WatermarkConf;
import io.github.yakirchen.watermark.swing.listener.ColorAlphaListener;
import io.github.yakirchen.watermark.swing.listener.ColorChooserListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
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

    private static final int   DEFAULT_ALPHA = (int) (255 * 0.2);
    private static final Color DEFAULT_COLOR = new Color(225, 0, 0, DEFAULT_ALPHA);

    private final JTextField textInput;
    private final JSlider    alphaSlider;
    private final JSpinner   fontSizeSpinner;

    public static Color color = DEFAULT_COLOR;

    public WatermarkConfPanel() {
        super();

        this.setPreferredSize(new Dimension(500, 250));
        this.setMaximumSize(new Dimension(500, 250));

        var lineSize         = new Dimension(480, 40);
        var labelSize        = new Dimension(80, 40);
        var inputSize        = new Dimension(120, 40);
        var colorChooserSize = new Dimension(480, 50);

        var line0 = BoxBuilder.horizontalBox(lineSize);
        var line1 = BoxBuilder.horizontalBox(lineSize);
        var line2 = BoxBuilder.horizontalBox(lineSize);
        var line3 = BoxBuilder.horizontalBox(lineSize);
        var root  = Box.createVerticalBox();

        var textLabel = new JLabel("文字:");
        textLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        textLabel.setPreferredSize(labelSize);
        this.textInput = new JTextField();
        textInput.setPreferredSize(inputSize);

        line0.add(textLabel);
        line0.addHorizontalStrut(20);
        line0.add(textInput);

        var colorPreviewLabel = new JLabel("颜色预览Preview");
        colorPreviewLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        colorPreviewLabel.setPreferredSize(colorPreviewLabel.getPreferredSize());
        colorPreviewLabel.setMaximumSize(colorPreviewLabel.getPreferredSize());
        colorPreviewLabel.setForeground(DEFAULT_COLOR);

        var alphaLabel = new JLabel("透明度:");
        alphaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        alphaLabel.setPreferredSize(labelSize);
        // 透明度值
        var alphaValueLabel = new JLabel(String.valueOf(DEFAULT_ALPHA));
        alphaValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alphaValueLabel.setPreferredSize(labelSize);
        this.alphaSlider = new JSlider(1, 255, DEFAULT_ALPHA);
        alphaSlider.setMajorTickSpacing(5);
        alphaSlider.setMinorTickSpacing(1);
        alphaSlider.setPaintTicks(true);
        alphaSlider.addChangeListener(ColorChooserListener.bind(colorPreviewLabel, alphaValueLabel, alphaSlider));

        line1.add(alphaLabel);
        line1.addHorizontalStrut(20);
        line1.add(alphaSlider);
        line1.addHorizontalStrut(10);
        line1.add(alphaValueLabel);

        var fontSizeLabel = new JLabel("字体大小:");
        fontSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        fontSizeLabel.setPreferredSize(labelSize);
        this.fontSizeSpinner = new JSpinner(new SpinnerNumberModel(130, 0, Integer.MAX_VALUE, 10));

        line2.add(fontSizeLabel);
        line2.addHorizontalStrut(20);
        line2.add(fontSizeSpinner);

        var colorChooserTitle = "选择字体颜色";
        var colorChooserBtn   = new JButton(colorChooserTitle);
        colorChooserBtn.setHorizontalAlignment(SwingConstants.CENTER);
        colorChooserBtn.setVerticalAlignment(SwingConstants.CENTER);
        colorChooserBtn.setPreferredSize(new Dimension(120, 45));
        colorChooserBtn.setMinimumSize(colorChooserBtn.getPreferredSize());
        colorChooserBtn.setMaximumSize(colorChooserBtn.getPreferredSize());
        colorChooserBtn.addActionListener(ColorAlphaListener.bind(this, colorPreviewLabel, colorChooserTitle, alphaSlider));

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
        line3.addHorizontalStrut(50);
        line3.add(colorPreviewBox);

        root.add(Box.createVerticalStrut(10));
        root.add(line0.get());
        root.add(Box.createVerticalStrut(10));
        root.add(line1.get());
        root.add(Box.createVerticalStrut(10));
        root.add(line2.get());
        root.add(Box.createVerticalStrut(10));
        root.add(line3.get());
        root.add(Box.createVerticalStrut(10));

        this.add(root);
    }

    public WatermarkConf action() {
        var cfg = new WatermarkConf();
        cfg.setText(this.textInput.getText());
        cfg.setAlpha(this.alphaSlider.getValue());
        cfg.setColor(this.color);
        cfg.setFontSize(Optional.of(this.fontSizeSpinner.getValue()).map(String::valueOf).map(Integer::valueOf).orElse(130));
        return cfg;
    }
}
