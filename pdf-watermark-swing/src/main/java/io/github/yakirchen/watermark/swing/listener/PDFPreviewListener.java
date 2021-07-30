package io.github.yakirchen.watermark.swing.listener;

import io.github.yakirchen.watermark.core.PDFManager;
import io.github.yakirchen.watermark.core.PDFWatermark;
import io.github.yakirchen.watermark.core.Watermark;
import io.github.yakirchen.watermark.swing.panel.PDFPreviewPanel;
import io.github.yakirchen.watermark.swing.panel.WatermarkConfPanel;

import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * PDFPreviewListener 水印预览
 * <p>
 * 实际为pdf生成图片
 *
 * @author yakir on 2021/07/14 18:15.
 */
public record PDFPreviewListener(PDFPreviewPanel pdfPreviewPanel, WatermarkConfPanel confPanel) implements ActionListener {

    public static PDFPreviewListener bind(PDFPreviewPanel pdfPreviewPanel, WatermarkConfPanel confPanel) {
        return new PDFPreviewListener(pdfPreviewPanel, confPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        var actionCommand = event.getActionCommand();
        var modifiers     = event.getModifiers();
        var when          = event.getWhen();
        var id            = event.getID();

        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                var bytes = PDFManager.createEmptyPdf();
                if (null == bytes || bytes.length < 1) {
                    return null;
                }

                System.out.printf("id: %d, actionCommand: %s, modifiers: %d, when: %d \n", id, actionCommand, modifiers, when);

                var watermarkConf = confPanel.action();
                var color         = watermarkConf.getColor();

                var watermark = new Watermark()
                        .setOriginBytes(bytes)
                        .setAlpha(watermarkConf.getAlpha() / 255)
                        .setColorRGB(color.getRed(), color.getGreen(), color.getBlue())
                        .setFontSize(watermarkConf.getFontSize())
                        .setText(Optional.ofNullable(watermarkConf.getText()).filter(_text -> !_text.isBlank()).orElse("水印"));

                var retBytes = PDFWatermark.builder(watermark).preview();

                pdfPreviewPanel.setIis(retBytes).repaint();

                return null;
            }
        }.execute();


    }
}
