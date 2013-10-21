package GUI;

import Statistics.Call;
import Statistics.CallStats;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public  class Gui extends JFrame {
    private final static Double SCREEN_RATIO = 0.7;
    private static  Gui mainWindow;
    private  Gui(){
        super();
        mainWindow = this;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setNormalWindowsize(this);
        this.setLayout(new BorderLayout());
        this.add(new RunningSettingsPanel(), BorderLayout.WEST);
        this.add(new StatsPanel(), BorderLayout.EAST);

    }

    private void setNormalWindowsize(JFrame window){
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int windowHeight = new Double(screenSize.getHeight() * SCREEN_RATIO).intValue();
        int windowWidth = new Double(screenSize.getWidth() * SCREEN_RATIO).intValue();
        int locationX = new Double((screenSize.getWidth() - windowWidth) / 2).intValue();
        int locationY = new Double((screenSize.getHeight() - windowHeight) / 2).intValue();
        window.setBounds(locationX, locationY, windowWidth, windowHeight);
    }

    public static Dimension getPreferredWidth(Double screenRatio){
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = new Double(screenSize.getHeight() * SCREEN_RATIO).intValue();
        int width = new Double(screenSize.getWidth() * screenRatio).intValue();

        return new Dimension(width, height);
    }

    public static Gui createGui(){
        if (mainWindow == null) return new Gui();
        else return mainWindow;
    }

    public static Gui getMainWindow(){
        return mainWindow;
    }

    public void setData(CallStats stats, LinkedList<Call> calls){
        CallLogTableModel.setData(calls);
        CallsTablePanel.getCallsTablePanel().resizeTable();
    }

}

