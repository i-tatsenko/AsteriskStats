package Statistics;

public class Stats {
    private int duration;
    private int callsCount;
    private final int TARGET;
    private String targetNumber;

    Stats(int target, Call call){
        TARGET = Call.getTargetPair(target);
        addCall(call);
        if ((targetNumber != null)&&(!targetNumber.equals(call.getCall()[TARGET]))) System.err.println("Smthnig went wrong in Statss.class with target number assigning");
        targetNumber = call.getCall()[target];
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

    public String getTargetNumber(){
        return targetNumber;
    }

}
