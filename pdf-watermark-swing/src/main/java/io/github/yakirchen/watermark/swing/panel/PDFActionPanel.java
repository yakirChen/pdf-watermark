package io.github.yakirchen.watermark.swing.panel;

import io.github.yakirchen.watermark.swing.BoxBuilder;
import io.github.yakirchen.watermark.swing.listener.PDFChooserListener;
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

    public PDFActionPanel action(PDFTablePanel pdfTablePanel, WatermarkConfPanel watermarkConfPanel) {

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

        var btnWmBox = Box.createVerticalBox();
        btnWmBox.setPreferredSize(btnSize);
        btnWmBox.setMaximumSize(btnWmBox.getPreferredSize());
        btnWmBox.setMinimumSize(btnWmBox.getPreferredSize());
        btnWmBox.add(Box.createVerticalGlue());
        btnWmBox.add(btnWm);
        btnWmBox.add(Box.createVerticalGlue());

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
                .addVerticalStrut(10);

        v0.add(line0);
        v0.add(Box.createVerticalStrut(10));
        v0.add(line1.get());

        this.add(v0);

        return this;
    }
}
