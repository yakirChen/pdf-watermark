package io.github.yakirchen.watermark.swing.listener;

import io.github.yakirchen.watermark.core.PDFWatermark;
import io.github.yakirchen.watermark.core.Watermark;
import io.github.yakirchen.watermark.swing.entity.PDFEntity;
import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * PDFWatermarkListener
 *
 * @author yakir on 2021/07/14 18:15.
 */
public class PDFWatermarkListener implements ActionListener {

    private final Component component;

    private PDFTablePanel pdfTablePanel;

    private PDFWatermarkListener(Component component) {
        this.component = component;
    }

    public static PDFWatermarkListener bind(Component component) {
        return new PDFWatermarkListener(component);
    }

    public PDFWatermarkListener datasource(PDFTablePanel pdfTablePanel) {
        this.pdfTablePanel = pdfTablePanel;
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        var actionCommand = event.getActionCommand();
        var modifiers     = event.getModifiers();
        var when          = event.getWhen();
        var source        = event.getSource();
        var id            = event.getID();

        System.out.printf("id: %d, actionCommand: %s, modifiers: %d, when: %d \n", id, actionCommand, modifiers, when);

        List<PDFEntity> pdfEntityList = this.pdfTablePanel.getAll();

        final var text = "你好🙂!";

        CompletableFuture.completedFuture(pdfEntityList)
                .thenAccept(_pdfEntityList -> {
                    for (int i = 0; i < _pdfEntityList.size(); i++) {

                        var pdfEntity = _pdfEntityList.get(i);

                        var watermark = new Watermark()
                                .setOrigin(pdfEntity.getPath())
                                .setAlpha(0.2f)
                                .setColorRGB(225, 0, 0)
                                .setFontSize(130)
                                .setText(text);

                        PDFWatermark.builder(watermark)
                                .mark();
                    }
                });


    }
}
