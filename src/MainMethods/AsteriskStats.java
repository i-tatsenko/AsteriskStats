package MainMethods;

import DataConnector.DbProcessor;
import GUI.CallsTablePanel;
import GUI.Gui;
import GUI.RunningSettingsPanel;
import Statistics.Call;
import Statistics.CallFilter;
import Statistics.CallStats;

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
        try{ UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e){}

        MainWindow = Gui.createGui();
        MainWindow.setVisible(true);
        updateInfo();
    }

    public static void updateInfo(){
        CallFilter cf = new CallFilter();
        String[] period = RunningSettingsPanel.getTimePeriod();
        cf.setCallsPeriod(Statistics.PeriodToString.interval(period[0], period[1]));
        setCallFilterSrcDst(cf);

        //cf.setDurationFilter(110);
        //cf.setOnlyNightCalls();
        //cf.setLastappFilter("Queue");

        ResultSet rs = DbProcessor.getConnector().outQuery("src, dst, lastapp, billsec, calldate", "cdr", cf.toString());
        LinkedList<Statistics.Call> calls = Statistics.Call.callsFabric(rs);
        MainWindow.setData(calls);
    }

    private static void setCallFilterSrcDst(CallFilter cf){
        RunningSettingsPanel.NumberComboBox comboSrc = RunningSettingsPanel.getCOMBO_SRC();
        RunningSettingsPanel.NumberComboBox comboDst = RunningSettingsPanel.getCOMBO_DST();

        if (!comboSrc.getPattern().equals("%%")) cf.setSrcFilter(comboSrc.getPattern());
        if (!comboDst.getPattern().equals("%%")) cf.setDstFilter(comboDst.getPattern());
    }


}