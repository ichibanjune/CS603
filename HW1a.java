/**Minxuan Zhao cs603 HW1a 
 * This program is to calculate the class break start/end time and class end time.**/

import java.util.Scanner;

public class HW1a {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		//get class start hour and minute
		System.out.println("Please enter the class starting hour in whole number(in 24 hours): ");
			int startH = keyboard.nextInt();
		
		System.out.println("Please enter the class starting minute in whole number: ");
			int startM = keyboard.nextInt();
		
			
		final int CLM = 140;	//constant value: length of class
		final int BLM = 10;		//constant value: length of break
		
		//calculate class break start hour and minute
		int breakM, breakH; 	//variable of break start hour & minute
		if (startM + ((CLM-BLM)/2) % 60 < 60) {	//check if break start minute is larger than 60
			breakM = startM + ((CLM-BLM)/2) % 60;
			breakH = startH + CLM/120;
		} else {
			breakM = startM + ((CLM-BLM)/2) % 60 - 60;
			breakH = startH + CLM/120 + 1;}
		
		//calculate break end hour and minute
		int breakendM, breakendH;
		if (breakM + BLM < 60) {	//check if break end minute is larger than 60
			breakendM = breakM + BLM;
			breakendH = breakH;
		} else {
			breakendM = breakM + BLM - 60;
			breakendH = breakH + 1;
		}
		
		//print break time
		System.out.println("Class break start hour: " + breakH);
		System.out.println("Class break start minute: " + breakM);

		System.out.println("Class break end hour: " + breakendH);
		System.out.println("Class break end minute: " + breakendM);
		
		//calculate class end hour and minute
		int endH, endM;
		if (startM + CLM%60 < 60) {
			endH = startH + CLM/60;
			endM = startM + CLM%60;
		} else {
			endH = startH + CLM/60 + 1;
			endM = startM + CLM%60 - 60;
		}
		
		//print class end time
		System.out.println("Class end hour: " + endH);
		System.out.println("Class end minute: " + endM);

		
		keyboard.close();
	}

}
