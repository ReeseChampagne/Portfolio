//Reese Champagne CS211 3/12/2018 Chapter 6 Project
import java.util.*;

public class Ch6Project {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;
		
		do {
		float expectedValue = 0;
		System.out.println("What is n(S), the amount of outcomes without restriction?");
		float nS = input.nextFloat();
		System.out.println("How many outcomes are there?");
		int n = input.nextInt();
		
		for (int i=0; i<n;i++) {
			System.out.println("OUTCOME " + (i+1) + ":\n\nWhat is the value of this outcome?");
			int v = input.nextInt();
			System.out.println("What is n(E), the amount of outcomes that have this value?");
			int nE = input.nextInt();
			float prob = nE / nS;
			System.out.println("Probability for this outcome is " + (prob*100) + "%\n");
			expectedValue += v * prob;
		} 
		
		System.out.println("The expected value E[X] = " + expectedValue);
		keepGoing = false;
		
		System.out.println("\nCalculate another? (y/n):");
		String yN = input.next();
		
		if (yN.equalsIgnoreCase("y")) {
			keepGoing = true;
		} else {
			System.exit(1);
		}
		
		} while (keepGoing);
		
	}

}
