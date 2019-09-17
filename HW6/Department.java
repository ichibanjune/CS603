//Minxuan Zhao HW6

import java.util.ArrayList;

/**
 * This class maintains a 'database' of faculty members in an ArrayList instance
 * variable for a department in a college. It provides functionality for finding
 * a faculty member in the department by id, adding new faculty, removing faculty,
 * and creating lists of faculty by type (tenure-track: all, with tenure, without
 * tenure, adjunct: all, union members, non-union).
 */

public class Department {
	private String name;
	private ArrayList<Faculty> facultyList; // department faculty

	public Department(String name) {
		this.name = name;
		this.facultyList = new ArrayList<Faculty>();
	}

	// accessor method for name instance variable
	public String getName(){ return this.name; }

	/** accessor method for facultyList instance variable that
	     returns the list as an array (for use in GUI interface) */
	public Faculty[] getFacultyList(){
		return this.facultyList.toArray(new Faculty[this.facultyList.size()]);
	}

	/** Returns a string description of each faculty member in the faculty list,
	 *  including thier ID, Name, Title, and tenure or union status, depending on
	 *  faculty type */
	public String toString() {
		String result = "";
		for (Faculty member : this.facultyList)
			result += member + "\n"; // one line per member
		return result;
	}
	
	//1.findID
	//this method is to do a case-insensitive search of id and return a faculty with corresponding id
	//or return null if no id found
	public Faculty findID(String id) {
		for(int i = 0; i < this.facultyList.size(); i++) {
			if (id.toLowerCase().equals(facultyList.get(i).getId().toLowerCase())) {
				return facultyList.get(i);
			}
		}
		return null;
	}
	
	//2.add Faculty
	//this method is to add a new faculty if the id do not exist 
	//and add the faculty to the end of the facultyList
	public void add(Faculty member) {
		if(findID(member.getId()) == null) {
			facultyList.add(member);
		}
	}
	
	//3. remove faculty
	//this method is to remove a faculty if passed id is found in facultyList-id
	public void remove(String identity) {
		for(int i = 0; i < this.facultyList.size(); i++) { //check id for each faculty in the list
			if (identity.toLowerCase().equals(facultyList.get(i).getId().toLowerCase())) {
				facultyList.remove(i);
			}
		}
	}
	
	//4.1 returns a list of type Faculty containing all tenure-track faculty members.
	public ArrayList<Faculty> tenureTrackList() {
		ArrayList<Faculty> tenureTrack = new ArrayList<Faculty>();
		for(int i = 0; i < this.facultyList.size(); i++) {
			if(!facultyList.get(i).getFacultyType().getTitle().equals("Lecturer")) {
				tenureTrack.add(facultyList.get(i));
			}
		}
		return tenureTrack;
	}
	
	//4.2 returns a list of type Faculty containing all tenure-track faculty members who have tenure
	public ArrayList<Faculty> tenuredList(){
		ArrayList<Faculty> tenured = new ArrayList<Faculty>();
		
		//tenured faculty must be in the tenureTrack ArrayList
		for(int i = 0; i < tenureTrackList().size(); i++) { 
			if(((TenureTrack)tenureTrackList().get(i).getFacultyType()).getTenured()) {
				tenured.add(tenureTrackList().get(i));
			}
		}
		return tenured;
	}
	
	//4.3 returns a list of type Faculty containing all tenure-track faculty members who do not have tenure.
	public ArrayList<Faculty> notTenuredList(){
		ArrayList<Faculty> notTenured = new ArrayList<Faculty>();
		
		//not tenure faculty must be in the tenureTrack ArrayList
		for(int i = 0; i < tenureTrackList().size(); i++) {
			if(!((TenureTrack)tenureTrackList().get(i).getFacultyType()).getTenured()) {
				notTenured.add(tenureTrackList().get(i));
			}
		}
		return notTenured;
	}
	
