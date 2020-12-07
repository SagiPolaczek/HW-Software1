package il.ac.tau.cs.software1.predicate;

public interface Predicate<T extends Product> {
	boolean test(T product);
}
