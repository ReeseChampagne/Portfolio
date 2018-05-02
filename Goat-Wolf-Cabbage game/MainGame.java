//Reese Champagne CS211 4/20/2018 Chapter 7 Project
package proj7;

import java.util.*;

public class Ch7Project {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;

		Characters traveler = new Characters("Traveler");
		Characters cabbage = new Characters("Cabbage");
		Characters goat = new Characters("Goat");
		Characters wolf = new Characters("Wolf");
		Characters[] characters = { traveler, cabbage, goat, wolf };

		do {
			System.out.print("Island 1: ");
			for (int i = 0; i < characters.length; i++) {
				if (characters[i].getLocation() == 0) {
					System.out.print(characters[i].getName() + " ");
				}
			}
			System.out.print("\nIsland 2: ");
			for (int i = 0; i < characters.length; i++) {
				if (characters[i].getLocation() == 1) {
					System.out.print(characters[i].getName() + " ");
				}
			}
			System.out.println(
					"\n1: Move Traveler\n2: Move Traveler/Cabbage\n3: Move Traveler/Goat\n4: Move Traveler/Wolf\n6: Exit Program");
			int choice = input.nextInt();
			switch (choice) {
			case 1: // Move Traveler Only
				if (characters[0].getLocation() == 0) {
					characters[0].setLocation(1);
				} else {
					characters[0].setLocation(0);
				}
				checkIfGameWon(characters);
				checkIfGameLost(characters);
				break;
			case 2: // Move Traveler/Cabbage
				if (characters[0].getLocation() != characters[1].getLocation()) {
					System.out.println("The traveler and cabbage must be on the same island to move!");
				} else if (characters[0].getLocation() == 0) {
					characters[0].setLocation(1);
					characters[1].setLocation(1);
				} else {
					characters[0].setLocation(0);
					characters[1].setLocation(0);
				}
				checkIfGameWon(characters);
				checkIfGameLost(characters);
				break;
			case 3: // Move Traveler/Goat
				if (characters[0].getLocation() != characters[2].getLocation()) {
					System.out.println("The traveler and goat must be on the same island to move!");
				} else if (characters[0].getLocation() == 0) {
					characters[0].setLocation(1);
					characters[2].setLocation(1);
				} else {
					characters[0].setLocation(0);
					characters[2].setLocation(0);
				}
				checkIfGameWon(characters);
				checkIfGameLost(characters);
				break;
			case 4: // Move Traveler/Wolf
				if (characters[0].getLocation() != characters[3].getLocation()) {
					System.out.println("The traveler and wolf must be on the same island to move!");
				} else if (characters[0].getLocation() == 0) {
					characters[0].setLocation(1);
					characters[3].setLocation(1);
				} else {
					characters[0].setLocation(0);
					characters[3].setLocation(0);
				}
				checkIfGameWon(characters);
				checkIfGameLost(characters);
				break;
			case 6: // Exit
				System.exit(0);
			}

		} while (keepGoing);
	}

	// Check winning condition
	public static void checkIfGameWon(Characters[] characters) {
		
		if (characters[0].getLocation() == 1 && characters[1].getLocation() == 1 && characters[2].getLocation() == 1
				&& characters[3].getLocation() == 1) {
			System.out.println("WIN: all 4 characters made it safely to Island 2");
			System.exit(0);
		}
		
	}

	// Check losing conditions
	public static void checkIfGameLost(Characters[] characters) {

		// Traveler left all 3
		if (characters[2].getLocation() == characters[1].getLocation()
				&& characters[2].getLocation() == characters[3].getLocation()
				&& characters[0].getLocation() != characters[2].getLocation()) {
			System.out.println(
					"GAME OVER: The wolf, goat, and cabbage were left alone, the wolf ate the goat after the goat ate the cabbage, or something");
			System.exit(0);
		}
		// Goat left with cabbage
		if (characters[1].getLocation() == characters[2].getLocation()
				&& characters[0].getLocation() != characters[2].getLocation()) {
			System.out.println("GAME OVER: The goat and cabbage were left alone, the goat ate the cabbage");
			System.exit(0);
		}
		// Wolf left with goat
		if (characters[2].getLocation() == characters[3].getLocation()
				&& characters[0].getLocation() != characters[2].getLocation()) {
			System.out.println("GAME OVER: The wolf and goat were left alone, the wolf ate the goat");
			System.exit(0);
		}

	}
}
