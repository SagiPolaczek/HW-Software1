package il.ac.tau.cs.sw1.ex4;

import java.util.Random;

public class MyRandom extends Random{


	private int[] intRands;

	private int nextIntRandIndex;
	private int nextFloatRandIndex;

	private float[] floatRands;

	public MyRandom(int[] intRands, float[] floatRands){

		this.intRands = intRands;

		this.floatRands = floatRands;

	}

	

	@Override
	public float nextFloat() {

		return floatRands[(nextFloatRandIndex++)%floatRands.length];

	}



	@Override
	public int nextInt(int n) {
		if (intRands[(nextIntRandIndex+1)%intRands.length] >= n){
			System.out.println("intRands contains a number that is too large number");
		}
		return intRands[(nextIntRandIndex++)%intRands.length];
	}
}