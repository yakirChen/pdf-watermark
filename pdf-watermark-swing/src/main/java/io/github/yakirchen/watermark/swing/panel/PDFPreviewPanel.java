package io.github.yakirchen.watermark.swing.panel;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * PDFPreviewPanel
 *
 * @author yakir on 2021/07/14 16:05.
 */
public class PDFPreviewPanel extends JPanel {

    private byte[] imageBytes;

    public PDFPreviewPanel() {
        super();
        var panelSize = new Dimension(360, 420);
        this.setPreferredSize(panelSize);
        this.setMaximumSize(panelSize);
        this.setMinimumSize(panelSize);
    }

    public PDFPreviewPanel setIis(byte[] imageBytes) {
        this.imageBytes = imageBytes;
        return this;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (null == imageBytes || imageBytes.length < 1) {
            return;
        }
        try {
            var image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            g.drawImage(image, 0, 0, 360, 420, this);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}
