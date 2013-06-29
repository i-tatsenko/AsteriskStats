
public class Call{
	public String SRC;
	public String DST;
	public int DURATION;
	public String LASTAPP;
	
	public String toString(){
		String out = "SRC: " + this.SRC + " DST: " + this.DST + " DIAL STATE: " + this.LASTAPP; 
		return out;
	}
}

