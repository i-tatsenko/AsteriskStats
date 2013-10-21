import DataConnector.DbProcessor;
import Errors.ErrHandler;
import GUI.Gui;
import GUI.RunningSettingsPanel;
import Statistics.Stats;
import Statistics.User;
import com.sun.org.glassfish.external.statistics.Statistic;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


@SuppressWarnings("unused")
public class AsteriskStats {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

        Statistics.CallFilter cf = new Statistics.CallFilter();
        cf.setSrcFilter("1__");
        //cf.setLastappFilter("Queue");
		//cf.setDstFilter("333");
		cf.setCallsPeriod(Statistics.PeriodToString.thisMonth());
        //cf.setDurationFilter(110);
        //cf.setOnlyNightCalls();

        ResultSet rs = DbProcessor.getConnector().outQuery("src, dst, lastapp, billsec, calldate", "cdr", cf.toString());
        LinkedList<Statistics.Call> calls = Statistics.Call.callsFabric(rs);

        Statistics.CallStats stats = new Statistics.CallStats(Statistics.Call.SRC, calls);
        //stats.printStatsSortByCallsCount();

        System.out.println("Total amount of calls: " + Statistics.Call.getCount());
        String intUser = "103";
        System.out.println(User.checkName(intUser) + " called:");
        Stats[] popNumbers = stats.popNumbers(intUser);

        try{ UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e){}

        Gui interlico = Gui.createGui();
        interlico.setVisible(true);
        interlico.setData(stats, calls);
        System.out.print(RunningSettingsPanel.getFromDate());
    }

}





