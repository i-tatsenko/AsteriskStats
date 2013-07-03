import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Instance of this class represents statistics
 * for some number. Statistics contains number
 * of calls, total calls` duration, and Map of
 * this source`s destinations.
 *
 *
 * User: Docent
 * Date: 30.06.13
 * Time: 22:38
 */
public class CallStatsSrc {
    private long count = 0;
    private long duration = 0;

    /**
     * dstDb is base of destinations with number
     * of calls were made to this dst.
     */
    private HashMap<String, Integer> dstDb;

    public CallStatsSrc(){
        this.dstDb = new HashMap<String, Integer>();
    }

    public CallStatsSrc(Call call){
        this.count++;
        this.duration = call.DURATION;
        this.dstDb = new HashMap<String, Integer>();
        dstDb.put(call.DST, 1);
    }

    public void addCall(Call call){
        this.count++;
        this.duration += call.DURATION;
        AsteriskStats.putStatDb(this.dstDb, call.DST, 1);
    }

    public String[] popNumbers(){
        String[] out = {"0:0", "0:0", "0:0"};
        for (String key : dstDb.keySet()){
            if (Integer.valueOf(out[0].split(":")[1]) < dstDb.get(key)){
                out[2] = out[1];
                out[1] = out[0];
                out[0] = key + ":" + dstDb.get(key);
            }else if (Integer.valueOf(out[1].split(":")[1]) < dstDb.get(key)){
                out[2] = out[1];
                out[1] = key + ":" + dstDb.get(key);
            }else if (Integer.valueOf(out[2].split(":")[1]) < dstDb.get(key)){
                out[2] = key + ":" + dstDb.get(key);
            }
        }

        return out;
    }

    public long getCount(){
        return this.count;
    }

    public long getDuration(){
        return this.duration;
    }

    public HashMap<String, Integer> getDstDb(){
        return this.dstDb;
    }

}
