This is an assignment on computing modulo square roots  

TestCaseResults.txt - contains the results I calculated for the given test cases. I computed all the 
the square roots for each test case and I verified each answer.

The source code is contained in the src folder.
Class Files
(1) MainProgram.java - this class contains the main logic of the program
(2) Validate.java - this class validates the input 
(3) SquareRoot.java - this class contains the code for calculating the modular square root and verifying if 
the answer is correct
(4)SquareRootTest.java - this is a class which computes the modular square roots, verifies the ansers and 
prints out the answers

The program is compiled by the following command -

	javac MainProgram.java SquareRoot.java Validate.java SquareRootTest.java

MainProgram is run, for example by calling the following command -

	java MainProgram 1 3 5
	
SquareRootTest is run by calling the following command -

		java SquareRootTest