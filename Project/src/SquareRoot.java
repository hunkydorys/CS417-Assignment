//package assignmentOne;

import java.math.BigInteger;

public class SquareRoot {

	final private BigInteger zero = BigInteger.ZERO;
	final private BigInteger one = BigInteger.ONE;
	final private BigInteger two = new BigInteger("2");
	final private BigInteger minusOne = new BigInteger("-1");

	public BigInteger[] squareRootAModP(BigInteger numA, BigInteger primeP) {
		

		// easy case for when p = 3 mod 4
		BigInteger modRes = primeP.mod(new BigInteger("4"));
		BigInteger result[] = new BigInteger[2];
		if (modRes.equals(new BigInteger("3"))) {
			result[0] = squareRootAModPEasy(numA, primeP);
		} else {
			// harder case for when p = 1 mod 4
			result[0] = squareRootAModPHard(numA, primeP);
		}
		result[1] = returnOtherSquareRoot(result[0], primeP);

		return result;
	}
	
	public BigInteger[] squareRootAModN(BigInteger numA, BigInteger primeP,
			BigInteger primeQ) {
		

		BigInteger[] resultP = squareRootAModP(numA, primeP);
		BigInteger[] resultQ = squareRootAModP(numA, primeQ);

		BigInteger[] results = new BigInteger[4];

		results[0] = chineseRemainderThereom(resultP[0], resultQ[0], primeP,
				primeQ);
		results[1] = chineseRemainderThereom(resultP[1], resultQ[0], primeP,
				primeQ);
		results[2] = chineseRemainderThereom(resultP[1], resultQ[1], primeP,
				primeQ);
		results[3] = chineseRemainderThereom(resultP[0], resultQ[1], primeP,
				primeQ);

		
		return results;
	}
	
	public boolean verifyAnswer(BigInteger a, BigInteger primeP,
			BigInteger answer) {
		BigInteger result = answer.modPow(two, primeP);

		return result.compareTo(a) == 0;
	}

	//for the case were p = 3 mod 4 
	private BigInteger squareRootAModPEasy(BigInteger numA, BigInteger primeP) {
		//answer is a^(p+1)/4
		BigInteger exponent = primeP.add(one);
		exponent = exponent.divide(new BigInteger("4"));
		BigInteger result = numA.modPow(exponent, primeP);
		return result;
	}

	//for the case were p = 1 mod 4
	private BigInteger squareRootAModPHard(BigInteger numA, BigInteger primeP) {

		// find  b mod p which is a quadratic non-residue
		BigInteger bModP = BigInteger.ONE;
		while (isQuadraticResidue(bModP, primeP))
			bModP = bModP.add(one);

		// compute (2^l)m = (p-1)/2
		BigInteger pMinusOne = primeP.subtract(one).divide(two);
		BigInteger l = zero;
		BigInteger m = pMinusOne;

		// finding l and m
		while (m.mod(two).compareTo(zero) == 0) {
			m = m.divide(two);
			l = l.add(one);
		}

		BigInteger r = two.pow(l.intValue()).multiply(m);
		BigInteger rDash = BigInteger.ZERO;
		// needed later one for the if statement
		BigInteger minusOneModP = new BigInteger("-1").mod(primeP);

		// for(i = l ; i > 0 ; i--)
		for (BigInteger i = l; i.compareTo(BigInteger.ZERO) > 0; i = i
				.subtract(one)) {
			r = r.divide(two);
			rDash = rDash.divide(two);
			// (a^r) mod p
			BigInteger aPowR = numA.modPow(r, primeP);
			// (b^r') mod p
			BigInteger bPowRDash = bModP.modPow(rDash, primeP);
			// (a^r)(b^r') mod p
			BigInteger aPowRxBPowRDash = aPowR.multiply(bPowRDash).mod(primeP);
			// maintain the invaraint (a^r)(b^r') = 1 mod p
			if (aPowRxBPowRDash.compareTo(minusOneModP) == 0) {
				BigInteger twoToPowLxM = two.pow(l.intValue()).multiply(m);
				rDash = rDash.add(twoToPowLxM);
			}
		}

		return returnResult(r, rDash, numA, bModP, primeP);
	}

	private BigInteger returnResult(BigInteger r, BigInteger rDash,
			BigInteger a, BigInteger b, BigInteger primeP) {

		// Now r = m, r' is even, and (a^r)(b^r') = 1 mod p
		BigInteger rPlusOne = (r.add(one).divide(two));
		// a^(r+1)/2 mod p
		BigInteger aPowRPlusOne = a.modPow(rPlusOne, primeP);
		// b^(r'/2) mod p
		BigInteger bPowRDash = b.modPow(rDash.divide(two), primeP);
		// answer = (a^(r+1)/2)(b^(r'/2)) mod p
		BigInteger result = aPowRPlusOne.multiply(bPowRDash).mod(primeP); 

		return result;
	}

	private BigInteger chineseRemainderThereom(BigInteger resultP,
			BigInteger resultQ, BigInteger primeP, BigInteger primeQ) {
		
		// Chinese Remainder Theroem
		BigInteger M = primeP.multiply(primeQ);
		BigInteger M1 = M.divide(primeP);
		BigInteger M2 = M.divide(primeQ);
		BigInteger N1 = M1.modInverse(primeP);
		BigInteger N2 = M2.modInverse(primeQ);
		// K1 = (a1)(M1)(N1)
		BigInteger K1 = resultP.multiply(M1).multiply(N1);
		BigInteger K2 = resultQ.multiply(M2).multiply(N2);
		// K = K1 + K2 mod M
		BigInteger K = K1.add(K2).mod(M);
		return K;
	}

	//returning the other square root which is -x mod p
	private BigInteger returnOtherSquareRoot(BigInteger result, BigInteger prime) {

		BigInteger doubleResult = result.multiply(minusOne);
		//BigInteger negResult = result.subtract(doubleResult);
		BigInteger newResult = doubleResult.mod(prime);

		return newResult;
	}

	//check for quadratic residuosity
	private boolean isQuadraticResidue(BigInteger a, BigInteger p) {
		BigInteger exponent = p.subtract(one);
		exponent = exponent.divide(two);
		BigInteger result = a.modPow(exponent, p);

		if (result.compareTo(one) == 0) {
			return true;
		}
		return false;
	}
	

	
}
