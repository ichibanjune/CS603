//Minxuan Zhao HW6

/**
 * Defines faculty members by id, name, title, and faculty type
 */
public class Faculty {

	private String id; // unique id
	private String first; // first name
	private String last; // last name
	private FacultyType type; // tenure track or adjunct

	/**
	 * 4-arg constructor
	 */
	public Faculty(String id, String first, String last, FacultyType type) {
		this.id = id;
		this.first = first;
		this.last = last;
		this.type = type;
	}

	// mutator method
	public void setType(FacultyType type) {
		this.type = type;
	}

	// accessor methods
	public String getId() {
		return this.id;
	}

	public String getFirst() {
		return this.first;
	}

	public String getLast() {
		return this.last;
	}

	public FacultyType getFacultyType() {
		return this.type;
	}
	
	//1.  the instance method that overrides the inherited toString() method and 
	//return a string with the id, last name, first name, title, and the tenure/union member status of the invoking faculty
	public String toString() {
		return "ID: " + this.id + "Name: " + this.last + ", " + this.first + " " + this.type.toString();
	}

	public static void main(String[] args) {
		// Some testing code to get you started
		FacultyType type = new TenureTrack("Associate Professor", true);
		Faculty faculty = new Faculty("1234", "Jane", "Smith", type);
		System.out.println(faculty);
		System.out.println(faculty.getFacultyType().promote());
		System.out.println(faculty);
		
		//test for Adjunct faculty
		FacultyType type1 = new Adjunct(true);
		Faculty faculty1 = new Faculty("1235", "Kunimizu", "Tetsuka", type1);
		System.out.println("\n" + faculty1);//union member
		System.out.println(faculty1.getFacultyType().promote());//return false
		System.out.println(faculty1);//no change

		FacultyType type2 = new Adjunct(false);
		Faculty faculty2 = new Faculty("1236", "Syusuke", "Fuji", type2);
		System.out.println("\n" + faculty2);//non-union
		System.out.println(faculty2.getFacultyType().promote());//return false
		System.out.println(faculty2);//no change

		FacultyType type3 = new Adjunct();
		Faculty faculty3 = new Faculty("1237", "Eji", "Kikumaru", type3);
		System.out.println("\n" + faculty3);//union member
		System.out.println(faculty3.getFacultyType().promote());//return false
		System.out.println(faculty3);//no change

		FacultyType type4 = new TenureTrack("Assistant Professor", false);
		Faculty faculty4 = new Faculty("1238", "Yushi", "Oshitari", type4);
		System.out.println("\n" + faculty4);//not tenured
		System.out.println(faculty4.getFacultyType().promote());//return true
		System.out.println(faculty4);//Associate professor, tenured
		
		FacultyType type5 = new TenureTrack("Associate Professor", false);
		Faculty faculty5 = new Faculty("1239", "Ryoma", "Echizan", type5);
		System.out.println("\n" + faculty5);//not tenured
		System.out.println(faculty5.getFacultyType().promote());//return true
		System.out.println(faculty5);//Full professor, tenured

		FacultyType type6 = new TenureTrack("Full Professor", true);
		Faculty faculty6 = new Faculty("1000", "Keigo", "Atobe", type6);
		System.out.println("\n" + faculty6);// tenured
		System.out.println(faculty6.getFacultyType().promote());//return false
		System.out.println(faculty6);//Full professor, tenured

	}

}