	//4.4 returns a list of type Faculty containing all faculty members who are adjunct
	public ArrayList<Faculty> adjunctList(){
		ArrayList<Faculty> adjunct = new ArrayList<Faculty>();
		for(int i = 0; i < this.facultyList.size(); i++) {
			if(facultyList.get(i).getFacultyType().getTitle().equals("Lecturer")) {
				adjunct.add(facultyList.get(i));
			}
		}
		return adjunct;
	}
	
	//4.5 returns a list of type Faculty containing all adjuncts who are members of the union
	public ArrayList<Faculty> unionList(){
		ArrayList<Faculty> unionMember = new ArrayList<Faculty>();
		
		//union members must be in the adjunctList
		for(int i = 0; i < adjunctList().size(); i++) {
			if(((Adjunct)adjunctList().get(i).getFacultyType()).isUnionMember()) {
				unionMember.add(adjunctList().get(i));
			}
		}
		return unionMember;
	}
	
	//4.6 returns a list of type Faculty containing all adjuncts who are not members of the union
	public ArrayList<Faculty> notUnionList(){
		ArrayList<Faculty> notUnion = new ArrayList<Faculty>();
		
		//non-union members must be included in the adjunctlist
		for(int i = 0; i < adjunctList().size(); i++) {
			if(!((Adjunct)adjunctList().get(i).getFacultyType()).isUnionMember()) {
				notUnion.add(adjunctList().get(i));
			}
		}
		return notUnion;
	}

	public static void main(String[] args) {
		// some code to get you started
		Department faculty = new Department("CIS");
		
		faculty.add(new Faculty("0124", "Margaret", "Mead", new TenureTrack("Associate Professor", true)));		
		faculty.add(new Faculty("1245", "Grace", "Hopper", new Adjunct(true)));
		
		System.out.println(faculty);
		
		//test findid method
		System.out.println(faculty.findID("0124")); // should return "ID: 0124Name: Mead, Margaret Title: Associate Professor Status: tenured"
		System.out.println(faculty.findID("0000")); //should return null
		
		//test add method
		faculty.add(new Faculty("2345", "Yukimura", "Tanaka", new TenureTrack("Assistant Professor", false)));
		faculty.add(new Faculty("0124", "Shuichiro", "Ooshi", new TenureTrack("Associate Professor", false)));// should not be added
		faculty.add(new Faculty("3456", "Shishido", "Ootori", new TenureTrack("Assistant Professor", true)));
		faculty.add(new Faculty("4567", "Ryoga", "Echizen", new TenureTrack("Full Professor", false)));
		faculty.add(new Faculty("5678", "Takashi", "Momoshiro", new TenureTrack("Full Professor", true)));
		faculty.add(new Faculty("6789", "Kawaru", "Kaito", new Adjunct(false)));
		faculty.add(new Faculty("7890", "Akaya", "Kirihara", new Adjunct()));
		faculty.add(new Faculty("8901", "Hiroki", "Aiba", new Adjunct(true)));


		System.out.println("\n" + faculty); //should print faculty members:Mead, Hopper, Tanaka, Ootori, Echizen, Momoshiro, Kaito, Kirihara, Aiba
		
		//test remove method
		faculty.remove("1245");//should remove Grace Hopper
		faculty.remove("9999");//should not remove anyone
		
		System.out.println("\nAfter remore Grace Hopper: \n" + faculty);//should print Mead, Tanaka, Ootori, Echizen, Momoshiro, Kaito, Kirihara, Aiba
		
		//test tenureTrack list
		System.out.println(faculty.tenureTrackList());
		
		//test tenuredlist
		System.out.println(faculty.tenuredList());
		
		//test not tenured list
		System.out.println(faculty.notTenuredList());
		
		//test adjunct list
		System.out.println(faculty.adjunctList());
		
		//test unionlist
		System.out.println(faculty.unionList());
		
		//test non-union list
		System.out.println(faculty.notUnionList());
		
	}
}
