package io.github.yakirchen.watermark.swing;

import io.github.yakirchen.watermark.core.PDFFont;
import io.github.yakirchen.watermark.swing.panel.PDFActionPanel;
import io.github.yakirchen.watermark.swing.panel.PDFPreviewPanel;
import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;
import io.github.yakirchen.watermark.swing.panel.WatermarkConfPanel;

import javax.swing.Box;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

/**
 * MainGUI
 *
 * @author yakir on 2021/07/09 19:52.
 */
public class MainGUI {

    public static void root() {

        var fontA = PDFFont.loadFont(PDFFont.FONT_A);
        var fontB = PDFFont.loadFont(PDFFont.FONT_B);
        if (null != fontA && null != fontB) {
            GraphicsEnvironment graphicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            System.out.println("注册字体 " + PDFFont.FONT_A);
            graphicsEnv.registerFont(fontA);
            System.out.println("注册字体 " + PDFFont.FONT_B);
            graphicsEnv.registerFont(fontB);
        }

        var frame = new JFrame();
        var size  = new Dimension(860, 600);
        frame.setTitle("PDF添加水印");
        frame.setLocationRelativeTo(null);
        frame.setSize(size);
        frame.setMaximumSize(size);
        frame.setMinimumSize(size);
        frame.setPreferredSize(size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var pdfTablePanel   = new PDFTablePanel();
        var pdfConfigPanel  = new WatermarkConfPanel();
        var pdfActionPanel  = new PDFActionPanel().action(pdfTablePanel, pdfConfigPanel);
        var pdfPreviewPanel = new PDFPreviewPanel();

        // 垂直 容纳所有
        var vbox0 = Box.createVerticalBox();

        // 水平 容纳table、预览
        var hbox0 = Box.createHorizontalBox();
        // 水平 容纳配置、动作
        var hbox1 = Box.createHorizontalBox();

        hbox0.add(pdfTablePanel);
        hbox0.add(Box.createVerticalStrut(1));
        hbox0.add(pdfPreviewPanel);

        hbox1.add(pdfConfigPanel);
        hbox1.add(Box.createVerticalStrut(1));
        hbox1.add(pdfActionPanel);

        vbox0.add(hbox0);
        vbox0.add(Box.createHorizontalStrut(1));
        vbox0.add(hbox1);
        vbox0.add(Box.createHorizontalStrut(1));

        frame.add(vbox0);

        frame.setJMenuBar(MenuBuilder.build(pdfTablePanel));

        MenuBuilder.systemTray();

        frame.pack();
        frame.setVisible(true);
    }

}
