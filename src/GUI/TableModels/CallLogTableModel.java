package GUI.TableModels;

import Statistics.Call;
import Statistics.User;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.LinkedList;


public class CallLogTableModel extends AbstractTableModel {

    public CallLogTableModel(){
        setColumnNames(new String[]{"Время", "Кто", "Куда", "Длительность"});
        data = new String[1][4];
    }

      public <List>  void setData(LinkedList callsList){
          LinkedList<Call> calls = (LinkedList<Call>)callsList.clone();

        data = new String[calls.size()][4];
        int i = 0;
        String[] callArray;
        Call call = (Call)calls.pollLast();
        while (call != null){
            callArray = call.getCall();
            data[i][0] = callArray[Call.CALL_DATE];
            data[i][1] = User.checkName(callArray[Call.SRC]);
            data[i][2] = User.checkName(callArray[Call.DST]);
            data[i++][3] = callArray[Call.DURATION];
            call = (Call)calls.pollLast();

        }
      }

    @Override
    protected void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }


}
