package GUI.TableModels;


import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.LinkedList;

public class PopNumbersSrcModel extends AbstractTableModel {
    private String[] columnNames = {"Исходящий номер", "Кол-во звонков", "Длительность, м"};
    private String[][] data;


    @Override
    public <List> void setData(LinkedList l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void setColumnNames() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
