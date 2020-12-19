package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Comparator;
import java.util.Map.Entry;

public class HashMapHistogramComperator<T extends Comparable<T>> implements Comparator<Entry<T, Integer>>{

	/*
	Compares its two arguments for order. 
	Returns a negative integer, zero, or a positive integer עכ
	as the first argument is less than, equal to, or greater than the second.
	*/
	
	@Override
	public int compare(Entry<T, Integer> e1, Entry<T, Integer> e2) {
		T e1Key = e1.getKey();
		T e2Key = e2.getKey();
		int e1Value = e1.getValue();
		int e2Value = e2.getValue();
		
		if (e1Value == e2Value) {
			return e1Key.compareTo(e2Key);
		}
		if (e1Value > e2Value) {
			return -1;
		} else {
			return 1;
		}
	}

}
