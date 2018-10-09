import java.util.Scanner;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class HyperGeometricProbabilityCalc {

	public static void main(String[] args) {
		boolean keepGoing = true;
		int k = 0;
		int x = 0;
		double C1 = 0;
		double C2 = 0;
		double C3 = 0;
		int N = 0;
		int n = 0;
		double ans = 0;

		do {
			System.out.println("1: Calculate\n2: Exit");
			Scanner input = new Scanner(System.in);
			int in =  input.nextInt();
			switch (in) {
			case 1: System.out.println("N = How many in entire population?");
					N = input.nextInt();
					System.out.println("n = How many trials?");
					n = input.nextInt();
					System.out.println("k = How many successes in entire population?");
					k = input.nextInt();
					System.out.println("x = How many successes?");
					x = input.nextInt();
					
					C1 = CombinatoricsUtils.binomialCoefficientDouble(k,x);
					C2 = CombinatoricsUtils.binomialCoefficientDouble(N-k,n-x);
					C3 = CombinatoricsUtils.binomialCoefficientDouble(N,n);
					ans = Math.round(((C1*C2) / C3) * 10000d) / 10000d; 
					System.out.println("The Hypergeometric Probability is: " + ans);
			break;
			case 2: keepGoing = false;
			break;
			}
		} while (keepGoing == true);
	}

}
