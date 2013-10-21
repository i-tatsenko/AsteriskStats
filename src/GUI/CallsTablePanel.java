package GUI;


import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import java.awt.*;

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

    static class PanelCallsTable extends JTable{
        PanelCallsTable(){
            super();
            setPreferredSize(new Dimension(WIDTH, 500));
            setModel(new CallLogTableModel());
            this.getTableHeader().setResizingAllowed(false);
            this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            this.getColumnModel().getColumn(3).setPreferredWidth(5);
            this.getColumnModel().getColumn(2).setPreferredWidth(5);
        }
    }
}
