package enumRiddles;

enum Day {
	MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

	private final int number;
	
	private Day(int number) {
		this.number = number;
	}

	public Day next(){
		   return Day.values()[(this.getDayNumber()) % 7];
	   }

	int getDayNumber() {
		return number;
	}
	
	
}

public class DayTest {
	public static void main(String[] args) {
		for (Day day : Day.values()) {
			System.out.printf("%s (%d), next is %s\n", day, day.getDayNumber(), day.next());
		}
	}
}
