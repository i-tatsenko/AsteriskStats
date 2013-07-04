import java.util.HashMap;

/**
 *
 * User: docent
 * Date: 04.07.13
 * Time: 13:37
 */
public class CallStatsSrc extends CallStats {

    public CallStatsSrc(Call call, callField statsFor){
        super();
        callMap.put(call.DST, 1);
    }

    public void addCall(Call call){
        super.addCall(call);
        AsteriskStats.putStatDb(this.callMap, call.DST, 1);
    }
    public static String getCallMapKey(){
        return "SRC";
    }

    public static  void putStatDb(HashMap<String, CallStatsSrc> db, Call call){

        if (db.containsKey(call.SRC)){
            db.get(call.SRC).addCall(call);
        }else{
            db.put(call.SRC, new CallStatsSrc(call, callField.SRC));

        }
    }

}
