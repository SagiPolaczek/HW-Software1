package il.ac.tau.cs.software1.predicate;

public interface Action<T extends Product> {
	void performAction(T product);
}
