package io.github.yakirchen.watermark.swing.listener;

import io.github.yakirchen.watermark.api.PDFEntity;
import io.github.yakirchen.watermark.swing.panel.PDFTablePanel;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static io.github.yakirchen.watermark.core.Constants.HOME;

/**
 * PDFChooser
 *
 * @author yakir on 2021/07/09 18:30.
 */
public class PDFChooserListener implements ActionListener {

    private final Component     component;
    private final PDFTablePanel pdfTablePanel;
    private final JFileChooser  fileChooser;


    private PDFChooserListener(Component component, PDFTablePanel pdfTablePanel) {
        this.component     = component;
        this.pdfTablePanel = pdfTablePanel;

        this.fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File(HOME));
        fileChooser.setFileSelectionMode(0);// 设定只能选择到文件
        fileChooser.setFileFilter(new PDFFileFilter());
    }

    public static PDFChooserListener bind(Component component, PDFTablePanel pdfTablePanel) {
        return new PDFChooserListener(component, pdfTablePanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        var actionCommand = event.getActionCommand();
        var modifiers     = event.getModifiers();
        var when          = event.getWhen();
        var id            = event.getID();

        System.out.printf("id: %d, actionCommand: %s, modifiers: %d, when: %d \n", id, actionCommand, modifiers, when);

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
