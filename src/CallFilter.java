
public class CallFilter extends Call{
	PeriodToString callsPeriod = null;
	public CallFilter(){
		this.SRC = null;
		this.DST = null;
		this.LASTAPP = "Dial";
		this.DURATION = 0;
	}
	
	public void setSrcFilter(String filter){
		this.SRC = filter;
	}
	
	public void setDstFilter(String filter){
		this.DST = filter;
	}
	
	public void setDurationFilter(int filter){
		this.DURATION = filter;
	}
	
	public void setLastappFilter(String filter){
		this.LASTAPP = filter;
	}
	
	public void setCallsPeriod(PeriodToString period){
		this.callsPeriod = period;
	}
	
	public String toString(){
		String temp = "";
		String and = "";
		if (this.SRC != null){ 
			temp += "src LIKE '" + this.SRC + "'";
			and = " AND ";
		}
		if (this.DST != null){ 
			temp += and + "dst LIKE '" + this.DST + "'";
			and = " AND ";
		
		}
		if (this.DURATION != 0){
			temp += and + "duration >= '" + this.DURATION + "'";
			and = " AND ";
		}
		if (this.callsPeriod != null){
			temp += and + this.callsPeriod;
		}
		temp += and + "lastapp = '" + this.LASTAPP + "'";
		
		return temp;
	}

}
