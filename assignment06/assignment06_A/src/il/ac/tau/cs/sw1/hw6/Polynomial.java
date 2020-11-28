package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class Polynomial {
	
	double[] poli;
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		this.poli = new double[1];
		poli[0] = 0;
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		this.poli = coefficients;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int n = Math.max(this.poli.length, polynomial.poli.length);
		double[] newCoeff = new double[n];
		for (int i = 0; i < this.poli.length; i++) {
			newCoeff[i] = newCoeff[i] + this.poli[i];
		}
		for (int j = 0; j < polynomial.poli.length; j++) {
			newCoeff[j] = newCoeff[j] + polynomial.poli[j];
		}
		Polynomial resultPoli = new Polynomial(newCoeff);
		
		return resultPoli;
		
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] newCoeff = new double[this.poli.length];
		for (int i = 0; i < newCoeff.length; i++) {
			newCoeff[i] = this.poli[i] * a;
		}
		
		Polynomial resultPoly = new Polynomial(newCoeff);
		return resultPoly;
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		if (this.poli.length <= 1) {
			return 0;
		}
		for (int n = this.poli.length -1; n > 0; n--) {
			if (this.poli[n] != 0) {
				return n;
			}
		}
		return 0;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		int degree = this.getDegree();
		if (n-1 <= degree) {
			return this.poli[n];
		} else {
			return 0;
		}
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if (n-1 <= this.poli.length) {
			this.poli[n] = c;
		} else {
			double[] newCoeffs = new double[n+1];
			for (int i = 0; i < this.poli.length; i++) {
				newCoeffs[i] = this.poli[i];
			}
			newCoeffs[n] = c;
			this.poli = newCoeffs;
		}
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		double[] dCoeffs = new double[this.poli.length -1];
		for (int i = 0; i < dCoeffs.length; i++) {
			dCoeffs[i] = (i+1) * poli[i+1];
		}
		Polynomial firstD = new Polynomial(dCoeffs);
		return firstD;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		double result = 0;
		for (int i = 0; i < this.poli.length; i++) {
			result = result + Math.pow(x, i) * this.poli[i];
		}
		return result;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x) {
		Polynomial firstD = getFirstDerivation();
		Polynomial secondD = getSecondDerivation();

		if (firstD.computePolynomial(x) == 0 & secondD.computePolynomial(x) != 0) {
			return true;
		}
		return false;

	}	
	
	public Polynomial getSecondDerivation() {
		Polynomial firstD = new Polynomial(getFirstDerivation().poli);
		Polynomial secondD = firstD.getFirstDerivation();
		return secondD;
	}	

    
    

}
