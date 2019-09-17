/** Minxuan Zhao
 *  Passenger on a flight  
 *  Passenger has first and last name with optional
 *  middle name. Also keeps track of if the passenger
 *  requires a special meal 
 */

public class Passenger {

	private String firstName;
	private String lastName;
	private String middleName;
	private boolean specialMeal;
	
	/** 4. TO DO
	 *  4-arg constructor used for instantiating a passenger 
	 *  for whom all three names have been specified. 
	 *  For full credit,  invoke the appropriate SET METHODS 
	 *  to ensure proper formatting.
	 */		
	public Passenger (String lastName, String firstName, String middleName, boolean specialMeal){
	    // your code here
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setMiddleName(middleName);
		this.specialMeal = specialMeal;
	}
	
	/** 5. TO DO
	 *  3-arg constructor used for instantiating a passenger 
	 *  for whom no middle name has been specified. 
	 *  Make use of your 4-arg constructor for full credit.
	 */
	public Passenger (String lname, String fname, boolean specialMeal){
	    // your code here 
		this(lname, fname, "", specialMeal);
	}
	
	/** 1. TO DO
	 * Static formatName(String) - static method that returns the value of the String 
	 * parameter with the first letter in uppercase and the remaining letters 
	 * in lowercase. Example, if passed "aBcD" it returns "Abcd"
	 */
	public static String formatName (String name) {
		if (!name.equals("")) {
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		}
		return name;
	}

	/** 2. TO DO
	 *  Provide mutator methods for setting the name instance variables to the FORMATTED 
	 *  value of the string parameter. No values
	 *  are returned
	 */
	
	//set and format first name using formatName method
	public void setFirstName (String FName) {
			this.firstName = formatName(FName);
	}
	
	//set and format middle name using formatName method
	public void setMiddleName (String MName) {
		this.middleName = formatName(MName);
	}
	
	//set and format last name using formatName method
	public void setLastName (String LName) {
			this.lastName = formatName(LName);
	}


	
	/** 3. TO DO
	 *  Provide accessor methods that return the values of the instance variables 
	 */
	//accessor for firstName instance variable
	public String getFirstName() {
		return this.firstName; 
	}
	
	//accessor for middleName instance variable
	public String getMiddleName() {
		return this.middleName; 
	}
	
	//accessor for lastName instance variable
	public String getLastName() {
		return this.lastName; 
	}
	
	//accessor for specialMeal instance variable
	public boolean getSpecialMeal() {
		return this.specialMeal; 
	}
		
	
	/** 6. TO DO
	 *  toString()  returns Lastname,<sp>Firstname<sp>Middlename (if exists)
	 *  followed by ** special meal ** if that option has been specified        
	 */
	public String toString() {
		if (this.middleName.equals("")) { //for passenger without middle name
			if (this.specialMeal) {
				return this.lastName + ", " + this.firstName + " ** special meal **";
			} else {
				return this.lastName + ", " + this.firstName;
			}
		} else { //for passenger with middle name
			if (this.specialMeal) {
				return this.lastName + ", " + this.firstName + " " + this.middleName + " ** special meal **";
			} else {
				return this.lastName + ", " + this.firstName + " " + this.middleName;
			}
		}
	}
		

	// testing code
	public static void main(String[] args){		
		//add new passengers
		Passenger p1 = new Passenger("pAng", "bO", true);
		Passenger p2 = new Passenger("wHite", "JameS", false);
		Passenger p3 = new Passenger("joHN", "JIMI", "jr", true);
		Passenger p4 = new Passenger("BURGER", "cHEESE", "jr", false);
		
		//print the passenger objects
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);		
		
		//print values of get methods
		System.out.println(p4.getFirstName());
		System.out.println(p4.getLastName());
		System.out.println(p4.getMiddleName());
		System.out.println(p4.getSpecialMeal());
	}

}
