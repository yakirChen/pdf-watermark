package io.github.yakirchen.watermark.swing;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;

/**
 * ReadonlyTableModel
 *
 * @author yakir on 2021/07/14 10:38.
 */
public class ReadonlyTableModel extends DefaultTableModel {

    @Serial
    private static final long serialVersionUID = -3441144734432638392L;

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
