package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q01 {

	public static void main(String[] args) {
		for (String s : args) {
			char c = s.charAt(0);
			int valC = (int) c;
			if ((valC % 2 == 0) && (valC % 3 == 0)) {
				System.out.println(c);
			}
		}
	}
}
