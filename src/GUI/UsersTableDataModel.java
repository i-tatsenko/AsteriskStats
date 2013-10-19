package GUI;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.10.13
 * Time: 1:45
 * To change this template use File | Settings | File Templates.
 */
public class UsersTableDataModel implements TableModel {
    private static String[] columnNames = {"Number", "Name"};
    private static String[][] data;

    public UsersTableDataModel(){
        data = new String[2][2];
        data[0][0] = "159";
        data[0][1] = "Ivan Tatsenko";
        data[1][0] = "130";
        data[1][1] = "Sergey Hilik";
    }

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
        if ((columnNames != null) && (columnNames.length > i + 1)) return columnNames[i];
        return null;
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return new String().getClass();
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
}
