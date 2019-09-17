/** Minxuan Zhao CS603 Assignment 4
 * Define a class called Flight to represent flight information with four private variables:
 * flightCode(string), departure(String), destination(String), duration(in minutes, integer)
 *
 */
public class Flight {
	//define instance variables
	private String flightCode = "ZZ-000";
	private String departure;
	private String destination;
	private int duration; //flight duration in minutes
	
	//4-arg constructor
	public Flight (String flightCode, String departure, String destination, int duration) {
		this.flightCode = flightCode;
		this.departure = departure;
		this.destination = destination;
		this.duration = duration;
	} //end of constructor
	
	//check if the flight code is valid
	public static boolean isValidCode (String flightCode) {
		if (flightCode.length() >= 6 && flightCode.length() <= 8) {
			if (Character.isAlphabetic(flightCode.charAt(0)) 
						&& Character.isAlphabetic(flightCode.charAt(1))
						&& flightCode.charAt(2) == '-') {
				for(int i = 3; i < flightCode.length(); i++) {
					if (!Character.isDigit(flightCode.charAt(i))) {
						return false;
					} 
				}
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	//set valid flight code in upper case, invalid flight code to "ZZ-000"
	public void setFlightCode (String flightCode) {
		if (isValidCode(flightCode)) {
			this.flightCode = flightCode.toUpperCase();
		} else {
			this.flightCode = "ZZ-000";
		}
	}
	
	//set departure in upper case
	public void setDeparture (String departure) {
		this.departure = departure.toUpperCase();
	}
	
	//set destination in upper case
	public void setDestination (String destination) {
		this.destination = destination.toUpperCase();
	}
	
	public String getFlightCode() {
		return flightCode;
	}
	
	//check if the flight is long enough to include meal
	public boolean hasMeal () {
		if (this.duration >= 300) { //check if flight duration is at least 6 hours (300 minutes)
			return true;
		} else return false;
	}
	
	//provide description of the Flight object in String
	public String toString() {
		String meal;
		if (this.hasMeal()) {
			meal = "Meal Included";
		} else {
			meal = "No Meal";
		}
		
		return "Flight code: " + this.flightCode + " Departure: " + this.departure + 
				" Destination: " + this.destination + " Duration: " + this.duration + " minutes " + meal;
	}
	
	//check if the two flights connect
	//by checking if the second flight departs from the destination of the first flight 
	//and if first two letters in the flight code of the two flights are the same 
	public boolean connecting(Flight secondFlight) {
		if (this.flightCode.charAt(0) == secondFlight.flightCode.charAt(0)
				&& this.flightCode.charAt(1) == secondFlight.flightCode.charAt(1)
				&& this.destination.equals(secondFlight.departure)) {
			return true;
		} else {
			return false;
		}
	}	
}
