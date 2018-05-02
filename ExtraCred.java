//Reese Champagne CS211 2/2/2018 Chapter 5 Extra Credit
import java.util.*;

public class ExtraCred {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String[] yn = {"Yes", "No"};
		boolean keepGoing = true;
		String answer = "";
		do {
			int a = (int) Math.round(Math.random());
			int b = (int) Math.round(Math.random());

			for (int i = 0; i < 2; i++) {
				if (yn[a].equals("Yes") && yn[b].equals("Yes")) {
					answer = "Ordered List";
				} else if (yn[a].equals("Yes") && yn[b].equals("No")) {
					answer = "Unordered List";
				} else if (yn[a].equals("No") && yn[b].equals("Yes")) {
					answer = "Permutation";
				} else {
					answer = "Set";
				}
			}
			System.out.println("Which structure do these rules belong to? Type 'Exit' to quit program\n"
					+ "\nRepeats allowed? " + yn[a] + "| Order matters? " + yn[b]);
			String userin = input.nextLine();
			if (answer.equalsIgnoreCase(userin.trim())) {
				System.out.println("\nCorrect! The answer was: " + answer + "\n" + "------------------");
			} else if (userin.equalsIgnoreCase("Exit")) {
				System.exit(0);
			} else {
				System.out.println("\nIncorrect. The answer was: " + answer + "\n" + "------------------");
			}
		} while (keepGoing);
	}

}
