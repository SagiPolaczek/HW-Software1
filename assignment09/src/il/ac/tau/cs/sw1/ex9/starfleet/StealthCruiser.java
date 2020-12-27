package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter{
	static int totalStealths;
	static List<Weapon> defaultWeaponList = defaultWeaponList();
	
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		totalStealths++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this(name, commissionYear, maximalSpeed, crewMembers, defaultWeaponList);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int totalCost = super.getAnnualMaintenanceCost();
		totalCost += totalStealths * 50 ;
		return totalCost;
	}
	
	public static List<Weapon> defaultWeaponList() {
		List<Weapon> theList = new ArrayList<Weapon>();
		theList.add(new Weapon("Laser Cannons", 10, 100));
		
		return theList;
	}
	
}
