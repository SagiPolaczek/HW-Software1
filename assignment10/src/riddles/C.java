package riddles;

public class C extends B {
	
	private int i;
	private int j;

	public C(int i, int j) {
		super(i,j);
		
	}

	@Override
	public int compareTo(A other) {
		int justHereToResolveWarnings = i+j;
		if (i == Double.POSITIVE_INFINITY) {
			System.out.println(justHereToResolveWarnings);
		}
		return -1;
	}



}