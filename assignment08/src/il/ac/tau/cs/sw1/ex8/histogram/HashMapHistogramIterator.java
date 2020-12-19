package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**************************************
 * Add your code to this class !!! *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T> {

	LinkedList<Entry<T, Integer>> entryList = new LinkedList<Entry<T, Integer>>();
	int i;
	int n;

	public HashMapHistogramIterator(Set<Entry<T, Integer>> entrySet) {
		for (Map.Entry<T, Integer> entry : entrySet) {
			entryList.add(entry);
		}
		Collections.sort(entryList, new HashMapHistogramComperator<T>());
		this.i = 0;
		this.n = entryList.size();
	}

	
	
	
	@Override
	public boolean hasNext() {
		return (i < n);
	}

	@Override
	public T next() {
		T res = entryList.get(i).getKey();
		i++;
		return res;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); // no need to change this
	}
}
