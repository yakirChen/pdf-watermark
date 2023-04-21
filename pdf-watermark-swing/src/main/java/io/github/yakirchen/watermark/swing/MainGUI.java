package io.github.yakirchen.watermark.swing;

import io.github.yakirchen.watermark.core.PDFFont;
import io.github.yakirchen.watermark.log.Log;
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

        GraphicsEnvironment graphicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

        for (String fontName : new String[]{PDFFont.FONT_A, /*PDFFont.FONT_B*/}) {
            Log.info("注册字体 {}", fontName);
            PDFFont.loadFont(fontName).ifPresent(graphicsEnv::registerFont);
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
        var pdfPreviewPanel = new PDFPreviewPanel();
        var pdfActionPanel  = new PDFActionPanel().action(pdfTablePanel, pdfConfigPanel, pdfPreviewPanel);

        // 水平 容纳所有
        var vbox0 = Box.createHorizontalBox();

        // 垂直 容纳table、预览
        var hbox0 = Box.createVerticalBox();
        // 垂直 容纳配置、动作
        var hbox1 = Box.createVerticalBox();

        hbox0.add(pdfTablePanel);
        hbox0.add(Box.createVerticalStrut(1));
        hbox0.add(pdfConfigPanel);

        hbox1.add(pdfPreviewPanel);
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
