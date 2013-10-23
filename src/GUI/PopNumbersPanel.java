package GUI;


import GUI.TableModels.PopNumbersModel;
import Statistics.Stats;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

public class PopNumbersPanel extends JPanel{
    private static PopNumbersPanel popNumbersPanel;
    private static SRCPopNumbersTable SRCTable;
    private static DSTPopNumbersTable DSTTable;

    public PopNumbersPanel(){
        super();
        popNumbersPanel = this;
        SRCTable = new SRCPopNumbersTable();
        DSTTable = new DSTPopNumbersTable();
        JScrollPane SRCPane = new JScrollPane(SRCTable);
        SRCPane.setPreferredSize(new Dimension(new Double(Gui.getMainWindow().getWidth() * 0.35).intValue(),new Double(Gui.getMainWindow().getHeight() * 0.45).intValue()));
        this.add(SRCPane);
        JScrollPane DSTPane = new JScrollPane(DSTTable);
        DSTPane.setPreferredSize(new Dimension(new Double(Gui.getMainWindow().getWidth() * 0.35).intValue(),new Double(Gui.getMainWindow().getHeight() * 0.45).intValue()));
        this.add(DSTPane);
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
            this.setPreferredSize(new Dimension(1000, 1000));
        }
    }

    public class DSTPopNumbersTable extends PopNumbersTable{
        DSTPopNumbersTable(){
            super();
            this.setModel(new PopNumbersModel("Чаще всего вызывали"));
            this.setPreferredSize(new Dimension(1000, 1000));
        }
    }




    abstract public class PopNumbersTable extends JTable{

        public void setData(Stats[] stats){
            PopNumbersModel model = (PopNumbersModel)this.getModel();
            LinkedList<Stats> statsList = new LinkedList<Stats>();
            if ((stats == null) || (stats.length == 0)) ;
            else {
                Collections.addAll(statsList, stats);
                model.setData(statsList);
                this.setPreferredSize(new Dimension(PopNumbersPanel.WIDTH / 2, this.getRowCount() * this.getRowHeight()));

                PopNumbersPanel.popNumbersPanel.getComponent(0).update(this.getGraphics());
            }

        }


    }



}
