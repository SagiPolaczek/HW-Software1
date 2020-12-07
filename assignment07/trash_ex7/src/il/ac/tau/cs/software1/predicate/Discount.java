package il.ac.tau.cs.software1.predicate;

public class Discount implements Action<Book> {
	private double percentage;
	
	public Discount(double percentage) { // Q3
		this.percentage = percentage;
	}
	
	
	@Override
	public void performAction(Book book) { // Q3
		double currPrice = book.getPrice();
		double newPrice = (currPrice * percentage)/100d;
		
		book.setPrice(newPrice);
	}
	
}
