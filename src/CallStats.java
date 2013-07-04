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
public  class CallStats {
    /**
     * statsFor can contain "SRC", orDST
     */
    protected final callField statsFor;
    protected long count = 0;
    protected long duration = 0;

    /**
     * callMap is base of sources\destinations with number
     * of calls were made to this dst \ were made from this
     * source.
     */
    protected HashMap<String, Integer> callMap;

    @SuppressWarnings("unused")
    protected CallStats(){
        this.statsFor = null;

    }

    public CallStats(callField statsFor){
        this.callMap = new HashMap<String, Integer>();
        this.statsFor = statsFor;
    }

    public CallStats(Call call, callField statsFor){
        this.count++;
        this.duration = call.DURATION;
        this.callMap = new HashMap<String, Integer>();
        //callMap.put(call.DST, 1); move to children classes
        this.statsFor = statsFor;
    }

    public void addCall(Call call){
        this.count++;
        this.duration += call.DURATION;
        //AsteriskStats.putStatDb(this.callMap, call.DST, 1); move to children class
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

    public static <T extends CallStats> void putStatDb(HashMap<String, T> db, Call call){

        if (db.containsKey(call.SRC)){
            db.get(call.SRC).addCall(call);
        }else{
           db.put(call.SRC, new T());

        }
    }



}
