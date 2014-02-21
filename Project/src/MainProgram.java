

import java.math.BigInteger;

public class MainProgram {

	/**
	 * @param args
	 */
	

	public static void main(String[] args) {

		Validate valid = new Validate();
		SquareRoot sqRoot = new SquareRoot();

		//case for when there are 2 inputs a and p
		if (args.length == 2) {
			BigInteger numA = new BigInteger(args[0]);
			BigInteger primeP = new BigInteger(args[1]);
			//if a is negative mod it by p
			if(isNegative(numA)) numA = numA.mod(primeP);

			System.out.println("Validating the input");
			valid.isValidInput(numA, primeP);
			System.out.println("The input is valid");
			//compute the square root
			BigInteger[] xSquared = sqRoot.squareRootAModP(numA, primeP);
			
			boolean[] verifyAnswer = new boolean[2];
			//verify the answers and print the results
			printResults(verifyAnswer, xSquared, numA, primeP, sqRoot);
			
			
		}
		//case for when there are  3 inputs a, p and q 
		if (args.length == 3) {

			BigInteger numA = new BigInteger(args[0]);
			BigInteger primeP = new BigInteger(args[1]);
			BigInteger primeQ = new BigInteger(args[2]);
			BigInteger N = primeP.multiply(primeQ);
			//if a is negative mod it by N
			if(isNegative(numA)) numA = numA.mod(N);
			
			System.out.println("Validating the input...");
			valid.isValidInput(numA, primeP, primeQ);
			System.out.println("The input is valid");
			//compute the square root
			BigInteger[] xSquared = sqRoot.squareRootAModN(numA, primeP, primeQ);
			
			
			boolean[] verifyAnswer = new boolean[4];
			//verify the answers and print the results
			printResults(verifyAnswer, xSquared, numA, N, sqRoot);
		}
	}
	
	private static boolean isNegative(BigInteger n){
		if (n.compareTo(BigInteger.ONE) < 0) {
			return true;
		}
		return false;
	}
	
	private static void printResults(boolean[] verifyAnswer, BigInteger [] xSquared, BigInteger numA, BigInteger N, SquareRoot sqRoot ){
		
		for (int i = 0; i < verifyAnswer.length; i++) {
			verifyAnswer[i] = sqRoot.verifyAnswer(numA, N, xSquared[i]);
		}
		
		System.out.println("Square Roots: ");
		
		for (int i = 0; i < verifyAnswer.length; i++) {
			System.out.println(xSquared[i] + " - answer is verified to be " + verifyAnswer[i]);
		}
	}

	
}
