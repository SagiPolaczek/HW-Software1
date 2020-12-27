package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public interface Spaceship extends Comparable<Spaceship> {
	String getName();
	int getCommissionYear();
	float getMaximalSpeed();
	int getFirePower();
	Set<? extends CrewMember> getCrewMembers();
	int getAnnualMaintenanceCost();
	List<Weapon> getWeapons();
}
