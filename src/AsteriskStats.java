import DataConnector.DbProcessor;
import Errors.ErrHandler;
import GUI.Gui;
import Statistics.Stats;
import Statistics.User;
import com.sun.org.glassfish.external.statistics.Statistic;

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
		cf.setCallsPeriod(Statistics.PeriodToString.daysAgo(1));
        //cf.setDurationFilter(110);
        //cf.setOnlyNightCalls();

		DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"), "asteriskcdrdb");
        if (dbProc == null) ErrHandler.fileMissing(System.getProperty("user.home") + "/.AsteriskStats/settings.xml");
        ResultSet rs = dbProc.outQuery("src, dst, lastapp, duration", "cdr", cf.toString());
        LinkedList<Statistics.Call> calls = Statistics.Call.callsFabric(rs);

        Statistics.CallStats stats = new Statistics.CallStats(Statistics.Call.SRC, calls);
        //stats.printStatsSortByCallsCount();

        System.out.println("Total amount of calls: " + Statistics.Call.getCount());
        String intUser = "103";
        System.out.println(User.checkName(intUser) + " called:");
        Stats[] popNumbers = stats.popNumbers(intUser);

        Gui interlico = Gui.createGui();
        interlico.setVisible(true);
    }

}





