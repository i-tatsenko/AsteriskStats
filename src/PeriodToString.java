import java.util.Calendar;

public class PeriodToString{
	private Calendar DATE_TO;
	private Calendar DATE_FROM;
	
	public String daysAgo(Integer days){
		this.DATE_TO = Calendar.getInstance();
		this.DATE_FROM = Calendar.getInstance();
		this.DATE_FROM.add(Calendar.DAY_OF_MONTH, -(days));
		
		return stringGenerate();
	}
	
	public String thisMonth(){
		this.DATE_TO = Calendar.getInstance();
		this.DATE_FROM = Calendar.getInstance();
		this.DATE_FROM.set(Calendar.DAY_OF_MONTH, 1);
		
		return stringGenerate();
	}
	
	public String month(Integer i){
		this.DATE_TO = Calendar.getInstance();
		this.DATE_FROM = Calendar.getInstance();
		this.DATE_TO.set(Calendar.MONTH, i-1);
		this.DATE_TO.set(Calendar.DAY_OF_MONTH, this.DATE_TO.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		this.DATE_FROM.set(Calendar.MONTH, i-1);
		this.DATE_FROM.set(Calendar.DAY_OF_MONTH, 1);
		
		return stringGenerate();
	}
	
	public String today(){
		this.DATE_FROM = Calendar.getInstance();
		this.DATE_TO = Calendar.getInstance();
		return stringGenerate();
	}
	
	private String stringGenerate(){
		String out = "calldate >= '" + this.DATE_FROM.get(Calendar.YEAR) + "-" + (this.DATE_FROM.get(Calendar.MONTH) + 1) + "-" + this.DATE_FROM.get(Calendar.DAY_OF_MONTH) + "'" + 
			       " AND calldate <= '" + this.DATE_TO.get(Calendar.YEAR) + "-" + (this.DATE_TO.get(Calendar.MONTH) + 1) + "-" + this.DATE_TO.get(Calendar.DAY_OF_MONTH) + "'";
		return out;
	}
	
	public String toString(){
		return "Period generated: " + this.DATE_FROM.get(Calendar.YEAR) + "-" + (this.DATE_FROM.get(Calendar.MONTH) + 1) + "-" + this.DATE_FROM.get(Calendar.DAY_OF_MONTH) + 
				" - " + this.DATE_TO.get(Calendar.YEAR) + "-" + (this.DATE_TO.get(Calendar.MONTH) + 1) + "-" + this.DATE_TO.get(Calendar.DAY_OF_MONTH);
	}
}
