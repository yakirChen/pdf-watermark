package io.github.yakirchen.watermark.swing;

import io.github.yakirchen.watermark.swing.pane.PDFActionPane;
import io.github.yakirchen.watermark.swing.pane.PDFTablePane;

import javax.swing.Box;
import javax.swing.JFrame;
import java.awt.Dimension;

/**
 * MainGUI
 *
 * @author yakir on 2021/07/09 19:52.
 */
public class MainGUI {

    public static void root() {

        var frame = new JFrame();
        frame.setTitle("PDF添加水印");
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 600);
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());

        var pdfTable  = new PDFTablePane();
        var pdfAction = new PDFActionPane().action(pdfTable);

        var vbox = Box.createVerticalBox();
        var hbox = Box.createHorizontalBox();

        vbox.add(pdfTable);
        vbox.add(Box.createHorizontalStrut(10));
        vbox.add(pdfAction);
        vbox.add(Box.createHorizontalStrut(10));

        hbox.add(vbox);
        frame.add(hbox);

        frame.setJMenuBar(MenuBuilder.build(pdfTable));

        frame.pack();
        frame.setVisible(true);
    }

}
