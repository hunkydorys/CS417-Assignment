
import java.math.BigInteger;


public class SquareRootTest  {

	final static SquareRoot sqRoot = new SquareRoot();
	
	public static void main (String args []){
		System.out.println("Test Case one :");
		testOne();
		System.out.println("Test Case Two :");
		testTwo();
		System.out.println("Test Case Three :");
		testThree();
		System.out.println("Test Case Four :");
		testFour();
		System.out.println("Test Case Five :");
		testFive();
		System.out.println("Test Case Six :");
		testSix();
	}

	public static void testOne() {

		BigInteger numA = new BigInteger("7");
		BigInteger prime = new BigInteger("29");
		
		BigInteger[] resultActual = sqRoot.squareRootAModP(numA, prime);
		printResults(resultActual, numA, prime);

	}

	public static void testTwo() {
		BigInteger numA = new BigInteger("3");
		BigInteger prime = new BigInteger("30275821");
		
		BigInteger[] resultActual = sqRoot.squareRootAModP(numA, prime);
		printResults(resultActual, numA, prime);

	}

	public static void testThree() {
		BigInteger numA = new BigInteger("49");
		BigInteger prime = new BigInteger("13");
		BigInteger primeQ = new BigInteger("5");
		
		BigInteger[] resultActual = sqRoot.squareRootAModN(numA, prime, primeQ);
		printResults(resultActual, numA, prime,primeQ);

	}

	public  static void testFour() {
		BigInteger numA = new BigInteger(
				"136715507000698606329724732805054724697961483039918414844922");
		BigInteger prime = new BigInteger(
				"1505986403115242551816089251500984969054810234820659958025407");
		
		BigInteger[] resultActual = sqRoot.squareRootAModP(numA, prime);
		printResults(resultActual, numA, prime);

	}

	public static void testFive() {
		BigInteger numA = new BigInteger("177327");
		BigInteger prime = new BigInteger("936361");
		BigInteger primeQ = new BigInteger("724639");
		BigInteger[] resultActual = sqRoot.squareRootAModN(numA, prime, primeQ);
		printResults(resultActual, numA, prime,primeQ);

	}

	public static void testSix() {
		BigInteger numA = new BigInteger(
				"4488327511042357449965619973907491412063190192332803886062522826747676728216398993791861201301166478038489963929135460037618097990222935721239914826660265728429510289570117458218964065891827591764219701510745054543850741516699485793616173090979104812295662113957374144464438658931912575055322994491945");
		BigInteger prime = new BigInteger(
				"6786545568303067747599775419671723189469956261935111630285277330081322914884318900690617533298616741149441373754749477735361835658567005571184649719195865274422518348312519534181302536747753093467213752679543034977429499588352972189108036266903492590273389311329757181814781240972274750632978152777209");
		BigInteger primeQ = new BigInteger(
				"9251775375736802037510964725943008422894706345370118732488960326215873510073470851694468225356875875505741528159770774997271036459137792267407037101126956273358275595940113350090710049613392051356080326445133174299923203254282623030364964212677235833359679263985409103005877364934113921716990343313621");
		BigInteger[] resultActual = sqRoot.squareRootAModN(numA, prime, primeQ);
		printResults(resultActual, numA, prime, primeQ);

	}

	public static void printResults(BigInteger [] resultActual,BigInteger numA, BigInteger prime) {
		for (int i = 0; i < resultActual.length; i++) {
			System.out.println(resultActual[i] + " - answer is verified to be "
					+ sqRoot.verifyAnswer(numA, prime, resultActual[i]));
		}
		System.out.println();
	}
	
	public static void printResults(BigInteger [] resultActual,BigInteger numA, BigInteger prime, BigInteger primeQ) {
		BigInteger N = prime.multiply(primeQ);
		printResults(resultActual, numA, N);
	}
}
