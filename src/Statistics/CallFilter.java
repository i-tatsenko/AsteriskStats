package Statistics;

public class CallFilter extends Call {
	PeriodToString callsPeriod = null;
    private int onlyNightCalls = 0;

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

    public void setOnlyNightCalls(){
        this.onlyNightCalls = 1;
    }



	public void setCallsPeriod(PeriodToString period){
		this.callsPeriod = period;
	}
	
	public String toString(){
		String temp = "";
		String and = "";
		if (callArray[Call.SRC] != null){
			temp += "src LIKE '" + callArray[Call.SRC] + "'";
			and = " AND ";
		}
		if (callArray[Call.DST] != null){
			temp += and + "dst LIKE '" + callArray[Call.DST] + "'";
			and = " AND ";
		
		}
		if (!callArray[Call.DURATION].equals("0")){
			temp += and + "duration >= '" + callArray[Call.DST] + "'";
			and = " AND ";
		}
		if (this.callsPeriod != null){
			temp += and + this.callsPeriod;
            and = " AND ";
		}

        if (callArray[Call.LASTAPP] != null){
            temp += and + "lastapp = '" + callArray[Call.LASTAPP] + "'";
        }

        switch (onlyNightCalls) {
            case 1:{
                return "(" + temp + ")" + " AND ( ((calldate REGEXP \"19:..:..$\") OR (calldate REGEXP \"2[0-4]:..:..$\")) OR (calldate REGEXP \"0[0-8]:..:..$\") )";
            }
            default: return temp;
        }
		

	}

}
