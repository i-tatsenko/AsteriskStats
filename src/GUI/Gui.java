package GUI;

import Statistics.Call;
import Statistics.CallStats;
import Statistics.Stats;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public  class Gui extends JFrame {
    private final static Double SCREEN_RATIO = 0.7;
    private static  Gui mainWindow;
    private static LinkedList<Call> callsList;


    private  Gui(){
        super();
        this.setTitle("AsteriskStats");
        mainWindow = this;
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    public void setData(LinkedList<Call> calls){
        if (calls != null)CallsTablePanel.getCallsTablePanel().updateTable(calls);
        callsList = calls;
        RunningSettingsPanel.getUsersTable().setRowSelectionInterval(0,0);
    }

    public static class CallLogStats{
        static LinkedList<Call> callsList;
        static CallStats SRCCallStats;
        static CallStats DSTCallStats;

        public static Stats[] getPopNumber(int srcOrDst,String number){
            if (Gui.callsList == null) return null;
            if ((callsList == null) || callsList != Gui.callsList) pullData();
            if (srcOrDst == Call.SRC) return SRCCallStats.popNumbers(number);
            else return DSTCallStats.popNumbers(number);
        }

        private static void pullData(){
            callsList = Gui.callsList;
            SRCCallStats = new CallStats(Call.SRC, callsList);
            DSTCallStats = new CallStats(Call.DST, callsList);
        }
    }
}

