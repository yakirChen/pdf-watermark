package io.github.yakirchen.watermark.swing.platform;

import io.github.yakirchen.watermark.swing.panel.AboutPanel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.awt.desktop.AboutEvent;

/**
 * MacOS
 *
 * @author yakir on 2021/07/16 14:35.
 */
public class MacOS {

    public static void macOS(JFrame frame) {
        Desktop.getDesktop()
                .setAboutHandler(
                        (AboutEvent e) -> {
                            var aboutPanel = new AboutPanel();
                            JOptionPane.showMessageDialog(frame,
                                    "Put your long message or JComponent here ...",
                                    "Put your dialog title here",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                );
    }

}
