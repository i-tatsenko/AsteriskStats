package GUI.TableModels;


import Statistics.Stats;
import Statistics.User;

import java.util.LinkedList;

public class PopNumbersModel extends AbstractTableModel {

    public PopNumbersModel(String firstColumnName){
        setColumnNames(new String[]{firstColumnName, "Кол-во звонков", "Время, c"});
        data = new String[1][3];
    }

    @Override
    public <List> void setData(LinkedList l) {
        Stats stats;
        int length = l.size() - 1;
        int i = 0;
        data = new String[l.size()][3];
        for (Object listItem:l){
            stats = (Stats)listItem;
            data[length - i][0] = User.checkName(stats.getTargetNumber());
            data[length - i][1] = String.valueOf(stats.getCallsCount());
            data[length - i++][2] = String.valueOf((int)(stats.getDuration()));
        }
    }

    @Override
    protected void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }
}
