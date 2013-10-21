package GUI;

import Statistics.Call;
import Statistics.User;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.LinkedList;


public class CallLogTableModel implements TableModel {
    private static String columnNames[] = new String[]{"Время", "Кто", "Куда", "Длительность"};
    private static String[][] data = new String[1][4];

    @Override
    public int getRowCount() {
        if (data !=null) return data.length;
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (data[0] !=null) return data[0].length;
        return 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if ((data != null) && (data[rowIndex] != null)) return data[rowIndex][columnIndex];
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public static void setData(LinkedList<Call> calls){
        data = new String[calls.size()][4];
        int i = 0;
        String[] callArray;
        Call call = calls.pollLast();
        while (call != null){
            callArray = call.getCall();
            data[i][0] = callArray[Call.CALL_DATE];
            data[i][1] = User.checkName(callArray[Call.SRC]);
            data[i][2] = User.checkName(callArray[Call.DST]);
            data[i++][3] = callArray[Call.DURATION];
            call = calls.pollLast();
        }
    }
}
