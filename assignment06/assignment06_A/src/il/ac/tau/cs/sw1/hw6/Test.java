package il.ac.tau.cs.sw1.hw6;

public class Test {
	
public static void main(String[] args) {
		
		
		Polynomial p1 = new Polynomial(); // p1 = 0
		
		printError(p1.getDegree() == 0,
				"the degree of p1 is 0");
		
		printError(p1.computePolynomial(3) == 0.0,
				"the value of p1 with x=3 is 0.0");
		
		double[] coefficients = new double[]{1.0,2.0,3.0};
		Polynomial p2 = new Polynomial(coefficients); // p2 = 1.0+2.0*x+3.0*x^2
		
		printError(p2.computePolynomial(3) == 34.0,
				"the value of p2 with x=3 is 34.0");
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		Polynomial p3 = p2.multiply(2.0);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 */
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		printError(p3.getCoefficient(1) == 4.0,
				"the coefficient of x is 4.0");
		
		
		Polynomial p4 = p2.adds(p3);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 * p4 = 3.0+6.0*x+9.0*x^2
		 */
		printError(p4.getDegree() == 2,
				"the degree of p4 is 2");
		
		Polynomial p5 = p4.getFirstDerivation();
		
		/*
		 * p4 = 3.0+6.0*x+9.0*x^2
		 * p5 = 6.0 + 18*x
		 */
		
		printError(p5.getDegree() == 1,
				"the degree of p5 is 1");
		
		double[] p6Coeffs = {6.0,0.0,0.0,42.0,0.0,24.3,0.0};
		Polynomial p6 = new Polynomial(p6Coeffs); // 6 + 42x^3 + 24.3x^5 
		
		if (p6.isExtrema(0)) {
			System.out.println("[Error] p6 is not Extrema");
		}
		if (p6.isExtrema(42)) {
			System.out.println("[Error] p6 is not Extrema");
		}
		if (p6.getDegree() != 5) {
			System.out.println("[Error] p6 getDegree. should be 5 and you got " + p6.getDegree());
		}
		
		double[] p7Coeffs = {14.0,0.0,0.0,-2.0,0.0,-4.3,20.0,0.0};
		Polynomial p7 = new Polynomial(p7Coeffs); // 14 -2x^3 -4.3x^5 + 20x^6
		
		Polynomial p8 = p6.adds(p7); //  12 + 40x^3 + 20x^5 +20x^6
		Polynomial p9 = p8.multiply(0.05); //  1 + 2x^3 + x^5 + x^6
		
		if (p9.computePolynomial(2) != 113) {
			System.out.println("[Error] compute p9 with x=2 should result 113, and you got "+ p9.computePolynomial(2));
		}
		
		if (p9.getDegree() != 6) {
			System.out.println("[Error] p9 getDegree. should be 6 and you got " + p6.getDegree());
		}
		
		if (p9.isExtrema(0)) {
			System.out.println("[Error] p9 is not Extrema.");
		}
		
		System.out.println("DONE!");
//		printPolynomial(p9);
	}

	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}
	
	public static void printPolynomial(Polynomial p) {
		for(int i = 0; i < p.poli.length; i++) {
			System.out.print("(" + p.poli[i] + " * x ^ " + i + ") + ");
		}
	}
}
