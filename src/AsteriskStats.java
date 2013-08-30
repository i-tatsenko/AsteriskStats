import java.io.*;
import java.util.*;
import java.sql.SQLException;
import java.sql.ResultSet;



public class AsteriskStats {

    /**
     *
     *
     * @param db
     * @param key
     * @param value - it`s means that value can be number of calls,
     *                of call duration
     */
    public static void putStatDb(HashMap<String, Integer> db, String key, Integer value){
        if (db.containsKey(key)){
            db.put(key, db.get(key) + value);
        }else{
            db.put(key, value);
        }

    }







	public static boolean hashToCsv(HashMap<String, Integer> db, String file_name, String head){
		try{
			PrintWriter file = new PrintWriter(new File(file_name));
			file.println(head);
			for (String keys:db.keySet()){
				file.println(keys + ", " + db.get(keys)/60 + ", minutes\"");
				
			}
			file.close();
			return true;	
		}catch (IOException e){
			System.out.println(e);
		}
		
		return false;
	}
	

	
	
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

        CallFilter cf = new CallFilter();
		//cf.setDstFilter("%_________");
		cf.setCallsPeriod(PeriodToString.thisMonth());
        //cf.setDurationFilter(110);
        cf.setOnlyNightCalls();

		DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"));
        if (dbProc == null){
            System.err.println("There is no settings file: " + System.getProperty("user.home") + "/.AsteriskStats/settings.xml");
            System.err.println("Program shutdown.");
            System.err.println("Operation system: " + System.getProperties().getProperty("os.name"));
            System.exit(0);
        }

        ResultSet rs = dbProc.outQuery("src, dst, lastapp, duration", "cdr", cf.toString());

        LinkedList<Call> calls = Call.callsFabric(rs);


        CallStats stats = new CallStats(Call.SRC, calls);
        stats.printStatsSortByCallsCount();
        System.out.println("Total amount of calls: " + Call.getCount());
        String popularDSTC[] = stats.popNumbers("137");
        System.out.println("The most popular numbers are:");
        for (String aPopularDSTC : popularDSTC) {
            if (!aPopularDSTC.equals("0:0"))
                System.out.println(aPopularDSTC.split(":")[0] + " with " + aPopularDSTC.split(":")[1] + " calls");
        }

    }
}





