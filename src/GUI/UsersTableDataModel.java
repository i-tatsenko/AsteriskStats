package GUI;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import Statistics.User;

public class UsersTableDataModel implements TableModel {
    private static String[] columnNames = {"Number", "Name"};
    private static String[][] data;

    public UsersTableDataModel(JTable table){
        data = new String[2][2];
       TreeSet<User> usersSet =  User.getUsers();
        if (!usersSet.isEmpty()){
            data = new String[usersSet.size()][2];
            int i=0;
            for (User user:usersSet){
                data[i][0] = user.getNumber();
                data[i++][1] = user.getName();
            }
            table.setPreferredSize(new Dimension(new Double(table.getPreferredSize().getWidth()).intValue(), (data.length) * table.getRowHeight()));

        }

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
}
