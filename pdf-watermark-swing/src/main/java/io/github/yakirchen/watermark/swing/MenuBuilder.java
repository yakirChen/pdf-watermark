package io.github.yakirchen.watermark.swing;

import io.github.yakirchen.watermark.swing.listener.PDFChooserListener;
import io.github.yakirchen.watermark.swing.listener.PDFRemoveListener;
import io.github.yakirchen.watermark.swing.pane.PDFTablePane;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * MenuBuilder
 *
 * @author yakir on 2021/07/09 19:55.
 */
public class MenuBuilder {

    public static JMenuBar build(PDFTablePane pdfTablePane) {
        JMenuBar menuBar = new JMenuBar();
        JMenu    menu    = new JMenu("文件");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext()
                .setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        JMenuItem addMenuItem = new JMenuItem("添加PDF", KeyEvent.VK_O);
        addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_DOWN_MASK));
        addMenuItem.getAccessibleContext().setAccessibleDescription("打开需要加水印的PDF");
        addMenuItem.addActionListener(PDFChooserListener.bind(addMenuItem).datasource(pdfTablePane));

        JMenuItem removeMenuItem = new JMenuItem("移除PDF", KeyEvent.VK_BACK_SPACE);
        removeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
        removeMenuItem.getAccessibleContext().setAccessibleDescription("打开需要加水印的PDF");
        removeMenuItem.addActionListener(PDFRemoveListener.bind(removeMenuItem).datasource(pdfTablePane));

        menu.add(addMenuItem);
        menu.add(removeMenuItem);

        return menuBar;
    }
}
