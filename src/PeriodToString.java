import java.util.Calendar;

public class PeriodToString{
	private Calendar DATE_TO;
	private Calendar DATE_FROM;
	
	private PeriodToString(){
		
	}
	
	public static PeriodToString daysAgo(Integer days){
		PeriodToString period = new PeriodToString();
		period.DATE_TO = Calendar.getInstance();
		period.DATE_FROM = Calendar.getInstance();
		period.DATE_FROM.add(Calendar.DAY_OF_MONTH, -(days));
		
		return period;
	}
	
	public static PeriodToString thisMonth(){
		PeriodToString period = new PeriodToString();
		period.DATE_TO = Calendar.getInstance();
		period.DATE_FROM = Calendar.getInstance();
		period.DATE_FROM.set(Calendar.DAY_OF_MONTH, 1);
		
		return period;
	}
	
	public static PeriodToString month(Integer i){
		PeriodToString period = new PeriodToString();
		period.DATE_TO = Calendar.getInstance();
		period.DATE_FROM = Calendar.getInstance();
		period.DATE_TO.set(Calendar.MONTH, i-1);
		period.DATE_TO.set(Calendar.DAY_OF_MONTH, period.DATE_TO.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		period.DATE_FROM.set(Calendar.MONTH, i-1);
		period.DATE_FROM.set(Calendar.DAY_OF_MONTH, 1);
		
		return period;
	}
	
	public static PeriodToString today(){
		PeriodToString period = new PeriodToString();
		period.DATE_FROM = Calendar.getInstance();
		period.DATE_TO = Calendar.getInstance();
		return period;
	}

    /**
     * Date format is YYYY-MM-DD
     * @param from
     * @param to
     * @return
     */
    public static PeriodToString interval(String from, String to){
        PeriodToString period = new PeriodToString();
        period.DATE_FROM = Calendar.getInstance();
        period.DATE_TO = Calendar.getInstance();

        String[] fromArray = from.split("-");

        period.DATE_FROM.set(Calendar.YEAR, Integer.valueOf(fromArray[0]));
        period.DATE_FROM.set(Calendar.MONTH, Integer.valueOf(fromArray[1]) - 1);
        period.DATE_FROM.set(Calendar.DAY_OF_MONTH, Integer.valueOf(fromArray[2]));

        String [] toArray = to.split("-");
        period.DATE_TO.set(Calendar.YEAR, Integer.valueOf(toArray[0]));
        period.DATE_TO.set(Calendar.MONTH, Integer.valueOf(toArray[1]) - 1);
        period.DATE_TO.set(Calendar.DAY_OF_MONTH, Integer.valueOf(toArray[2]));
        return period;
    }
	
	public String toString(){
        return "calldate >= '" + this.DATE_FROM.get(Calendar.YEAR) + "-" + (this.DATE_FROM.get(Calendar.MONTH) + 1) + "-" + this.DATE_FROM.get(Calendar.DAY_OF_MONTH) + "'" +
			       " AND calldate <= '" + this.DATE_TO.get(Calendar.YEAR) + "-" + (this.DATE_TO.get(Calendar.MONTH) + 1) + "-" + this.DATE_TO.get(Calendar.DAY_OF_MONTH) + "'";

	}
	
	public String periodAnnotation(){
		return "Period generated: " + this.DATE_FROM.get(Calendar.YEAR) + "-" + (this.DATE_FROM.get(Calendar.MONTH) + 1) + "-" + this.DATE_FROM.get(Calendar.DAY_OF_MONTH) + 
				" - " + this.DATE_TO.get(Calendar.YEAR) + "-" + (this.DATE_TO.get(Calendar.MONTH) + 1) + "-" + this.DATE_TO.get(Calendar.DAY_OF_MONTH);
	}
}
