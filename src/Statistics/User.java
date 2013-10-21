package Statistics;

import DataConnector.DbProcessor;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeSet;

import Errors.ErrHandler;



@SuppressWarnings("unused")
public class User implements Comparable<User> {
    private int number;
    private String name;

    private static HashMap<Integer, String> users = new HashMap<Integer, String>();
    static {
        try {
            pullAsteriskUsers();
        }catch (SQLException e){
            ErrHandler.SQLError(e);
        }
    }

    private User(){}

    public User(int number, String name){
        this.number = number;
        this.name = name;
    }


    public static void pullAsteriskUsers() throws SQLException {
        DbProcessor dbProc = DbProcessor.getDbProcessor(new File(System.getProperty("user.home") + "/.AsteriskStats/settings.xml"), "asterisk");
        if (dbProc == null) ErrHandler.fileMissing(System.getProperty("user.home") + "/.AsteriskStats/settings.xml");
        ResultSet rs = dbProc.outQuery("extension, name", "users");

        while(rs.next()){
            users.put(rs.getInt(1), rs.getString(2));
        }
    }

    public static TreeSet<User> getUsers(){
        TreeSet<User> usersSet = new TreeSet<User>();
        for (int number:users.keySet()){
            usersSet.add(new User(number, users.get(number)));
        }
        return usersSet;
    }

    public static String checkName(String number){
        if ((number == null) || (number.equals("Anonymous")) || number.length() > 9) return number;
        if (users.keySet().contains(Integer.valueOf(number))) return users.get(Integer.valueOf(number)) + "<" + number +">";
        return number;
    }

    public int compareTo(User user){
        if (this.number > user.number) return 1;
        if (this.number < user.number) return -1;
        return 0;
    }

    public String getNumber(){
        return String.valueOf(number);
    }

    public String getName(){
        return this.name;
    }

}
