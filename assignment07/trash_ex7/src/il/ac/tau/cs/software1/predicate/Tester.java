package il.ac.tau.cs.software1.predicate;

import java.util.ArrayList;
import java.util.List;

public class Tester {
	private static Store<Book> bookStore;
	private static Store<SmartPhone> phoneStore;
	
	
	// Create a book store and a phone store for testing
		public static void init() {
			List<Book> books = new ArrayList<>();
			books.add(new Book("Nineteen Eighty-Four", 1984, "Orwell"));
			books.add(new Book("Slaughterhouse-Five", 5, "Vonnegut"));
			books.add(new Book("Catch-22", 22, "Heller"));
			books.add(new Book("Java 8 Lambdas", 8, "Warburton"));
			bookStore = new Store<>(books);

			List<SmartPhone>phones = new ArrayList<>();
			phones.add(new SmartPhone("iphone", 999, 9));
			phones.add(new SmartPhone("Nexus", 666, 6));
			phones.add(new SmartPhone("Xiaomi", 333, 3));
			phones.add(new SmartPhone("Pixel", 111, 1));
			phoneStore = new Store<>(phones);
		}

		public static void main(String[] args) {
			init();
			ByAuthor byAuthor = new ByAuthor('h');
			ByPrice byPrice = new ByPrice(500);
			Discount discount = new Discount(50);
			Upgrade upgrade = new Upgrade();
			
			
			// test 1
			if (Product.getTotalPrice(bookStore.getInventory()) == 2019 && Product.getTotalPrice(phoneStore.getInventory()) == 2109) {
				System.out.println("Test 1 correct");
			}
			else {
				System.out.println("Test 1 Error!");
			}
			
			
			// test 2
			bookStore.transform(byAuthor, (Action<Book>) discount);
			List<Book> bI = bookStore.getInventory();
			if (bI.size() == 4 && bI.get(0).getPrice() == 1984 &&  bI.get(1).getPrice() == 5 && bI.get(2).getPrice() == 11 && bI.get(3).getPrice() == 8) {
				System.out.println("Test 2 correct");
			}
			else {
				System.out.println("Test 2 Error!");
				System.out.println(bookStore.getInventoryDescription());
			}
			
			
			// test 3
			phoneStore.transform(byPrice, upgrade);
			List<SmartPhone> pI = phoneStore.getInventory();
			
			if (pI.size() == 4 && pI.get(0).getVersion() == 9 &&  pI.get(1).getVersion() == 6 && pI.get(2).getVersion() == 4 && pI.get(3).getVersion() == 2) {
				System.out.println("Test 3 correct");
			}
			else {
				System.out.println("Test 3 Error!");
				System.out.println(phoneStore.getInventoryDescription());
			}
			
			
			// test 4
			List<Book> twoBooks = new ArrayList<>();
			twoBooks.add(new Book("Nineteen Eighty-Four", 1984, "Orwell"));
			twoBooks.add(new Book("Slaughterhouse-Five", 5, "Vonnegut"));
			bookStore = new Store<>(twoBooks);
			
			String Description = String.format("Name: Nineteen Eighty-Four%n" + 
					"Price: 1984.0%n" + 
					"Name: Slaughterhouse-Five%n" + 
					"Price: 5.0%n");
			if (bookStore.getInventoryDescription().equals(Description)) {
				System.out.println("Test 4 correct");
			}
			else {
				System.out.println("Test 4 Error!");
				System.out.println(bookStore.getInventoryDescription());
			}
		}

	}
