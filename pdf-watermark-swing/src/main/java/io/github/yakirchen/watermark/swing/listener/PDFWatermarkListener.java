package io.github.yakirchen.watermark.swing.listener;

import io.github.yakirchen.watermark.core.PDFWatermark;
import io.github.yakirchen.watermark.core.Watermark;
import io.github.yakirchen.watermark.api.PDFEntity;
import io.github.yakirchen.watermark.logging.Log;
import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;
import io.github.yakirchen.watermark.swing.panel.WatermarkConfPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

/**
 * PDFWatermarkListener
 *
 * @author yakir on 2021/07/14 18:15.
 */
public record PDFWatermarkListener(PDFTablePanel pdfTablePanel, WatermarkConfPanel confPanel) implements ActionListener {

    public static PDFWatermarkListener bind(PDFTablePanel pdfTablePanel, WatermarkConfPanel confPanel) {
        return new PDFWatermarkListener(pdfTablePanel, confPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        var actionCommand = event.getActionCommand();
        var modifiers     = event.getModifiers();
        var when          = event.getWhen();
        var id            = event.getID();

        Log.info("id: {}, actionCommand: {}, modifiers: {}, when: {}", id, actionCommand, modifiers, when);

        List<PDFEntity> pdfEntityList = this.pdfTablePanel.getAll();
        var             watermarkConf = confPanel.action();
        var             color         = watermarkConf.getColor();

        for (PDFEntity pdfEntity : pdfEntityList) {

            var watermark = new Watermark()
                    .setOrigin(pdfEntity.getPath())
                    .setAlpha(watermarkConf.getAlpha() / 255)
                    .setColorRGB(color.getRed(), color.getGreen(), color.getBlue())
                    .setFontSize(watermarkConf.getFontSize())
                    .setText(Optional.ofNullable(watermarkConf.getText()).filter(_text -> !_text.isBlank()).orElse("水印"));

            PDFWatermark.builder(watermark).mark();
        }
    }
}
