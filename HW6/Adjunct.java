//Minxuan Zhao HW6

/**
* Adjunct class inherits from FacultyType.  
* Faculty who are adjuncts are not eligible for promotion or tenure.
* Their title is always lecturer. 
*/

public class Adjunct extends FacultyType {
	private boolean unionMember;

	public boolean isUnionMember() {
		return unionMember;
	}


	public void setUnionMember(boolean unionMember) {
		this.unionMember = unionMember;
	}

	
	/** An adjunct can't be promoted */
	public boolean promote() {
		return false;
	}
	
	//1.   1-arg constructor
	//one parameter of type boolean showing the union member status of the invoking adjunct object
	public Adjunct(boolean unionMember) {
		super("Lecturer");
		this.unionMember = unionMember;
	}

	//2.  0-arg constructor
	//for adjunct object type union member
	public Adjunct() {
		super("Lecturer");
		this.unionMember = true;
	}
	
	//override toString method and return a string 
	//showing the title of invoking adjunct object, which is always "Lecturer",
	//and the union member status
	public String toString() {
		if(this.unionMember) {
			return "Title: Lecturer  Status: union member";
		} else {
			return "Title: Lecturer  Status: non-union";
		}
	}

	public static void main(String[] args) {
		//test codes for all three possible situations
		Adjunct m1 = new Adjunct(true);
		Adjunct m2 = new Adjunct(false);
		Adjunct m3 = new Adjunct();
		
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
	}
}
