/**
 * Created with IntelliJ IDEA.
 * User: docent
 * Date: 05.07.13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class Stats implements Comparable<Stats>{
    private int duration;
    private int callsCount;
    private final int TARGET;

    Stats(int target, Call call){
        TARGET = Call.getTargetPair(target);
        addCall(call);
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

    public int compareTo(Stats tempStats){
        if (this.callsCount > tempStats.callsCount)return 1;
        else return -1;
    }

}
