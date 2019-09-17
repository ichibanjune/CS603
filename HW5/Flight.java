/** Minxuan Zhao
 *  Flight class - each flight has a flight number, departure and 
 *  arrival cities, a list of seats, and a number of rows,
 *  with 5 seats per row.
 *  
 *  The main method includes code for adding seats to a flight using numbers 
 *  for the row and letters for the seat locations (such as 1A, 1B, 2A for
 *  seats in row 1 locations A and B, row 2 location A, etc.)
 */

import java.util.ArrayList;

public class Flight {
	private String flightNumber;
	private String departure;
	private String arrival;
	private int numSeats; // maximum number of seats on flight
	private Seat[] seats;
	private int numAddedSeats; // actual number of seats added to flight 

	/** 
	 *  4-arg constructor for setting flight number, departure and arrival locations, 
	 *  and the maximum number of seats on the plane, which is also used for initializing the
	 *  size of the array representing seats on the plane
	 */ 
	public Flight(String flightNumber, String departure, String arrival, int numSeats){
		this.flightNumber = flightNumber;
		this.departure = departure;
		this.arrival = arrival;
		this.numSeats = numSeats;
		this.seats = new Seat[this.numSeats];
	
	}

	/** add seat to array of seats if have room in array */
	public void addSeat(Seat s){
		if (this.numAddedSeats < this.numSeats){
			this.seats[this.numAddedSeats++] = s;
		}
	}

	/** 1. TO DO 
	 *  getSpecialSeats() returns an array containing the index values 
	 *  of all seats in the Seat array that have passengers getting special 
	 *  meals. The size of this array must be EXACTLY equal to the number seats 
	 *  on the plane in which passengers have special meals. For example, if
	 *  passengers in three seats have special meals, the array
	 *  returned must be of size three and contain only the index 
	 *  values of those three seats.  
	 */
	public int[] getSpecialSeats(){
		// your code here
		
		//fill the seats with passengers getting special meal to obj (arrarylist)
		ArrayList<Integer> obj = new ArrayList<Integer>();
		for(int i=0; i< this.numAddedSeats; i++) {
			if (this.seats[i].hasSpecialMeal()) {
				obj.add(i);
			}
		}
		//set the length of specialSeat array to the size of obj (arraylist), 
		//which is the number of seats with passengers getting special meal
		int[] specialSeat = new int[obj.size()]; 
		
		//fill out the specialSeat array with the seatIDs that contain passengers with special meal 
		for (int j = 0; j<obj.size(); j++) {
			specialSeat[j] = obj.get(j);
		}
		return specialSeat; 
	}
	
	public String toString(){
		return "Flight " + this.flightNumber + " from " + this.departure + " to " 
				+ this.arrival;
	}
		
	// standard accessor and mutator methods
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	
	public Seat[] getSeats(){
		return this.seats;
	}
	
	public void setSeats(Seat[] seats){
		this.seats = seats;
	}
	
	
	// Testing code
	public static void main(String[] args){
		Flight f = new Flight("1544", "BOS Boston", "AUS Austin", 10);
		System.out.println(f); // print flight into

		// add 3 seats to the flight
		Seat s1 = new Seat(1, "A");
		Seat s2 = new Seat(1, "B");
		Seat s3 = new Seat(2, "E");
		f.addSeat(s1);
		f.addSeat(s2);
		f.addSeat(s3);

		// add passengers to seats
		Passenger p1 = new Passenger("Jones", "Mary", true);
		Passenger p2 = new Passenger("Smith", "John", false);
		Passenger p3 = new Passenger("Henderson", "Jill", true);
		s1.addPassenger(p1);
		s2.addPassenger(p2);
		s3.addPassenger(p3);
		// add code to print seat indices for seats with special meals
		System.out.println("The length of the output array: " + f.getSpecialSeats().length);
		for (int i = 0; i<f.getSpecialSeats().length; i++) {
			System.out.print(f.getSpecialSeats()[i] + " ");
		}
		
		//add another testing flight
		Flight fl =  new Flight ("802", "New York", "Los Angelos", 22);
		//print flight info
		System.out.println("\n" + fl); 
		//print number of seats with special meal
		System.out.println("Number of special seats before adding seats: " + fl.getSpecialSeats().length);
		
		//add 5 seats to flight fl
		Seat s4 = new Seat(1,"E");
		Seat s5 = new Seat(3,"A");
		Seat s6 = new Seat(3,"E");
		Seat s7 = new Seat(4,"B");
		Seat s8 = new Seat(4,"E");
		fl.addSeat(s4);
		fl.addSeat(s5);
		fl.addSeat(s6);
		fl.addSeat(s7);
		fl.addSeat(s8);
		//print number of seats with special meal
		System.out.println("Number of special seats before adding passengers: " + fl.getSpecialSeats().length);

		
		//add passengers to seats
		Passenger p4 = new Passenger("Johan", "Liebert", false);
		Passenger p5 = new Passenger("Anna", "lieBert", true);
		Passenger p6 = new Passenger("Heinrich", "lunge", "m", false);
		Passenger p7 = new Passenger("eva", "heinemann", "sue", true);
		Passenger p8 = new Passenger("Kenzou", "tenma", true);
		s4.addPassenger(p4);
		s5.addPassenger(p5);
		s6.addPassenger(p6);
		s7.addPassenger(p7);
		s8.addPassenger(p8);
		
		//print seats indices for special seats
		System.out.println("Number of special seats after adding passengers: " + fl.getSpecialSeats().length);
		for (int i = 0; i<fl.getSpecialSeats().length; i++) {
			System.out.print(fl.getSpecialSeats()[i] + " ");
		}
	}
}
