
public class CallFilter extends Call{
	PeriodToString callsPeriod = null;

    public CallFilter(){
		callArray = new String[]{null, null, "Dial", "0"};
	}

    public CallFilter(String src){
        callArray = new String[]{src, null, "Dial", "0"};
    }

    public CallFilter(String src, String dst){
        callArray = new String[]{src, dst, "Dial", "0"};
    }

    public CallFilter(String src, String dst, String lastapp){
        callArray = new String[]{src, dst, lastapp, "0"};
    }

    public CallFilter(String src, String dst, String lastapp, int duration){
        callArray = new String[]{src, dst, lastapp, String.valueOf(duration)};
    }

    public CallFilter(String src, String dst, String lastapp, int duration, PeriodToString period){
        callArray = new String[]{src, dst, lastapp, String.valueOf(duration)};
        this.callsPeriod = period;
    }


	
	public void setSrcFilter(String filter){
		callArray[Call.SRC] = filter;
	}
	
	public void setDstFilter(String filter){
        callArray[Call.DST] = filter;
	}
	
	public void setDurationFilter(int filter){
        callArray[Call.DURATION] = String.valueOf(filter);
	}
	
	public void setLastappFilter(String filter){
		callArray[Call.LASTAPP] = filter;
	}


	public void setCallsPeriod(PeriodToString period){
		this.callsPeriod = period;
	}
	
	public String toString(){
		String temp = "";
		String and = "";
		if (callArray[SRC] != null){
			temp += "src LIKE '" + callArray[SRC] + "'";
			and = " AND ";
		}
		if (callArray[DST] != null){
			temp += and + "dst LIKE '" + callArray[DST] + "'";
			and = " AND ";
		
		}
		if (!callArray[DURATION].equals("0")){
			temp += and + "duration >= '" + callArray[DST] + "'";
			and = " AND ";
		}
		if (this.callsPeriod != null){
			temp += and + this.callsPeriod;
		}

        if (callArray[LASTAPP] != null){
            temp += and + "lastapp = '" + callArray[LASTAPP] + "'";
        }
		
		return temp;
	}

}
