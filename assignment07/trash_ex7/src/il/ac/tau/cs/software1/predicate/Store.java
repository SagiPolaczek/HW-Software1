package il.ac.tau.cs.software1.predicate;

import java.util.List;

public class Store<T extends Product> {
	private List<T> inventory;
	
	public Store(List <T> inventory) {
		this.inventory = inventory;
	}

	public List<T> getInventory() {
		return inventory;
	}

	public String getInventoryDescription() { // Q4
		String description = "";
		List<T> inventory = this.getInventory();
		for (T item : inventory) {
			description += item.getDescription();
		}
		return description; 
	}

	public void transform(Predicate<T> pred, Action<T> action) { // Q5
		List<T> inventory = this.getInventory();
		for (T item : inventory) {
			if (pred.test(item)) {
				action.performAction(item);
			}
		}
	}
}
