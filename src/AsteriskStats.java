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


        CallStats stats = new CallStats(Call.DST, calls);
        stats.printStatsSortByCallsCount();
        System.out.println("Total amount of calls: " + Call.getCount());
        String intUser = "106";
        String popularDSTC[] = stats.popNumbers(intUser);
        System.out.println("The most popular numbers for " + intUser + " are:");
        for (String aPopularDSTC : popularDSTC) {
            if (!aPopularDSTC.equals("0:0"))
                System.out.println(User.checkName(aPopularDSTC.split(":")[0]) + " with " + aPopularDSTC.split(":")[1] + " calls");
        }


    }
}





