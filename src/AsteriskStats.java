import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class AsteriskStats {
		
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
	
	public static void resSetToHash(ResultSet rs, HashMap<String, Integer> db){
		try {
			Call call = new Call();
		while (rs.next()){
			call.SRC = rs.getString(2);
			call.DST = rs.getString(1);
			call.DURATION = rs.getInt(3);
			if (db.containsKey(call.SRC)){
					db.put(call.SRC, db.get(call.SRC) + call.DURATION);
				}else{
					db.put(call.SRC, call.DURATION);
				}
			
		}
		}catch (SQLException e){
			ExceptionHandler.ErrorOutput(e, System.out);
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		/*Connection conn = getMysqlConnect("docent","31415Docent");
		PeriodToString period = new PeriodToString();
		
		String sql_querry = "SELECT * FROM cdr WHERE " + period.thisMonth();
		System.out.print(sql_querry);
		ResultSet rs = commitStatement(conn, sql_querry);
		
		HashMap<String, Integer> db = new HashMap<String, Integer>();
		resSetToHash(rs, db);
		
		conn.close();
		
		System.out.print(hashToCsv(db, "X:\\1.txt", "Statistics for " + period + " SQL query to base was: " + sql_querry + "\""));
		
		for (String keys:db.keySet()){
			System.out.println(keys + ": " + db.get(keys)/60 + " minutes");
		}*/
		
		CallFilter cf = new CallFilter();
		cf.setDstFilter("[90]________%");
		cf.setSrcFilter("1__");
		cf.setDurationFilter(10);
		cf.setCallsPeriod(PeriodToString.today());
		
		DbProcessor dbProc = new DbProcessor(new File(System.getenv("HOME") + "/.AsteriskStats/settings.xml"));
		ResultSet rs = dbProc.outQuery("dst, src, duration", "cdr", cf.toString());
		
		HashMap<String, Integer> db = new HashMap<String, Integer>();
		resSetToHash(rs, db);
		for (String keys:db.keySet()){
			System.out.println(keys + ": " + db.get(keys)/60 + " minutes");
		}

	}
}




