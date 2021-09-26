package io.github.yakirchen.watermark.swing.listener;

import io.github.yakirchen.watermark.logging.Log;
import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PDFRemoveListener
 *
 * @author yakir on 2021/07/14 16:36.
 */
public record PDFRemoveListener(PDFTablePanel pdfTablePanel) implements ActionListener {

    public static PDFRemoveListener bind(PDFTablePanel pdfTablePanel) {
        return new PDFRemoveListener(pdfTablePanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        var actionCommand = event.getActionCommand();
        var modifiers     = event.getModifiers();
        var when          = event.getWhen();
        var id            = event.getID();

        Log.info("id: {}, actionCommand: {}, modifiers: {}, when: {}", id, actionCommand, modifiers, when);

        pdfTablePanel.del();
    }

}
