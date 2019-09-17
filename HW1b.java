/**Minxuan Zhao CS603 HW1b 

 * This program is to calculate the delivery cost based on weight and delivery method.**/


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class HW1b {
	public static void main (String[] srgs) {
		Scanner keyboard = new Scanner(System.in);
		
	//enter the number of pounds & ounces
	System.out.println("Enter the number of pounds in whole number: ");
	int pound = keyboard.nextInt();
	
	System.out.println("Enter the number of ounces in whole number: ");
	int oz = keyboard.nextInt();
	
	//select delivery method, express or regular
	System.out.println("Enter 1 for express delivery or any other integer for regular delivery.");
	int express = keyboard.nextInt();
	
	//coupon?
	System.out.println("Enter 1 if you have a coupon or any other integer if not");
	int coupon = keyboard.nextInt();
	
	final double FR = 5.50; 	//constant express flat rate
	final double EXPP = 1.20; 	//constant express per pound
	final double RPP = 0.98;	//constant regular per pound
	final double ED = 0.07; 	//constant express discount
	final double RD = 0.05; 	//constant regular discount
	
	//compute total cost
	double cost;
	if (express == 1) {
		cost = FR + (EXPP * pound) + (EXPP * oz / 16); 
	} else {
		cost = RPP * pound + RPP * oz / 16;
	}
	
	//compute discount
	double discount;
	if (coupon == 1) {
		if (express ==1 ) {
			discount = cost * ED;
		} else 
			discount = cost * RD;
	} else 
		discount = 0.00;
	
	//compute total due after discount
	double due = cost - discount;
	
	//two digits after decimal
	NumberFormat twodigits = new DecimalFormat("#0.00");
	
	
	//print total cost, discount & total due
	System.out.println("Total cost before discount: $" + twodigits.format(cost));
	System.out.println("Amount of discount: $" + twodigits.format(discount));
	System.out.println("Final cost: $" + twodigits.format(due));
	
	keyboard.close();
	} 	//end of method
	
}	//end of class
