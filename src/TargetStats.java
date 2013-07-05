import java.lang.annotation.Target;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: docent
 * Date: 05.07.13
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
public class TargetStats {
    private int duration;
    private int callsCount;
    private final int TARGET;
    private boolean dbCreated = false;
    private HashMap<String, Stats> targetBase = new HashMap<String, Stats>();

    public TargetStats(Call call, int target){
        TARGET = Call.getTargetPair(target);
        addCall(call);
        addTargetBase(call);

    }


    public void addTargetBase(Call call){
        if (targetBase.containsKey(call.getCall()[Call.getTargetPair(TARGET)])){
            targetBase.get(call.getCall()[Call.getTargetPair(TARGET)]).addCall(call);
            addCall(call);

        }else {
            targetBase.put(call.getCall()[Call.getTargetPair(TARGET)], new Stats(Call.getTargetPair(TARGET), call));
            addTargetBase(call);
        }

    }

    public Stats getCallStatsValue(String key){
        return targetBase.get(key);
    }

    public HashMap<String, Stats> getCallStats(){
        return targetBase;
    }

    protected void addCall(Call call){
        callsCount++;
        duration += Integer.valueOf(call.getCall()[Call.DURATION]);
    }

    public int getDuration(){
        return duration;
    }

    public int getCallsCount(){
        return callsCount;
    }



}
