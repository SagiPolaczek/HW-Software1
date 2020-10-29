package il.ac.tau.cs.sw1.ex2;
import java.lang.Math;

public class Assignment02Q02 {

	public static void main(String[] args) {
		double piEstimation = 0.0;
		
		int p = Integer.parseInt(args[0]);
		for (int i = 0; i < p; i++) {
			piEstimation += Math.pow(-1, i) * 4 * (1.0 / ((2*i) +1));
		}
		// Sum of i=0 to p of: 4 * (-1)^i * 1/(2i +1) = π
		
		System.out.println(piEstimation + " " + Math.PI);

	}

}
