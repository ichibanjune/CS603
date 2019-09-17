/**Minxuan Zhao
 * Seat on a plane - represented by a row number and a seatID (String) Each seat
 * has one passenger - can assign passenger to and remove passenger from a seat
 */

public class Seat {
	private int row;
	private String seatID;
	private Passenger passenger;

	/** Seat represented by row and seat ID, such as 1A, 5C, etc. */
	public Seat(int row, String seatID) {
		this.row = row;
		this.seatID = seatID;
	}

	/** Add passenger to a seat */
	public void addPassenger(Passenger p) {
		this.passenger = p;
	}

	/** Remove passenger by setting field to null */
	public void removePassenger() {
		this.passenger = null;
	}
	
	/**
	 * 1. TO DO hasSpecialMeal() returns true if seat has a passenger and that
	 * passenger has a special meal. Otherwise, false is returned.
	 */
	public boolean hasSpecialMeal() {
		if (this.passenger != null) {
			return this.passenger.getSpecialMeal();
		} else {
			return false;
		}
	}

	/**
	 * 2. TO DO toString() returns the row and seat ID ("2A" for ex.) 
	 *    and includes a description of the passenger if there one in the seat
	 */
	public String toString() {
		if (this.passenger == null) {
			return Integer.toString(this.row) + this.seatID;
		} else {
			return Integer.toString(this.row) + this.seatID + " " + this.passenger.toString();
		}
	}
	
	
	// standard accessor and mutator methods
	public Passenger getPassenger() {
		return this.passenger;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return this.row;
	}

	public void setSeatID(String id) {
		this.seatID = id;
	}

	public String getSeatID() {
		return this.seatID;
	}

	// testing code
	public static void main(String[] args) {
		Seat s = new Seat(1, "G");
		System.out.println("seat: " + s);
		// test hasSpecialMeal()
		s.addPassenger(new Passenger("Jones", "Mary", "Ann", true));
		System.out.println("After adding passenger:\n" + s);
		// test hasSpecialMeal()
		
		// add additional testing code	
		Seat s1 = new Seat(3, "B");
		System.out.println("Seat: " + s1);
		
		s1.addPassenger(new Passenger("right", "kim", "jr", false));
		System.out.println("After adding passenger without special meal:\n" + s1);
		
		s1.addPassenger(new Passenger("right", "kim", "jr", true));
		System.out.println("After updating passenger:\n" + s1);
		
		Seat s2 = new Seat(5, "C");
		System.out.println("Seat: " + s2);

		s2.addPassenger(new Passenger("kato", "kazuki", false));
		System.out.println("After adding passenger:\n" + s2);
		s2.removePassenger();
		System.out.println("After removing passenger:\n" + s2);
		
		
	}
}
