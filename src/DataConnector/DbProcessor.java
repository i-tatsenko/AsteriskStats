package DataConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.IOException;

import Errors.ErrHandler;
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
	
	private DbProcessor(File settingsXMLFile, String dbName){
		SAXBuilder builder = new SAXBuilder();
		try{
			Document document = builder.build(settingsXMLFile);
			Element rootNode = document.getRootElement();
			List<Element> sqlList = rootNode.getChildren("sqlserver");
			Element sqlNode = sqlList.get(0);
            this.db = dbName;
			this.host = sqlNode.getChildText("host");
			this.dbUser = sqlNode.getChildText("user");
			this.dbPassword = sqlNode.getChildText("password");
			
			
		}catch (JDOMException e){
			ErrHandler.ErrorOutput(e, System.out);
		}catch (IOException e){
			ErrHandler.ErrorOutput(e, System.out);}
    }

    public static DbProcessor getDbProcessor(File settingsXMLFile, String dbName){
        if (settingsXMLFile.exists())return new DbProcessor(settingsXMLFile, dbName);
        else return null;
    }
	
	
	private Connection getMysqlConnect(){
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.db + "?" + "user=" + this.dbUser + "&password=" + this.dbPassword);
		}catch (SQLException e){
			 ErrHandler.ErrorOutput(e, System.out);
		}
		return conn;
	}
	
	public  ResultSet outQuery(String columns, String baseName, String filter){
		try{
            System.out.println("SELECT " + columns +
                    " FROM " +	baseName +
                    " WHERE " + filter);
			return getMysqlConnect().createStatement().executeQuery("SELECT " + columns +
																	" FROM " +	baseName +
																	" WHERE " + filter);

			
		}catch (SQLException e){
			ErrHandler.ErrorOutput(e, System.out);
		}
		return null;
	}

    public  ResultSet outQuery(String columns, String baseName){
        try{
            return getMysqlConnect().createStatement().executeQuery("SELECT " + columns +
                    " FROM " +	baseName);


        }catch (SQLException e){
            ErrHandler.ErrorOutput(e, System.out);
        }
        return null;
    }

    public static DbProcessor getConnector(){
        DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"), "asteriskcdrdb");
        if (dbProc == null) ErrHandler.fileMissing(System.getProperty("user.home") + "/.AsteriskStats/settings.xml");
        return dbProc;
    }
	

}
