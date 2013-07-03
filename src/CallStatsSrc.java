import java.util.HashMap;

/**
 * Instance of this class represents statistics
 * for some number. Statistics contains number
 * of calls, total calls` duration, and Map of
 * this source`s destinations /  Map  of  this
 * destination`s sources
 *
 *
 * User: Docent
 * Date: 30.06.13
 * Time: 22:38
 */
public class CallStatsSrc {
    /**
     * statsFor can contain "SRC", orDST
     */
    private final callField statsFor;
    private long count = 0;
    private long duration = 0;

    /**
     * callMap is base of sources\destinations with number
     * of calls were made to this dst \ were made from this
     * source.
     */
    private HashMap<String, Integer> callMap;

    @SuppressWarnings("unused")
    private CallStatsSrc(){
        this.statsFor = null;

    }

    public CallStatsSrc(callField statsFor){
        this.callMap = new HashMap<String, Integer>();
        this.statsFor = statsFor;
    }

    public CallStatsSrc(Call call, callField statsFor){
        this.count++;
        this.duration = call.DURATION;
        this.callMap = new HashMap<String, Integer>();
        callMap.put(call.DST, 1);
        this.statsFor = statsFor;
    }

    public void addCall(Call call){
        this.count++;
        this.duration += call.DURATION;
        AsteriskStats.putStatDb(this.callMap, call.DST, 1);
    }

    public String[] popNumbers(){
        String[] out = {"0:0", "0:0", "0:0"};
        for (String key : callMap.keySet()){
            if (Integer.valueOf(out[0].split(":")[1]) < callMap.get(key)){
                out[2] = out[1];
                out[1] = out[0];
                out[0] = key + ":" + callMap.get(key);
            }else if (Integer.valueOf(out[1].split(":")[1]) < callMap.get(key)){
                out[2] = out[1];
                out[1] = key + ":" + callMap.get(key);
            }else if (Integer.valueOf(out[2].split(":")[1]) < callMap.get(key)){
                out[2] = key + ":" + callMap.get(key);
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

    public HashMap<String, Integer> getCallMap(){
        return this.callMap;
    }

}
