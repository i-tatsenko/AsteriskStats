import java.sql.SQLException;
import java.util.*;

/**
 *
 *
 * User: Docent
 * Date: 30.06.13
 * Time: 22:38
 */
@SuppressWarnings("unused")
public  class CallStats {
    private static HashMap<String, String> MobileCodes = new HashMap<String, String>();


    protected final int TARGET;
    protected final int TARGET_PAIR;
    protected TreeMap<String, TargetStats> targetDb = new TreeMap<String, TargetStats>();


    private CallStats(){
        this.TARGET = 0;
        this.TARGET_PAIR = 1;
    }

    public CallStats(int target){
        this.TARGET = target;
        this.TARGET_PAIR = Call.getTargetPair(TARGET);
    }

    public CallStats(int target, LinkedList<Call> calls){
        this.TARGET = target;
        this.TARGET_PAIR = Call.getTargetPair(TARGET);
        for (Call call:calls){
            addCall(call);
        }
    }



    public void addCall(Call call){
        if (targetDb.containsKey(call.getCall()[this.TARGET])){
            this.targetDb.get(call.getCall()[this.TARGET]).addTargetBase(call);
        }else{
            this.targetDb.put(call.getCall()[this.TARGET], new TargetStats(call, TARGET));
            //addCall(call);
        }

    }

    public String[] popNumbers(String targetNumber){                        //Change realisation!
        String[] out = {"0:0", "0:0", "0:0"};
        TargetStats targetBase = targetDb.get(targetNumber);
        if (targetBase == null) return out;
        for (String key : targetBase.getCallStats().keySet()){
            if (Integer.valueOf(out[0].split(":")[1]) < targetBase.getCallStatsValue(key).getCallsCount()){
                out[2] = out[1];
                out[1] = out[0];
                out[0] = key + ":" + targetBase.getCallStatsValue(key).getCallsCount();
            }else if (Integer.valueOf(out[1].split(":")[1]) < targetBase.getCallStatsValue(key).getCallsCount()){
                out[2] = out[1];
                out[1] = key + ":" + targetBase.getCallStatsValue(key).getCallsCount();
            }else if (Integer.valueOf(out[2].split(":")[1]) < targetBase.getCallStatsValue(key).getCallsCount()){
                out[2] = key + ":" + targetBase.getCallStatsValue(key).getCallsCount();
            }
        }

        return out;
    }


    @SuppressWarnings("unused")
    public void printStatsSortByNumber(){
        for (String key:targetDb.keySet()){
            System.out.println(key + ": " + targetDb.get(key).getCallsCount());
        }
    }

    public void printStatsSortByCallsCount() throws SQLException {
        TreeSet<TargetStats> tempTree = new TreeSet<TargetStats>();
        for (String key:targetDb.keySet()){
            tempTree.add(targetDb.get(key));
            targetDb.get(key).setSourceNumber(key);
        }

        for (TargetStats tmp:tempTree){
            System.out.println(tmp.getCallsCount() + ": \t" + (User.checkName(tmp.getSourceNumber())));
        }
    }

    static {
        MobileCodes.put("039", "Kyivstar");
        MobileCodes.put("067", "Kyivstar");
        MobileCodes.put("068", "Kyivstar");
        MobileCodes.put("096", "Kyivstar");
        MobileCodes.put("097", "Kyivstar");
        MobileCodes.put("098", "Kyivstar");

        MobileCodes.put("050", "MTC");
        MobileCodes.put("066", "MTC");
        MobileCodes.put("095", "MTC");
        MobileCodes.put("099", "MTC");

        MobileCodes.put("063", "Life:)");
        MobileCodes.put("093", "Life:)");

        MobileCodes.put("091", "Utel");
    }

    public void printMobileStatistics(LinkedList<Call> calls){
        HashMap<String, Integer> stats = new HashMap<String, Integer>();
        for (Call call:calls){
            String operator = MobileCodes.get(call.getCall()[2].substring(0,2));
            if (stats.containsKey(operator)){
                stats.put(operator, Integer.valueOf(MobileCodes.get(operator)) + Integer.valueOf(call.getCall()[4]));
            }
            else stats.put(operator, Integer.valueOf(call.getCall()[4]));
        }
    }


    public String toString(){
        return "This unit has " + targetDb.keySet().size() + " keys and " + targetDb.values().size() + " values";
    }


}
