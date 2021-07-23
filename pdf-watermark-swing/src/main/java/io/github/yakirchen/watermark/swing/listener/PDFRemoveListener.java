package io.github.yakirchen.watermark.swing.listener;

import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PDFRemoveListener
 *
 * @author yakir on 2021/07/14 16:36.
 */
public class PDFRemoveListener implements ActionListener {

    private final PDFTablePanel pdfTablePanel;

    private PDFRemoveListener(PDFTablePanel pdfTablePanel) {
        this.pdfTablePanel = pdfTablePanel;
    }

    public static PDFRemoveListener bind(PDFTablePanel pdfTablePanel) {
        return new PDFRemoveListener(pdfTablePanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        var actionCommand = event.getActionCommand();
        var modifiers     = event.getModifiers();
        var when          = event.getWhen();
        var source        = event.getSource();
        var id            = event.getID();

        System.out.printf("id: %d, actionCommand: %s, modifiers: %d, when: %d \n", id, actionCommand, modifiers, when);

        pdfTablePanel.del();
    }

}
