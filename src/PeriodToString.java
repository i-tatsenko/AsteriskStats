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
	
	public String toString(){
		String out = "calldate >= '" + this.DATE_FROM.get(Calendar.YEAR) + "-" + (this.DATE_FROM.get(Calendar.MONTH) + 1) + "-" + this.DATE_FROM.get(Calendar.DAY_OF_MONTH) + "'" + 
			       " AND calldate <= '" + this.DATE_TO.get(Calendar.YEAR) + "-" + (this.DATE_TO.get(Calendar.MONTH) + 1) + "-" + this.DATE_TO.get(Calendar.DAY_OF_MONTH) + "'";
		return out;
	}
	
	public String periodAnnotation(){
		return "Period generated: " + this.DATE_FROM.get(Calendar.YEAR) + "-" + (this.DATE_FROM.get(Calendar.MONTH) + 1) + "-" + this.DATE_FROM.get(Calendar.DAY_OF_MONTH) + 
				" - " + this.DATE_TO.get(Calendar.YEAR) + "-" + (this.DATE_TO.get(Calendar.MONTH) + 1) + "-" + this.DATE_TO.get(Calendar.DAY_OF_MONTH);
	}
}
