package io.github.yakirchen.watermark.swing.listener;

import io.github.yakirchen.watermark.swing.entity.PDFEntity;
import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * PDFChooser
 *
 * @author yakir on 2021/07/09 18:30.
 */
public class PDFChooserListener implements ActionListener {

    private final Component component;

    private PDFTablePanel pdfTablePanel;

    private PDFChooserListener(Component component) {
        this.component = component;
    }

    public static PDFChooserListener bind(Component component, PDFTablePanel pdfTablePanel) {
        var listener = new PDFChooserListener(component);
        listener.pdfTablePanel = pdfTablePanel;
        return listener;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        var actionCommand = event.getActionCommand();
        var modifiers     = event.getModifiers();
        var when          = event.getWhen();
        var source        = event.getSource();
        var id            = event.getID();

        System.out.printf("id: %d, actionCommand: %s, modifiers: %d, when: %d \n", id, actionCommand, modifiers, when);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileSelectionMode(0);// 设定只能选择到文件
        fileChooser.setFileFilter(new PDFFileFilter());
        if (fileChooser.showOpenDialog(this.component) == JFileChooser.APPROVE_OPTION) {
            File[] fs = fileChooser.getSelectedFiles();// f为选择到的文件
            for (File file : fs) {
                if (null == file) {
                    continue;
                }

                var name = file.getName();
                var path = file.getAbsolutePath();

                var pdfEntity = new PDFEntity().setName(name).setPath(path);

                this.pdfTablePanel.add(new Object[]{pdfEntity.getName(), pdfEntity.getPath()});
            }
        }
    }
}


class PDFFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isFile() && f.getAbsolutePath().endsWith(".pdf");
    }

    @Override
    public String getDescription() {
        return "PDF documents (*.pdf)";
    }
}
