package GUI.TableModels;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import Statistics.User;

public class UsersTableDataModel extends AbstractTableModel {

     public UsersTableDataModel(){
         setData(null);
         setColumnNames(new String[]{"Номер", "Пользователь"});
    }
    @Override
    public <List> void setData(LinkedList l) {
        data = new String[2][2];
        TreeSet<User> usersSet =  User.getUsers();
        if (!usersSet.isEmpty()){
            data = new String[usersSet.size()][2];
            int i=0;
            for (User user:usersSet){
                data[i][0] = user.getNumber();
                data[i++][1] = user.getName();
            }
        }
    }

    @Override
    protected void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }
}
