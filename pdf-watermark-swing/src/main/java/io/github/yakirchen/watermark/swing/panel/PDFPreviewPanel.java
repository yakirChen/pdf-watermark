package io.github.yakirchen.watermark.swing.panel;

import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * PDFPreviewPanel
 *
 * @author yakir on 2021/07/14 16:05.
 */
public class PDFPreviewPanel extends JPanel {

    public PDFPreviewPanel() {
        super();
        var panelSize = new Dimension(360, 348);
        this.setPreferredSize(panelSize);
        this.setMaximumSize(panelSize);
        this.setMinimumSize(panelSize);
    }
}
