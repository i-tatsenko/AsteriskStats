import GUI.Gui;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


@SuppressWarnings("unused")
public class AsteriskStats {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

        CallFilter cf = new CallFilter();
        cf.setSrcFilter("1__");
        //cf.setLastappFilter("Queue");
		//cf.setDstFilter("333");
		cf.setCallsPeriod(PeriodToString.daysAgo(1));
        //cf.setDurationFilter(110);
        //cf.setOnlyNightCalls();

		DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"), "asteriskcdrdb");
        if (dbProc == null) ErrHandler.fileMissing(System.getProperty("user.home") + "/.AsteriskStats/settings.xml");
        ResultSet rs = dbProc.outQuery("src, dst, lastapp, duration", "cdr", cf.toString());
        LinkedList<Call> calls = Call.callsFabric(rs);

        CallStats stats = new CallStats(Call.SRC, calls);
        //stats.printStatsSortByCallsCount();

        System.out.println("Total amount of calls: " + Call.getCount());
        String intUser = "103";
        System.out.println(User.checkName(intUser) + " called:");
        Stats[] popNumbers = stats.popNumbers(intUser);
        int currentIndex;
        for (int i = 1; i < 5; i++){
            currentIndex = popNumbers.length - i;
            if (i > popNumbers.length)break;
            System.out.println(popNumbers[currentIndex].getCallsCount() + " \t times to: " + User.checkName(popNumbers[currentIndex].getTargetNumber()));
        }
        Gui interlico = Gui.createGui();
        interlico.setVisible(true);
    }

}





