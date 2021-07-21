package io.github.yakirchen.watermark.swing.panel;

import io.github.yakirchen.watermark.swing.listener.PDFChooserListener;
import io.github.yakirchen.watermark.swing.listener.PDFRemoveListener;
import io.github.yakirchen.watermark.swing.listener.PDFWatermarkListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
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

        var btnSize = new Dimension(80, 20);

        var btnAdd = new JButton("添加PDF");
        btnAdd.addActionListener(PDFChooserListener.bind(btnAdd).datasource(pdfTablePanel));
        btnAdd.setPreferredSize(btnSize);
        btnAdd.setMaximumSize(btnSize);
        btnAdd.setMinimumSize(btnSize);

        var btnRemove = new JButton("移除PDF");
        btnRemove.addActionListener(PDFRemoveListener.bind(btnRemove).datasource(pdfTablePanel));
        btnRemove.setPreferredSize(btnSize);
        btnRemove.setMinimumSize(btnSize);
        btnRemove.setMaximumSize(btnSize);

        var btnWm = new JButton("为全部PDF添加水印");
        btnWm.setPreferredSize(btnSize);
        btnWm.setMinimumSize(btnSize);
        btnWm.setMaximumSize(btnSize);
        btnWm.addActionListener(PDFWatermarkListener.bind(btnWm).action(pdfTablePanel, watermarkConfPanel));

        var lineSize = new Dimension(480, 40);
        var line0    = Box.createHorizontalBox();
        line0.setPreferredSize(lineSize);
        var line1 = Box.createHorizontalBox();
        line1.setPreferredSize(lineSize);
        var v0 = Box.createVerticalBox();

        var btnAddBox = Box.createVerticalBox();
        btnAddBox.setPreferredSize(btnSize);
        btnAddBox.setMaximumSize(btnAddBox.getPreferredSize());
        btnAddBox.setMinimumSize(btnAddBox.getPreferredSize());
        btnAddBox.add(Box.createVerticalGlue());
        btnAddBox.add(btnAdd);
        btnAddBox.add(Box.createVerticalGlue());

        var btnRemoveBox = Box.createVerticalBox();
        btnRemoveBox.setPreferredSize(btnSize);
        btnRemoveBox.setMaximumSize(btnRemoveBox.getPreferredSize());
        btnRemoveBox.setMinimumSize(btnRemoveBox.getPreferredSize());
        btnRemoveBox.add(Box.createVerticalGlue());
        btnRemoveBox.add(btnRemove);
        btnRemoveBox.add(Box.createVerticalGlue());

        var btnWmBox = Box.createVerticalBox();
        btnWmBox.setPreferredSize(btnSize);
        btnWmBox.setMaximumSize(btnWmBox.getPreferredSize());
        btnWmBox.setMinimumSize(btnWmBox.getPreferredSize());
        btnWmBox.add(Box.createVerticalGlue());
        btnWmBox.add(btnWm);
        btnWmBox.add(Box.createVerticalGlue());

        line0.add(Box.createVerticalStrut(10));
        line0.add(btnAddBox);
        line0.add(Box.createVerticalStrut(10));
        line0.add(btnRemoveBox);
        line0.add(Box.createVerticalStrut(10));

        line1.add(btnWmBox);

        v0.add(line0);
        v0.add(Box.createVerticalStrut(10));
        v0.add(line1);

        this.add(v0);

        return this;
    }
}
