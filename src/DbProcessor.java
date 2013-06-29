import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class DbProcessor {
	
	private String host;
	private String db;
	private String dbUser;
	private String dbPassword;
	
	@SuppressWarnings("unused")
	private DbProcessor(){
	}
	
	public DbProcessor(File settingsXMLFile){
		SAXBuilder builder = new SAXBuilder();
		try{
			Document document = builder.build(settingsXMLFile);
			Element rootNode = document.getRootElement();
			List<Element> sqlList = rootNode.getChildren("sqlserver");
			Element sqlNode = (Element)sqlList.get(0);
			this.host = sqlNode.getChildText("host");
			this.db = sqlNode.getChildText("db");
			this.dbUser = sqlNode.getChildText("user");
			this.dbPassword = sqlNode.getChildText("password");
			
			
		}catch (JDOMException e){
			ExceptionHandler.ErrorOutput(e, System.out);
		}catch (IOException e){
			ExceptionHandler.ErrorOutput(e, System.out);
		}
	}
	
	
	private Connection getMysqlConnect(){
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.db + "?" + "user=" + this.dbUser + "&password=" + this.dbPassword);
		}catch (SQLException e){
			
		}
		System.out.print("Connection established");
		return conn;
	}
	
	public  ResultSet outQuery(String columns, String baseName, String filter){
		try{
			return getMysqlConnect().createStatement().executeQuery("SELECT " + columns +  
																	" FROM " +	baseName + 
																	" WHERE" + filter);
			
		}catch (SQLException e){
			ExceptionHandler.ErrorOutput(e, System.out); 
		}
		return null;
	}
	

}
