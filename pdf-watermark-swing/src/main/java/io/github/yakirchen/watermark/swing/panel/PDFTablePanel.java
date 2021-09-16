package io.github.yakirchen.watermark.swing.panel;

import io.github.yakirchen.watermark.api.PDFEntity;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * PDFTablePanel
 *
 * @author yakir on 2021/07/09 20:01.
 */
public class PDFTablePanel extends JScrollPane {

    private final JTable            pdfTable;
    private final DefaultTableModel tableModel;

    public PDFTablePanel() {
        super();

        this.setPreferredSize(new Dimension(499, 348));

        this.pdfTable   = new JTable();
        this.tableModel = new TableModelReadonly();
        this.pdfTable.setRowHeight(14);
        this.pdfTable.setModel(tableModel);
        this.pdfTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.tableModel.setColumnIdentifiers(new Object[]{"文件名", "路径"});
        this.pdfTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        this.pdfTable.getColumnModel().getColumn(1).setPreferredWidth(290);
        this.pdfTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setViewportView(this.pdfTable);
        this.setAutoscrolls(true);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public PDFTablePanel add(Object[] row) {
        this.tableModel.addRow(row);
        return this;
    }

    public PDFTablePanel del() {
        var selectedRows = this.pdfTable.getSelectedRows();
        selectedRows = Optional.ofNullable(selectedRows).orElse(new int[]{});

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            var index = selectedRows[i];
            if (index < 0) {
                continue;
            }
            this.tableModel.removeRow(index);
        }
        this.pdfTable.clearSelection();
        return this;
    }

    public List<PDFEntity> getAll() {
        var rowCount = this.tableModel.getRowCount();
        if (rowCount <= 0) {
            return Collections.emptyList();
        }
        List<PDFEntity> list = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {

            final String name = (String) this.tableModel.getValueAt(i, 0);
            final String path = (String) this.tableModel.getValueAt(i, 1);

            list.add(new PDFEntity().setName(name).setPath(path));
            System.out.printf("Row: %d Name: %s Path: %s \n", i, name, path);
        }
        return list;

    }
}
