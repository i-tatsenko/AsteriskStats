package GUI;

import javax.swing.*;
import java.awt.*;


public class StatsPanel extends JPanel {
    StatsPanel(){
        super();
        setLayout(new BorderLayout());
        add(new CallsTablePanel(), BorderLayout.NORTH);
    }
}
