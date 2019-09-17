/** Minxuan Zhao CS603 Assignment 4
 * This is the main method that create two flight objects when taking in flight code, 
 * departure city, destination city, and the duration of the flight in minutes.
 * It then check if the two flights connect
 */
import java.util.Scanner;

public class Travel {
	public static void main(String[] args) {
		Scanner kb = new Scanner (System.in);
		
		//set up variables
		String FlightCode;
		String Departure;
		String Destination;
		int Duration;
		
		System.out.println("Please enter a flight code that begins with two letters followed by a dash and 3 to 5 digits: ");
		FlightCode = kb.nextLine();
		
		System.out.println("Enter the departure city: ");
		Departure = kb.nextLine();
		
		System.out.println("Enter the destination city: ");
		Destination = kb.nextLine();
		
		System.out.println("Enter the flight duration in minutes: ");
		Duration = kb.nextInt();
		
		//create the first Flight object with the above input
		Flight AFlight = new Flight (FlightCode, Departure, Destination, Duration);
		
		//validate the flight code and prompt for re-enter the flight code if not valid
		AFlight.setFlightCode(FlightCode);
		while (AFlight.getFlightCode().equals("ZZ-000")) {
			System.out.println("The flight code for the newly created flight is invalid. Please re-enter: ");
			FlightCode = kb.nextLine();
			AFlight.setFlightCode(FlightCode);
		}
		
		//print the first flight object
		System.out.println(AFlight.toString());
		
		//take in the second flight information
		System.out.println("\nPlease enter a flight code that begins with two letters followed by a dash and 3 to 5 digits: ");
		FlightCode = kb.nextLine();
		
		System.out.println("Enter the departure city: ");
		Departure = kb.nextLine();
		
		System.out.println("Enter the destination city: ");
		Destination = kb.nextLine();
		
		System.out.println("Enter the flight duration in minutes: ");
		Duration = kb.nextInt();
		
		//validate the flight code and prompt for re-enter the flight code if not valid for the second Flight
		Flight bFlight = new Flight (FlightCode, Departure, Destination, Duration);
		bFlight.setFlightCode(FlightCode);
		while (bFlight.getFlightCode().equals("ZZ-000")) {
			System.out.println("The flight code for the newly created flight is invalid. Please re-enter: ");
			FlightCode = kb.nextLine();
			bFlight.setFlightCode(FlightCode);
		}	
		
		//print the second Flight object
		System.out.println(bFlight.toString());
		
		//check and prompt if the two flights connect 
		if (AFlight.connecting(bFlight)) {
			System.out.println("\nThe two flights connect.");
		} else {
			System.out.println("\nThe two flights do not connect.");
		}
		
		kb.close();
	}//end of main method
}//end of class 
