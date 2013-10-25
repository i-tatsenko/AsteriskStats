package GUI;



import GUI.TableModels.AbstractTableModel;
import GUI.TableModels.CallLogTableModel;
import Statistics.Call;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class CallsTablePanel extends JScrollPane{
    private static CallsTablePanel callsTablePanel;
    private static PanelCallsTable CALLS_TABLE;
    CallsTablePanel(){
        super();
        this.setPreferredSize(new Dimension(Gui.getPreferredWidth(1 - RunningSettingsPanel.SIZE_RATIO).width - 10, Gui.getMainWindow().getSize().height / 5 * 2));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        CALLS_TABLE = new PanelCallsTable();
        this.setViewportView(CALLS_TABLE);
        callsTablePanel = this;
        System.out.println("Calls Logo = " + this.getPreferredSize());
    }

    public void resizeTable(){
        JTable table = (JTable)this.getViewport().getView();
        int height = table.getRowCount() * table.getRowHeight();
        table.setPreferredSize(new Dimension(CallsTablePanel.WIDTH - 10, height));
        table.tableChanged(new TableModelEvent(table.getModel()));
    }

    public static CallsTablePanel getCallsTablePanel(){
        return callsTablePanel;
    }

    class PanelCallsTable extends JTable{
        PanelCallsTable(){
            super();
            setPreferredSize(new Dimension(CallsTablePanel.WIDTH, 500));
            this.setBackground(Color.LIGHT_GRAY);
            this.getTableHeader().setForeground(new Color(0,0,160));
            setModel(new CallLogTableModel());
            CallsLogSorter sorter = new CallsLogSorter(this.getModel());
            this.setRowSorter(sorter);
        }
    }

    class CallsLogSorter extends TableRowSorter<TableModel>{

        CallsLogSorter(TableModel model){
            super(model);
        }

        public Comparator<?> getComparator(int columnIndex){
            if (columnIndex == 0){
                return new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        Date d1 = sqlStringToDate(s1);
                        Date d2 = sqlStringToDate(s2);
                        return new Double(d1.getTime() - d2.getTime()).intValue();
                    }
                };
            }
            if ((columnIndex == 1) || (columnIndex == 2)){
                return new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return cutNumber(s1) - cutNumber(s2);
                    }

                    Integer cutNumber(String s){
                        if (s.contains("<")) s = s.substring(s.indexOf('<') + 1, s.indexOf('>'));
                        return Integer.valueOf(s);
                    }
                };

            }
            if (columnIndex == 3){
                return new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return Integer.valueOf(s1) - Integer.valueOf(s2);
                    }
                };
            }
            return super.getComparator(columnIndex);
        }

        private Date sqlStringToDate(String s){
            DateFormat df = new SimpleDateFormat("yyyy-M-dd HH:mm:ss.S");
            try {
                return df.parse(s);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

    }

    public void updateTable(LinkedList<Call> calls){
        AbstractTableModel logTableModel = (AbstractTableModel)CALLS_TABLE.getModel();
        logTableModel.setData(calls);
        resizeTable();


    }
}
