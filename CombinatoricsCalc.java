//Reese Champagne CS211 2/2/2018 Chapter 5 Project
import java.util.*;

public class Ch5Project {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;
		
		do {
			System.out.println("Select Calculator Type:\n 1: Ordered List\n 2: Unordered List\n 3: Permutation\n 4: Combination\n 5: Exit Program");
			int choice = input.nextInt();
			switch(choice) {
			case 1: //OrderedList
				System.out.println("\nOrdered List Calculator:\nEnter n and r variables separated by space: ");
				int oN = input.nextInt();
				int oR = input.nextInt();
				if (oR < 0) {
					System.out.println("Use positive 'r' numbers only!");
				} else {
				System.out.println(orderedCalc(oN, oR));
				}
				System.out.println();
				break;
			case 2: //UnordererdList
				System.out.println("\nUnordered List Calculator:\nEnter n and r variables separated by space: ");
				int uN = input.nextInt();
				int uR = input.nextInt();
				if (uN < 0 || uR < 0) {
					System.out.println("Use positive numbers only!");
				} else if(uN < uR) {
					System.out.println("'r' can't be greater than 'n'!");
				} else {
				System.out.println(unorderedCalc(uN, uR));
				}
				System.out.println();
				break;
			case 3: //Permutation
				System.out.println("\nPermutation Calculator:\nEnter n and r variables separated by space: ");
				int pN = input.nextInt();
				int pR = input.nextInt();
				if (pN < 0) {
					System.out.println("Undefined!");
				} else if (pR < 0) {
					System.out.println("Use positive 'r' numbers only!");
				} else {
				System.out.println(permCalc(pN, pR));
				}
				System.out.println();
				break;
			case 4: //Combination
				System.out.println("\nCombination Calculator:\nEnter n and r variables separated by space: ");
				int cN = input.nextInt();
				int cR = input.nextInt();
				if (cN < 0 || cR < 0) {
					System.out.println("Use positive numbers only!");
				} else {
				System.out.println(combCalc(cN, cR));
				}
				System.out.println();
				break;
			case 5: //Exit
				System.exit(0);
			}
			
			
		} while (keepGoing);
	}

	//Permutation
	private static long permCalc(long n, long r) {
		if (n < r) {
			return 0;
		} else if(r == 0) {
			return 1;
		}
		long d = n-r;
		for (long i = n-1; i > d; i--) {
			n *= i;
			if (n < 0) {
				System.out.println("Beyond the max this calculator is capable of!");
				return Long.MAX_VALUE;
			}
		}
		return n;
	}

	//Combination
	private static long combCalc(long n, long r) {
		if (n < r) {
			return 0;
		} 
		return permCalc(n,r) / permCalc(r,r);
	}

	//OrderedList
	private static long orderedCalc(long n, long r) {
		return (long) Math.pow(n, r);
	}

	//UnorderedList
	private static long unorderedCalc(long n, long r) {
		long first = r + n - 1;
		return combCalc(first, r);
	}
}
