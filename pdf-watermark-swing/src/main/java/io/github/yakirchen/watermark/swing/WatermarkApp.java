package io.github.yakirchen.watermark.swing;

import io.github.yakirchen.watermark.core.log.Log;

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
            Log.info("Look & Feel {}", laf);
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Log.error("Exception: ", e);
        }

        var systemTray = SystemTray.isSupported();
        Log.info("SystemTray is {}", (systemTray ? "" : "not ") + "supported");

        SwingUtilities.invokeLater(MainGUI::root);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
