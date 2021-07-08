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
    }

    public PDFActionPanel action(PDFTablePanel pdfTablePanel) {

        var btnSize = new Dimension(80, 20);

        var btnAdd = new JButton("添加PDF");
        btnAdd.addActionListener(PDFChooserListener.bind(btnAdd).datasource(pdfTablePanel));
        btnAdd.setPreferredSize(btnSize);
        var btnRemove = new JButton("移除PDF");
        btnRemove.addActionListener(PDFRemoveListener.bind(btnRemove).datasource(pdfTablePanel));
        btnRemove.setPreferredSize(btnSize);
        var btnWm = new JButton("为全部PDF添加水印");
        btnWm.setPreferredSize(btnSize);
        btnWm.addActionListener(PDFWatermarkListener.bind(btnWm).datasource(pdfTablePanel));

        var hBox1 = Box.createHorizontalBox();
        var vBox1 = Box.createVerticalBox();

        hBox1.add(btnAdd);
        hBox1.add(btnRemove);
        vBox1.add(hBox1);

        var hBox2 = Box.createHorizontalBox();
        hBox2.add(btnWm);
        vBox1.add(hBox2);

        this.add(vBox1);

        return this;
    }
}
