import java.io.*;
import java.util.*;
import java.sql.SQLException;
import java.sql.ResultSet;



public class AsteriskStats {

    public static void putStatDb(HashMap<String, Integer> db, String key, Integer value){
        if (db.containsKey(key)){
            db.put(key, db.get(key) + value);
        }else{
            db.put(key, value);
        }

    }

    public static void putStatDb(HashMap<String, CallStatsSrc> db, Call value){
        if (db.containsKey(value.SRC)){
            db.get(value.SRC).addCall(value);
        }else{
            db.put(value.SRC, new CallStatsSrc(value));
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

		CallFilter cf = new CallFilter("1__");
		//cf.setDstFilter("%_________");
		cf.setCallsPeriod(PeriodToString.month(6));
        //cf.setDurationFilter(110);

		DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"));
        if (dbProc == null){
            System.err.println("There is no settings file: " + System.getProperty("user.home") + "/.AsteriskStats/settings.xml");
            System.err.println("Program shutdown.");
            System.err.println("Operation system: " + System.getProperties().getProperty("os.name"));
            System.exit(0);
        }

        ResultSet rs = dbProc.outQuery("src, dst, duration", "cdr", cf.toString());

        HashMap<String, CallStatsSrc> db = new HashMap<String, CallStatsSrc>();
        LinkedList<Call> calls = Call.callsFabric(rs);

        for (Call call:calls){
            putStatDb(db, call);
        }
        for (String keys:db.keySet()){
            System.out.println(keys + ": " + (float)db.get(keys).getDuration()/60 + " minutes");
        }

        System.out.println("Total amount of calls: " + Call.getCount());
        String popularDSTC[] = db.get("137").popNumbers();
        System.out.println("The most popular numbers are:");
        for (String aPopularDSTC : popularDSTC) {
            if (!aPopularDSTC.equals("0:0"))
                System.out.println(aPopularDSTC.split(":")[0] + " with " + aPopularDSTC.split(":")[1] + " calls");
        }

    }
}





