package il.ac.tau.cs.software1.predicate;


public class SmartPhone implements Product {
	private double price;
	private String name;
	private int version;
	
	public SmartPhone(String name, double price, int version) {
		this.name = name;
		setPrice(price);
		this.version = version;
	}
	
	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double newPrice) {
		if (newPrice >= 0) {
			price = newPrice;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	public int getVersion() {
		return version;
	}

	public void upgrade() {
		version += 1;
	}

}
