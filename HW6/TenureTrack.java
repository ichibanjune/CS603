//Minxuan Zhao HW6

/**
* TenureTrack class inherits from FacultyType.  
* Tenure-track faculty can be tenured or untenured and can be promoted.
* Assistant professor is the lowest rank, full professor is the highest,
* and associate professor is in between. When an assistant professor is
* promoted to associate, s/he is automatically granted tenure (only 
* in this application!). A faculty member moves up one rank for each 
* promotion.
*/

public class TenureTrack extends FacultyType {
	boolean tenured;
	
	/*1.  2-arg constructor for TenureTrack
	 * first parameter is string and the second is a boolean*/
	public TenureTrack (String title, boolean tenured) {
		super(title);
		this.setTenured(tenured);
	}
	
	//2. the following instance method overrides the inherited abstract method and 
	//return a boolean of true if the faculty is promoted
	//or false if not
	public boolean promote() {
		boolean p = false;
		if (this.getTitle().equals("Assistant Professor")){
			this.tenured = true;
			super.setTitle("Associate Professor");
			p = true;
		} else if (this.getTitle().equals("Associate Professor")) {
			this.tenured = true;
			super.setTitle("Full Professor");
			p = true;
		}
		return p;
	}
	
	public void setTenured(boolean tenured) {
		this.tenured = tenured;
	}

	public boolean getTenured() { 
		return this.tenured; 
	}
	
	//3. overrides the inherited toString() method and return a string that 
	//contains the title and the tenure status of the invoking tenureTrack
	public String toString() {
		if (this.tenured) {
			return "Title: " + this.getTitle() + " Status: tenured";
		} else {
			return "Title: " + this.getTitle() + " Status: not tenured";
		}
		
	}
	

	public static void main(String[] args) {
		TenureTrack p1 = new TenureTrack("Assistant Professor", false);
		TenureTrack p2 = new TenureTrack("Associate Professor", true);
		TenureTrack p3 = new TenureTrack("Associate Professor", false);
		TenureTrack p4 = new TenureTrack("Full Professor", true);
		
		System.out.println(p1);//should return "Title: Assistant Professor Status: not tenured"
		p1.promote();//true
		System.out.println("After promote: " + p1); //should return "Title: Associate Professor Status: tenured"
		
		System.out.println(p2);//should return "Title: Associate Professor Status: tenured"
		p2.promote();//true
		System.out.println("After promote: " + p2); //should return "Title: Full Professor Status: tenured"
		
		System.out.println(p3);//should return "Title: Associate Professor Status: not tenured"
		p3.promote();//true
		System.out.println("After promote: " + p3); //should return "Title Associate Professor Status: tenured"
		
		System.out.println(p4);//should return "Title: Full Professor Status: tenured"
		p4.promote();//false
		System.out.println("After promote: " + p4); //should return "Title: Full Professor Status: tenured"

	}

}
