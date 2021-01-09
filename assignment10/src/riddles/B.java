package riddles;

public class B extends A{
	
	protected int i;
	protected int j;


	public B(int i, int j) {
		super(i,j);
	
	}


	@Override
	public int hashCode() {

		return j;
		
	}


	@Override
	public boolean equals(Object obj) {
		
		if (this.hashCode() == obj.hashCode()) {
			return true;
		}
		return false;
	}

}
