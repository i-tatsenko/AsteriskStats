package MainMethods;

import DataConnector.DbProcessor;
import GUI.CallsTablePanel;
import GUI.Gui;
import GUI.RunningSettingsPanel;
import Statistics.CallFilter;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;


@SuppressWarnings("unused")
public class AsteriskStats {
    public static Gui MainWindow;
	public static void main(String[] args) throws SQLException {
       // Statistics.CallStats stats = new Statistics.CallStats(Statistics.Call.SRC, calls);
        //stats.printStatsSortByCallsCount();

        try{ UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e){}

        MainWindow = Gui.createGui();
        MainWindow.setVisible(true);
        updateInfo();
    }

    public static void updateInfo(){
        CallFilter cf = new CallFilter();
        cf.setSrcFilter("1__");
        String[] period = getTimePeriod();
        cf.setCallsPeriod(Statistics.PeriodToString.interval(period[0], period[1]));
        //cf.setDurationFilter(110);
        //cf.setOnlyNightCalls();
        //cf.setLastappFilter("Queue");
        //cf.setDstFilter("333");

        ResultSet rs = DbProcessor.getConnector().outQuery("src, dst, lastapp, billsec, calldate", "cdr", cf.toString());
        LinkedList<Statistics.Call> calls = Statistics.Call.callsFabric(rs);
        MainWindow.setData(null, calls);

    }

    private static String[] getTimePeriod(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String[] out = new String[2];
        if (RunningSettingsPanel.getFromCheckBoxState())out[0] = dateFormat.format(RunningSettingsPanel.getFromDate());
        else out[0] = "2013-01-01";

        if (RunningSettingsPanel.getToCheckBoxState())out[1] = dateFormat.format(RunningSettingsPanel.getToDate());
        else {
            Calendar dateTo = Calendar.getInstance();
            dateTo.add(Calendar.DAY_OF_MONTH, 1);
            out[1] = dateFormat.format(dateTo.getTime());
        }
        return out;
    }

}





