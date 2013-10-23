package GUI.TableModels;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.LinkedList;


public abstract class AbstractTableModel implements TableModel{
    protected    String[] columnNames;
    protected   String[][] data;

    @Override
    public int getRowCount() {
        if (data != null) return data.length;
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (data != null) return data[0].length;
        return 0;
    }

    @Override
    public String getColumnName(int i) {
        if (columnNames != null) return columnNames[i];
        return null;
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i2) {
        return false;
    }

    @Override
    public Object getValueAt(int i, int i2) {
        if ((data.length > i) && (data[i].length > i2)) return data[i][i2];
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i2) {

    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }

    public abstract <List> void setData(LinkedList l);

    protected abstract void setColumnNames(String[] columnNames);

}
