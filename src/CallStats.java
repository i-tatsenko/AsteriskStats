import java.util.*;

/**
 *
 *
 * User: Docent
 * Date: 30.06.13
 * Time: 22:38
 */
public  class CallStats {

    protected final int TARGET;
    protected final int TARGET_PAIR;
    protected TreeMap<String, TargetStats> targetDb = new TreeMap<String, TargetStats>();

    @SuppressWarnings("unused")
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

    public String[] popNumbers(String targetNumber){
        String[] out = {"0:0", "0:0", "0:0"};
        TargetStats targetBase = targetDb.get(targetNumber);
        for (String key : targetBase.getCallStats().keySet()){
            if (Integer.valueOf(out[0].split(":")[1]) < Integer.valueOf(targetBase.getCallStatsValue(key).getCallsCount())){
                out[2] = out[1];
                out[1] = out[0];
                out[0] = key + ":" + Integer.valueOf(targetBase.getCallStatsValue(key).getCallsCount());
            }else if (Integer.valueOf(out[1].split(":")[1]) < Integer.valueOf(targetBase.getCallStatsValue(key).getCallsCount())){
                out[2] = out[1];
                out[1] = key + ":" + Integer.valueOf(targetBase.getCallStatsValue(key).getCallsCount());
            }else if (Integer.valueOf(out[2].split(":")[1]) < Integer.valueOf(targetBase.getCallStatsValue(key).getCallsCount())){
                out[2] = key + ":" + Integer.valueOf(targetBase.getCallStatsValue(key).getCallsCount());
            }
        }

        return out;
    }



    public void printStatsSortByNumber(){
        for (String key:targetDb.keySet()){
            System.out.println(key + ": " + targetDb.get(key).getCallsCount());
        }
    }

    public void printStatsSortByCallsCount(){
        class temp implements Comparable<temp>{
            public int hashCode(){
                return Integer.valueOf(this.number);
            }
            int callsCount;
            String number;
              public int compareTo(temp c){
                  if (this.callsCount > c.callsCount) return 0;
                  else if (this.callsCount < c.callsCount) return 1;
                  else return -1;
              }
         private temp(){};
         temp(int cc, String nb){
             this.callsCount = cc;
             this.number = nb;
         }

        }


        TreeSet<temp> tempTree = new TreeSet<temp>();
        for (String key:targetDb.keySet()){
            System.out.print(tempTree.add(new temp(targetDb.get(key).getCallsCount(), key)));


        }

        for (temp tmp:tempTree){
            System.out.println(tmp.number + ": " + tmp.callsCount);
        }
    }

    public String toString(){
        return "This unit has " + targetDb.keySet().size() + " keys and " + targetDb.values().size() + " values";
    }


}
