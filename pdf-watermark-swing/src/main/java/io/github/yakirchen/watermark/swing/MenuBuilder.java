package io.github.yakirchen.watermark.swing;

import io.github.yakirchen.watermark.swing.listener.PDFChooserListener;
import io.github.yakirchen.watermark.swing.listener.PDFRemoveListener;
import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;
import io.github.yakirchen.watermark.swing.util.ImageUtils;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 * MenuBuilder
 *
 * @author yakir on 2021/07/09 19:55.
 */
public class MenuBuilder {

    public static JMenuBar build(PDFTablePanel pdfTablePanel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu    menu    = new JMenu("文件");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("文件");
        menuBar.add(menu);

        JMenuItem addMenuItem = new JMenuItem("添加PDF", KeyEvent.VK_O);
        addMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_DOWN_MASK));
        addMenuItem.getAccessibleContext().setAccessibleDescription("打开需要加水印的PDF");
        addMenuItem.addActionListener(PDFChooserListener.bind(addMenuItem).datasource(pdfTablePanel));

        JMenuItem removeMenuItem = new JMenuItem("移除PDF", KeyEvent.VK_BACK_SPACE);
        removeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
        removeMenuItem.getAccessibleContext().setAccessibleDescription("打开需要加水印的PDF");
        removeMenuItem.addActionListener(PDFRemoveListener.bind(removeMenuItem).datasource(pdfTablePanel));

        menu.add(addMenuItem);
        menu.add(removeMenuItem);

        return menuBar;
    }

    public static void systemTray() {

        final PopupMenu  popup    = new PopupMenu();
        var              icon     = Objects.requireNonNull(ImageUtils.createImage("images/icon.png", "pdf watermark icon"));
        final TrayIcon   trayIcon = new TrayIcon(icon, "PDF水印");
        final SystemTray tray     = SystemTray.getSystemTray();

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(_action -> {
            tray.remove(trayIcon);
            System.exit(0);
        });

        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

}
