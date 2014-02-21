//package assignmentOne;

import java.math.BigInteger;

public class Validate {

	final private BigInteger zero = BigInteger.ZERO;
	final private BigInteger one = BigInteger.ONE;
	final private BigInteger two = new BigInteger("2");

	public Validate() {}

	public void isValidInput(BigInteger numA, BigInteger primeP) {
		
		//isZero(numA);
		isNegativeOrZero(primeP);
		isALessThanN(numA, primeP);
		isPrime(primeP);
		isQuadraticResidue(numA, primeP);
		isTwo(primeP);

	}

	public void isValidInput(BigInteger numA, BigInteger primeP,
			BigInteger primeQ) {

		//isZero(numA);
		isNegativeOrZero(primeP);
		isNegativeOrZero(primeQ);
		isPAndQEqual(primeP, primeQ);
		isTwo(primeP);
		isTwo(primeQ);
		isALessThanN(numA, primeP.multiply(primeQ));
		isPrime(primeP);
		isPrime(primeQ);
		isQuadraticResidue(numA, primeP, primeQ);

	}

	private void isALessThanN(BigInteger a, BigInteger N) {

		if (a.compareTo(N) > 0) {
			System.err.println("Invalid input");
			System.err.println("a must be less than N=pq" );
			System.exit(1);
		}
	}

	private void isPrime(BigInteger prime) {
		
		if (!prime.isProbablePrime(20)) {
			System.err.println("Invalid input");
			System.err.println(prime + " is probably not a prime");
			System.exit(1);
		}
	}
	
	
	private  void isQuadraticResidue(BigInteger a, BigInteger p) {
		if(a.compareTo(zero)==0){
			return;
		}
		BigInteger exponent = p.subtract(one);
		exponent = exponent.divide(two);
		BigInteger result = a.modPow(exponent, p);

		if (!(result.compareTo(one)==0)) {
			System.err.println("Invalid input");
			System.err.println("a must be a quadratic residue of p and q ");
			System.exit(1);
		}
	}
	
	private  void isQuadraticResidue(BigInteger a, BigInteger p,BigInteger q) {
		

		isQuadraticResidue(a.mod(p), p);
		isQuadraticResidue(a.mod(q), q);
		
	}
	private void isNegativeOrZero(BigInteger primeP){
		
		if( primeP.compareTo(one) < 0)
		{
			System.err.println("Invalid input");
			System.err.println("p and q cannot be less than 1");
			System.exit(1);
		}
		
	}
	private void isTwo(BigInteger N){
		if( N.compareTo(two) == 0)
		{
			System.err.println("Invalid input");
			System.err.println("p and q  cannot be 2");
			System.exit(1);
		}
	}
	private void isPAndQEqual(BigInteger p, BigInteger q){
		if( p.compareTo(q) == 0)
		{
			System.err.println("Invalid input");
			System.err.println("p and q cannot be the same number");
			System.exit(1);
		}
	}

	
}
