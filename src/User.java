import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: docent
 * Date: 02.09.13
 * Time: 11:56
 */
@SuppressWarnings("unused")
public class User {
    private static HashMap<Integer, String> users = new HashMap<Integer, String>();
    static {
        try {
            pullAsteriskUsers();
        }catch (SQLException e){
            ErrHandler.SQLError(e);
        }
    }

    private User(){}


    public static void pullAsteriskUsers() throws SQLException {
        DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"), "asterisk");
        if (dbProc == null) ErrHandler.fileMissing(System.getProperty("user.home") + "/.AsteriskStats/settings.xml");
        ResultSet rs = dbProc.outQuery("extension, name", "users");

        while(rs.next()){
            users.put(rs.getInt(1), rs.getString(2));
        }
    }

    public static HashMap<Integer, String> getUsers(){
        return users;
    }

    public static String checkName(String number){
        if (users.keySet().contains(Integer.valueOf(number))) return users.get(Integer.valueOf(number)) + "<" + number +">";
        return number;
    }


}
