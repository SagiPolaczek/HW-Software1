package il.ac.tau.cs.sw1.ex4;

import java.util.Scanner;

public class testingTests {

	public static void main(String[] args) {
		System.out.println("--- Setting stage ---");
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter your hiding probability:");
			double prob = userInput.nextDouble();
			System.out.println(prob);
	}
}
