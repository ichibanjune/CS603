/** Minxuan Zhao	CS603 Assignment2
 * This program is to calculate college costs for a two- or four-year public (in-state 
 * or out-of-state) college.
 */

import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class HW2 {
	public static void main (String[] args) {
		Scanner key = new Scanner (System.in);
		
		//input the number of years for the program
		System.out.println("Enter 2 for two-year program or 4 for four-year program: ");
		int years = key.nextInt();
		//validation of number of years for the program
		while (years != 2 && years != 4) {
			System.out.println("Invalid number of college years. Please re-enter: ");
			years = key.nextInt();
		}
		
		//input in- or out-of-state for 4 years program
		int state = 1; //default as in-state
		if (years == 4) {
			System.out.println("Enter 1 for in-state or 2 for out-of-state: ");
			state = key.nextInt();
		}
		//validation of in- or out-of-state input
		while (state != 1 && state != 2) {
			System.out.println("Invalid program location. Please re-enter: ");
			state = key.nextInt();
		}
		
		//input 1 for Tuitions and fees only or 2 for full cost including room and board
		System.out.println("Enter 1 for Tuitions and Fees only or 2 for Full Cost (including room and board: ");
		int costtype = key.nextInt();
		//validation for tuition or full cost selection
		while (costtype != 1 && costtype != 2) {
			System.out.println("Invalid cost type. Please re-enter: ");
			costtype = key.nextInt();
		}
		
		//input GPA
		int gpa2 = 0; //default GPA for two-year is 0
		int gpa4 = 0; //default GPA for four-year is 0
		if (years == 2) {
			System.out.println("Enter weighted GPA for two-year program as whole number (1-10): ");
			gpa2 = key.nextInt(); //input GPA for two-year program
		} else {
			System.out.println("Enter weighted GPA for four-year program as whole number (4-20): ");
			gpa4 = key.nextInt(); //input GPA for four-year program
		} //end if
		
		//GPA validation for two-year program
		while (years == 2 && gpa2 > 10 || gpa2 < 1) {
			System.out.println("Invalid GPA. Please re-enter: ");
			gpa2 = key.nextInt();
		}
		
		//GPA validation for four-year program
		while (years == 4 && gpa4 > 20 || gpa4 < 4) {
			System.out.println("Invalid GPA. Please re-enter: ");
			gpa4 = key.nextInt();
		}
		
		//input SAT score
		System.out.println("Enter a combined SAT score (400-1600): ");
		int sat = key.nextInt();
		
		//SAT validation
		while (sat > 1600 || sat < 400) {
			System.out.println("Invalid SAT scores. Please re-enter: ");
			sat = key.nextInt();
		}
		
		final int TUITION2YR = 3826; //constant value for two-year tuition and fees
		final int TUITION4YRINSTATE = 14307; //constant value for four-year in state tuition and fees
		final int TUITION4YROUTOFSTATE = 30885; //constant value for four-year out-of-state tuition and fees
		
		final int ROOM2YR = 6850; //constant value for two-year room and board
		final int ROOM4YR = 13495; //constant value for four-year room and board, same for in- or out-of- state
		
		//compute the cost before merit aid
		double cost;
		if (years == 2) {
			if (costtype == 1) {
				cost = TUITION2YR; //cost for two-year tuition and fees only
			} else {
				cost = TUITION2YR + ROOM2YR; //cost for two-year full cost
			}
		} else {
			if (state == 1) { 
				if (costtype == 1) {
					cost = TUITION4YRINSTATE; //cost for four-year in-state tuition only
				} else cost = TUITION4YRINSTATE + ROOM4YR; //cost for four-year in-state full cost
			} else {
				if (costtype ==1) {
					cost = TUITION4YROUTOFSTATE; //cost for four-year out-of-state tuition only
				} else cost = TUITION4YROUTOFSTATE + ROOM4YR; //cost for four-year out-of-state full cost
			}
		} //end of cost calculate
		
		double merit = 0.00; //default merit value is $0.00
		int i = 0; //set up a counting variable for merit calculating
		
		//calculate merit for 2 year program
		while (years == 2 && i<4) {
			if (gpa2 >= 9-i*1 && sat >= 1500-i*50) {
				merit = 2100 - i*500;
				break; 
			} //end if
			i++;
		} 
		
		//calculate merit for 4 year in-state program
		while (years == 4 && state == 1 && i<4) {
			if (gpa4 >= 18-i*3 && sat >= 1540-i*35) {
				merit = 5200 - i*1000;
				break;
			} //end if
			i++;
		} 
		
		//calculate merit for 4 year out-of-state program
		while (years == 4 && state == 2 && i<3) {
			if (gpa4 >= 18-i*4 && sat >= 1550-i*50) {
				merit = 8800 - i*1200;
			} //end if
			i++;
		}
				
		//calculate the net cost after merit aid
		double net = cost - merit;
		
		//two digits after decimal
		NumberFormat twodigits = new DecimalFormat("#0.00");

		//print the cost amount
		System.out.println("Cost of 1 year of college: $" + twodigits.format(cost));
		System.out.println("Amount of merit aid: $" + twodigits.format(merit));
		System.out.println("Net cost: $" + twodigits.format(net));
		
		
		key.close(); //close the scanner
	} //end of main

} //end of class
