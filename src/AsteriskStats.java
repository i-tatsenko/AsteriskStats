import java.io.*;
import java.util.*;
import java.sql.SQLException;
import java.sql.ResultSet;


@SuppressWarnings("unused")
public class AsteriskStats {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

        CallFilter cf = new CallFilter();
        cf.setSrcFilter("1__");
        //cf.setLastappFilter("Queue");
		//cf.setDstFilter("333");
		cf.setCallsPeriod(PeriodToString.thisMonth());
        //cf.setDurationFilter(110);
        //cf.setOnlyNightCalls();

		DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"), "asteriskcdrdb");
        if (dbProc == null) ErrHandler.fileMissing(System.getProperty("user.home") + "/.AsteriskStats/settings.xml");


        ResultSet rs = dbProc.outQuery("src, dst, lastapp, duration", "cdr", cf.toString());
        LinkedList<Call> calls = Call.callsFabric(rs);


        CallStats stats = new CallStats(Call.SRC, calls);
        stats.printStatsSortByCallsCount();
        System.out.println("Total amount of calls: " + Call.getCount());
        String intUser = "130";
        TreeMap<Stats, String> popularDSTC = new TreeMap<Stats, String>();
        for (String number:stats.targetDb.get(intUser).getCallStats().keySet()){
            popularDSTC.put(stats.targetDb.get(intUser).getCallStats().get(number), number);
        }
        Stats[] popularDSTCArray = popularDSTC.keySet().toArray(new Stats[0]);
        System.out.println("The most popular numbers for " + User.checkName(intUser) + " are:");
        Stats tempStats;
                                                                                                System.out.println(popularDSTC.keySet());
        for (int i = 1; i < popularDSTCArray.length; i++){
            tempStats = popularDSTCArray[popularDSTCArray.length - i];
            System.out.println(User.checkName(popularDSTC.get(tempStats)) + " with " + tempStats.getCallsCount() + " calls");
            if (User.checkName(popularDSTC.get(tempStats)) == null) System.out.println(tempStats);
        }


    }
}





