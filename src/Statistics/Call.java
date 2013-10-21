package Statistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import Errors.ErrHandler;

public class Call{


    private final long id;
    private static char lineEnterPrefix = '9';
    private String lastApp = "Dial";

    protected static long count = 0;
    protected String[] callArray;

	public static int SRC = 0;
	public static int DST = 1;
    public static int LASTAPP = 2;
	public static int DURATION = 3;
    public static int CALL_DATE = 4;




    protected Call(){ id = -1; callArray = null;}

    private static String removeLineEnterPrefix(String number){
        if (number.charAt(0) == lineEnterPrefix){return number.substring(1);}
        else return number;

    }

    public Call(String src, String dst, int duration, String calldate){
        this.callArray = new String[]{removeLineEnterPrefix(src), removeLineEnterPrefix(dst), lastApp, String.valueOf(duration), calldate};
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
                Call call = new Call(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getString(5));
                list.add(call);
            }
        }catch (SQLException e){
            ErrHandler.ErrorOutput(e, System.out);
        }

        return list;
    }

    public String[] getCall(){
        return callArray;
    }

    public static int getTargetPair(int target){
        if (target == SRC) return DST;
        if (target == DST) return SRC;
        return 0;
    }



	public String toString(){
        return  "SRC: " + this.callArray[SRC] + " DST: " + this.callArray[DST] + " TIME: " + callArray[DURATION] + " DIAL STATE: " + this.callArray[LASTAPP];
	}
}



