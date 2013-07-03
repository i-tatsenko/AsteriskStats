import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Call{

    protected static long count = 0;
    private final long id;
    private static char lineEnterPrefix = '9';

	public String SRC;
	public String DST;
	public int DURATION;
	public String LASTAPP;

    protected Call(){ id = -1;}

    private static String removeLineEnterPrefix(String number){
        if (number.charAt(0) == lineEnterPrefix){return number.substring(1);}
        else return number;

    }

    public Call(String src, String dst, int duration){
        this.SRC = removeLineEnterPrefix(src);
        this.DST = removeLineEnterPrefix(dst);
        this.DURATION = duration;
        this.id = ++count;
    }


    public static long getCount(){
        return count;
    }


    public long getId(){
        return this.id;
    }

    public static LinkedList<Call> callsFabric(ResultSet rs){
        LinkedList<Call> list = new LinkedList<Call>();
        try {
            while (rs.next()){
                Call call = new Call(rs.getString(1), rs.getString(2), rs.getInt(3));
                list.add(call);
            }
        }catch (SQLException e){
            ExceptionHandler.ErrorOutput(e, System.out);
        }

        return list;
    }


	public String toString(){
        return  "SRC: " + this.SRC + " DST: " + this.DST + " DIAL STATE: " + this.LASTAPP;
	}
}



