package io.github.yakirchen.watermark.swing;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.SystemTray;
import java.awt.Toolkit;

/**
 * WatermarkApp
 * <p>
 * 应用名称设置 -Xdock:name=PDF水印
 *
 * @author yakir on 2021/07/09 11:36.
 */
public class WatermarkApp {

    public static void main(String[] args) {

        try {
            // 使用macOS系统菜单样式
            System.setProperty("swing.defaultlaf", "com.apple.laf.AquaLookAndFeel");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "About");
            System.setProperty("com.apple.mrj.application.live-resize", "true");
            var laf = UIManager.getSystemLookAndFeelClassName();
            System.out.println("Look & Feel " + laf);
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        var systemTray = SystemTray.isSupported();
        System.out.println("SystemTray is " + (systemTray ? "" : "not ") + "supported");

        SwingUtilities.invokeLater(MainGUI::root);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
