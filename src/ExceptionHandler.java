import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;

import org.jdom2.JDOMException;

/**ExceptionHandler is in plans
 * 
 * @author docent
 *
 */
public class ExceptionHandler {
	public static void ErrorOutput(SQLException e, PrintStream out){
		out.println("SQLException: " + e.getMessage());
	    out.println("SQLState: " + e.getSQLState());
	    out.println("VendorError: " + e.getErrorCode());
	    
	}
	
	public static void ErrorOutput(JDOMException e, PrintStream out){
		out.println(e);
	}
	
	public static void ErrorOutput(IOException e, PrintStream out){
		out.println(e);
	}

}
