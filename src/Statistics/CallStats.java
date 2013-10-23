package Statistics;

import java.sql.SQLException;
import java.util.*;


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

    public Stats[] popNumbers(String targetNumber){
        try{
            Collection<Stats> popNubmersColl = targetDb.get(targetNumber).getCallStats().values();

        Stats[] out = new Stats[popNubmersColl.size()];
        int i = 0;
        for (Stats temp:popNubmersColl){
            out[i++] = temp;
        }
        sort(out);

        return out;
        }catch(NullPointerException e){
            System.err.print("There is no statistic for " + targetNumber);
        }
        return null;
    }

    private void sort(Stats[] array){
        Stats temp;
        boolean wasChanged = true;
        while (wasChanged){
            wasChanged = false;
            for (int i = 1;i < array.length;i++){
                if(array[i].getCallsCount() < array[i-1].getCallsCount()){
                    temp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = temp;
                    if (i > 2)i -= 2;
                    wasChanged = true;
                }
            }
        }
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
