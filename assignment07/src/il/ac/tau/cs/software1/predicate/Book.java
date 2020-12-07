package il.ac.tau.cs.software1.predicate;

public class Book implements Product {

private double price;	
private String name;
private String author;

	public Book(String name, double price, String author) {
		this.name = name;
		setPrice(price);
		this.author = author;
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
	
	public String getAuthor() {
		return author;
	}

}
