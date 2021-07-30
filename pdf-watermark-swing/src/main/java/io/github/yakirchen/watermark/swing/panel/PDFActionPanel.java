package io.github.yakirchen.watermark.swing.panel;

import io.github.yakirchen.watermark.swing.BoxBuilder;
import io.github.yakirchen.watermark.swing.listener.PDFChooserListener;
import io.github.yakirchen.watermark.swing.listener.PDFPreviewListener;
import io.github.yakirchen.watermark.swing.listener.PDFRemoveListener;
import io.github.yakirchen.watermark.swing.listener.PDFWatermarkListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * PDFActionPanel
 *
 * @author yakir on 2021/07/14 10:41.
 */
public class PDFActionPanel extends JPanel {

    public PDFActionPanel() {
        super(new GridLayout());
        var panelSize = new Dimension(360, 250);
        this.setPreferredSize(panelSize);
        this.setMaximumSize(panelSize);
        this.setMinimumSize(panelSize);
    }

    public PDFActionPanel action(PDFTablePanel pdfTablePanel, WatermarkConfPanel watermarkConfPanel, PDFPreviewPanel pdfPreviewPanel) {

        var btnSize = new Dimension(120, 45);

        var btnAdd = new JButton("添加PDF");
        btnAdd.addActionListener(PDFChooserListener.bind(btnAdd, pdfTablePanel));
        btnAdd.setPreferredSize(btnSize);
        btnAdd.setMaximumSize(btnSize);
        btnAdd.setMinimumSize(btnSize);
        btnAdd.setBackground(Color.cyan);

        var btnRemove = new JButton("移除PDF");
        btnRemove.addActionListener(PDFRemoveListener.bind(pdfTablePanel));
        btnRemove.setPreferredSize(btnSize);
        btnRemove.setMinimumSize(btnSize);
        btnRemove.setMaximumSize(btnSize);

        var btnWm = new JButton("生成水印");
        btnWm.setPreferredSize(btnSize);
        btnWm.setMinimumSize(btnSize);
        btnWm.setMaximumSize(btnSize);
        btnWm.addActionListener(PDFWatermarkListener.bind(pdfTablePanel, watermarkConfPanel));

        var previewWm = new JButton("预览");
        previewWm.setPreferredSize(btnSize);
        previewWm.setMinimumSize(btnSize);
        previewWm.setMaximumSize(btnSize);
        previewWm.addActionListener(PDFPreviewListener.bind(pdfPreviewPanel, watermarkConfPanel));

        var btnAddBox = BoxBuilder.verticalBox(btnSize)
                .addVerticalGlue()
                .add(btnAdd)
                .addVerticalGlue()
                .get();
        var btnRemoveBox = BoxBuilder.verticalBox(btnSize)
                .addVerticalGlue()
                .add(btnRemove)
                .addVerticalGlue()
                .get();
        var btnWmBox = BoxBuilder.verticalBox(btnSize)
                .addVerticalGlue()
                .add(btnWm)
                .addVerticalGlue()
                .get();
        var previewWmBox = BoxBuilder.verticalBox(btnSize)
                .addVerticalGlue()
                .add(previewWm)
                .addVerticalGlue()
                .get();

        var lineSize = new Dimension(480, 60);
        var v0       = Box.createVerticalBox();

        var line0 = BoxBuilder.horizontalBox(lineSize)
                .addVerticalStrut(10)
                .add(btnAddBox)
                .addVerticalStrut(10)
                .add(btnRemoveBox)
                .addVerticalStrut(10)
                .get();

        var line1 = BoxBuilder.horizontalBox(lineSize)
                .addVerticalStrut(10)
                .add(btnWmBox)
                .addVerticalStrut(10)
                .add(previewWmBox)
                .addVerticalStrut(10)
                .get();

        v0.add(line0);
        v0.add(Box.createVerticalStrut(10));
        v0.add(line1);

        this.add(v0);

        return this;
    }
}
