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
			zvonok call = new zvonok();
		while (rs.next()){
			call.SRC = rs.getString(3);
			call.DST = rs.getString(4);
			call.LASTAPP = rs.getString(8);
			call.DURATION = rs.getInt(10);
			if ((call.SRC.length() == 3) && (call.LASTAPP.equals("Dial")) && (call.SRC.charAt(0)=='1') && (call.DST.length()>8) ){ 
				// �������: ����� ��������� ������ 3 �����(������ �� �����); ������="Dial" - ����� ������, ����� �����������; ����� ��������� ���������� � 1 (��� ����);
				//�����, ���� ������, �������� ������ 8 ������ (���������� ������ ���������)
				if (db.containsKey(call.SRC)){
					db.put(call.SRC, db.get(call.SRC) + call.DURATION);
				}else{
					db.put(call.SRC, call.DURATION);
				}
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
		
		DbProcessor dbProc = new DbProcessor(new File(System.getenv("HOME") + "/settings.xml"));
	}
}

class zvonok{
	String SRC;
	String DST;
	int DURATION;
	String LASTAPP;
	
	public String toString(){
		String out = "SRC: " + this.SRC + " DST: " + this.DST + " DIAL STATE: " + this.LASTAPP; 
		return out;
	}
}


