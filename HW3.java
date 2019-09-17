/** Minxuan Zhao CS603 Assignment 3
 * This program ask you to guess a sequence of our letters ranging from a to f
 * You have up to 10 chances to guess
 */

import java.util.Arrays;
import java.util.Scanner;


public class HW3 {
	public static void main (String[] args) {
		//get randomletters
		char[] correctSequence = RandomLetters.generateSequence();
		
		Scanner ky = new Scanner(System.in);
		
		int guessNo = 1; //count the number of guesses
		char[] guess = new char[4]; // array of player's guess
		
		int exactMatch; //number of exact match, initialized to 0
		int partialMatch; //number of partial match, initialized to 0
		
		char[] copyCorrect = new char[4]; //a copy of the correct sequence, for checking propose
		char[] copyGuess = new char[4]; //a copy of the guess, for checking propose

		System.out.println("You have up to 10 chances to gress a sequence of four letters ranging from a to f.\n");
		System.out.println("Enter four letters, separated by a space.");
		
		while (guessNo <= 10) {
			exactMatch = 0;
			partialMatch = 0;
			System.out.println("\nGuess " + guessNo); 
			for (int i = 0; i < correctSequence.length; i++) { //take in the player's guess of letters
				guess[i] = ky.next().charAt(0);
			} 
			guess = arrayToLowerCase(guess); //change all char in lower case
			
			while (!verifyLetters(guess, 'a', 'f')) { //verify guess and prompt for new guess if not valid
				System.out.println("Enter only letter between 'a' and 'f'");
				System.out.println("Guess " + guessNo); // ask player to enter a valid guess for the same sequence number
				for (int i = 0; i < correctSequence.length; i++) { //take in the player's guess of letters
					guess[i] = ky.next().charAt(0);
				} 
				guess = arrayToLowerCase(guess);//change all char in lower case
			}
			
			copyCorrect = Arrays.copyOf(correctSequence, correctSequence.length);
			copyGuess = Arrays.copyOf(guess, guess.length);
			
			for (int i = 0; i < correctSequence.length; i++) {//count exact match
				if (guess[i] == correctSequence[i]) {
					copyCorrect[i] = '1';
					copyGuess[i] = '0';
					exactMatch++;
				}
			} //end for loop -- count exact match
			
			if (exactMatch == correctSequence.length) {
				System.out.println("You won!");
				break; //win the game and stop the loop whenever the player gets all correct guesses
			}
			else { //check for partial match
				for (int i = 0; i < copyGuess.length; i++) {
					for (int j = 0; j < copyCorrect.length; j++) {
						if (copyGuess[i] == copyCorrect[j]) {
							copyCorrect[j] = '1';
							copyGuess[i] = '0';
							partialMatch++;
						} //end if
					} //end inner for loop
				} //end outer for loop
				System.out.println("Exact (correct letter and position): " + exactMatch);
				System.out.println("Partial (correct letter, incorrect position): " + partialMatch);
			}
			guessNo++;
		} //end while
		if (guessNo == 11) { // the player lose if run out of chances
			System.out.println("\nYou lost. The correct sequence is: " + Arrays.toString(correctSequence));
		}
		
		ky.close(); //close keyboard
	} //end main method
	
	/** the following method change the letters to lower case*/
	public static char[] arrayToLowerCase (char[] original) {
		char[] lowerCaseArray = new char[original.length];
		for (int i = 0; i < original.length; i++) {
			lowerCaseArray[i] = Character.toLowerCase(original[i]);
		}
		return lowerCaseArray;
	} //end method arrayToLowerCase
	
	/** the following method verify if input guess is valid 
	 * and return false if any of the letters is out of valid range*/
	public static boolean verifyLetters (char[] oneArray, char start, char end) {
		for(int i = 0; i < oneArray.length; i++) {
			if (oneArray[i] < start || oneArray[i] > end) {
				return false;
			} 
		}
		return true;
	} // end method verifyLetters
} //end class
