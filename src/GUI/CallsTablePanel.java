package GUI;



import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class CallsTablePanel extends JScrollPane{
    private static int WIDTH;
    private static int HEIGHT;
    private static CallsTablePanel callsTablePanel;
    CallsTablePanel(){
        super();
        WIDTH = new Double(Gui.getMainWindow().getSize().width * 0.71).intValue();
        HEIGHT = new Double(Gui.getMainWindow().getHeight() * 0.5).intValue();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setViewportView(new PanelCallsTable());
        callsTablePanel = this;
    }

    public void resizeTable(){
        JTable table = (JTable)this.getViewport().getView();
        int height = table.getRowCount() * table.getRowHeight();
        table.setPreferredSize(new Dimension(table.getPreferredSize().width, height));
    }

    public static CallsTablePanel getCallsTablePanel(){
        return callsTablePanel;
    }

    class PanelCallsTable extends JTable{
        PanelCallsTable(){
            super();
            setPreferredSize(new Dimension(WIDTH, 500));
            this.setBackground(Gui.getMainWindow().getBackground());
            setModel(new CallLogTableModel());
            this.getColumnModel().getColumn(0).setPreferredWidth(4);
            this.getColumnModel().getColumn(1).setPreferredWidth(10);
            this.getColumnModel().getColumn(2).setPreferredWidth(10);
            this.getColumnModel().getColumn(3).setPreferredWidth(1);
            CallsLogSorter sorter = new CallsLogSorter(this.getModel());
            this.setRowSorter(sorter);
        }
    }

    static class CallsLogSorter extends TableRowSorter<TableModel>{

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
                        System.out.print(s);
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

    public static void updateTable(){
        callsTablePanel.getViewport().getView().update(callsTablePanel.getViewport().getView().getGraphics());
        JTable a = (JTable)callsTablePanel.getViewport().getView();

    }
}
