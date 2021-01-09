package riddles;


public class A implements Comparable<A> {
	
	protected int i;
	protected int j;

	public A(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int hashCode() {
		return this.i;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.hashCode() == obj.hashCode()) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(A o) {
		if (this.j == o.j) {
			return 0;
		}
		return 1;
	}
	
	
	public String toString() {return "("+this.i+" "+this.j+")";}



}
