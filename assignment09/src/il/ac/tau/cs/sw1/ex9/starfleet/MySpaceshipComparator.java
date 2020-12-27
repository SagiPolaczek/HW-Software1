package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;

public class MySpaceshipComparator implements Comparator<Spaceship>{

	@Override
	public int compare(Spaceship s1, Spaceship s2) {
		int fire1 = s1.getFirePower();
		int fire2 = s2.getFirePower();
		if (fire1 != fire2) {
			return Integer.compare(fire2, fire1);
		}
		
		int comm1 = s1.getCommissionYear();
		int comm2 = s2.getCommissionYear();
		if (comm1 != comm2) {
			return Integer.compare(comm2, comm1);
		}
		String name1 = s1.getName();
		String name2 = s2.getName();
		
		return name1.compareTo(name2);
	}

}
