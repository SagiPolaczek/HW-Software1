package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfEven = 0;
		int n = Integer.parseInt(args[0]);
		int f1 = 1;
		int f2 = 1;
		int temp;
		int[] myFibonacci = new int[n];
		myFibonacci[0] = f1;
		myFibonacci[1] = f2;
		for (int i = 2; i < n; i++) {
			temp = f1;
			f1 += f2;
			f2 = temp;
			myFibonacci[i] = f1;
			if (f1 % 2 == 0) {
				numOfEven += 1;
			}
		}
		
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		for (int i = 0; i < n-1; i++) {
			System.out.print(myFibonacci[i] + " ");
		}
		System.out.println(myFibonacci[n-1]);
		System.out.println("The number of even numbers is: "+numOfEven);

	}

}
