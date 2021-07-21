package io.github.yakirchen.watermark.swing.panel;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;

/**
 * TableModelReadonly
 *
 * @author yakir on 2021/07/14 10:38.
 */
public class TableModelReadonly extends DefaultTableModel {

    @Serial
    private static final long serialVersionUID = -3441144734432638392L;

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
