package il.ac.tau.cs.software1.predicate;

public class ByAuthor implements Predicate<Book> {
	private char letter;

	public ByAuthor(char letter) { // Q2
		this.letter = letter;
	}

	@Override
	public boolean test(Book book) { // Q2
		String authorName = book.getAuthor();
		if (!(authorName.length() > 0)) {
			return false;
		}
		char firstLetter = Character.toLowerCase(authorName.charAt(0));
		
		return this.letter == firstLetter;
	}
}