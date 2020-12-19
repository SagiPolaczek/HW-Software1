package il.ac.tau.cs.sw1.ex8.histogram;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	HashMap<T, Integer> myMap = new HashMap<T, Integer>();
	
	
	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<T>(myMap.entrySet());
	}

	@Override
	public void addItem(T item) {
		// If item in map we add 1, else put 1.
		int itemValue = myMap.getOrDefault(item, 0) + 1;
		myMap.put(item, itemValue);
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		if (myMap.getOrDefault(item, 0) == 0) {
			throw new IllegalItemException();
		}
		myMap.remove(item);
		
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if (k < 1) {
			throw new IllegalKValueException(k);
		}
		int itemNewValue = myMap.getOrDefault(item, 0) + k;
		myMap.put(item, itemNewValue);
		
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		int currValue = myMap.getOrDefault(item, 0);
		// item doesn't exist in myMap
		if (currValue == 0) {
			throw new IllegalItemException();
		}
		// item exist but 'k' value is illegal.
		if ((k < 1) || currValue < k) {
			throw new IllegalKValueException(k);
		}
		// item's value equals to k, thus we remove it.
		if (currValue == k) {
			myMap.remove(item);
		} else {
			myMap.put(item, currValue - k);
		}
		
	}

	@Override
	public int getCountForItem(T item) {
		return myMap.getOrDefault(item, 0);
	}

	@Override
	public void addAll(Collection<T> items) {
		for (T item : items) {
			addItem(item);
		}
	}

	@Override
	public void clear() {
		myMap.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return myMap.keySet();
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		for (T item : anotherHistogram.getItemsSet()) {
			try {
				this.addItemKTimes(item, anotherHistogram.getCountForItem(item));
				
			} catch (IllegalKValueException e) {
				System.out.println("Should have never get here.");
			}
		}
	}
	

}
