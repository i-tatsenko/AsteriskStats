package GUI;

import javax.swing.*;
import java.awt.*;


public  class StatsPanel extends JPanel {
    public static int PANE_WIDTH;
    public static int PANE_HEIGHT;
    StatsPanel(){
        super();
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Gui.getPreferredWidth(1 - RunningSettingsPanel.SIZE_RATIO).width - 10, Gui.getMainWindow().getSize().height));
        add(new CallsTablePanel(), BorderLayout.NORTH);
        add(new PopNumbersPanel(), BorderLayout.SOUTH);

        PANE_WIDTH = this.getPreferredSize().width;
        PANE_HEIGHT = this.getPreferredSize().height;

    }
}
