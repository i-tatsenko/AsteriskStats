package GUI;


import GUI.TableModels.PopNumbersModel;
import Statistics.Stats;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class PopNumbersPanel extends JPanel{
    private static PopNumbersPanel popNumbersPanel;
    private static SRCPopNumbersTable SRCTable;
    private static DSTPopNumbersTable DSTTable;

    public PopNumbersPanel(){
        super();
        this.setLayout(new BorderLayout());
        popNumbersPanel = this;
        SRCTable = new SRCPopNumbersTable();
        DSTTable = new DSTPopNumbersTable();

        this.setPreferredSize(new Dimension(Gui.getPreferredWidth(1 - RunningSettingsPanel.SIZE_RATIO).width - 10, Gui.getMainWindow().getSize().height / 5 * 3 - 30));

        JScrollPane SRCPane = new JScrollPane(SRCTable);
        SRCPane.setPreferredSize(new Dimension(this.getPreferredSize().width / 2 - 1, 1000));
        this.add(SRCPane, BorderLayout.WEST);

        JScrollPane DSTPane = new JScrollPane(DSTTable);
        DSTPane.setPreferredSize(new Dimension(this.getPreferredSize().width / 2 - 1, 1000));
        this.add(DSTPane, BorderLayout.EAST);
    }

    public static PopNumbersPanel getPopNumbersPanel(){
        return popNumbersPanel;
    }

    public static SRCPopNumbersTable getSRCTable(){
        return SRCTable;
    }

    public static DSTPopNumbersTable getDSTTable(){
        return DSTTable;
    }

    public class SRCPopNumbersTable extends PopNumbersTable{
        SRCPopNumbersTable(){
            super();
            this.setModel(new PopNumbersModel("Популярные абоненты"));
            this.setRowSorter(new PopNumbersSort(this.getModel()));
        }
    }

    public class DSTPopNumbersTable extends PopNumbersTable{
        DSTPopNumbersTable(){
            super();
            this.setModel(new PopNumbersModel("Чаще всего вызывали"));
            this.setRowSorter(new PopNumbersSort(this.getModel()));
        }
    }

    class PopNumbersSort extends TableRowSorter<TableModel>{
        PopNumbersSort(TableModel model){
            super(model);
        }

        public Comparator<?> getComparator(int columnIndex){
            if (columnIndex > 1){
                return new Comparator<String>(){
                    @Override
                    public int compare(String s1, String s2) {
                        return Integer.valueOf(s1) - Integer.valueOf(s2);
                    }
                };
            }
            return super.getComparator(columnIndex);
        }

    }

    abstract public class PopNumbersTable extends JTable{

        PopNumbersTable(){
            super();
            this.setPreferredSize(new Dimension(500, 1000));
            this.setBackground(Color.LIGHT_GRAY);
            this.getTableHeader().setForeground(new Color(0,0,160));

        }

        public void setData(Stats[] stats){
            PopNumbersModel model = (PopNumbersModel)this.getModel();
            LinkedList<Stats> statsList = new LinkedList<Stats>();
            if ((stats == null) || (stats.length == 0)) ;
            else {
                Collections.addAll(statsList, stats);
                model.setData(statsList);
                this.setPreferredSize(new Dimension(PopNumbersPanel.WIDTH / 5, this.getRowCount() * this.getRowHeight()));


                this.tableChanged(new TableModelEvent(this.getModel()));
            }
        }
    }
}
