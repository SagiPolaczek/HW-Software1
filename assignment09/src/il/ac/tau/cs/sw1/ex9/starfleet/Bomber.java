package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends MySpaceship{

	List<Weapon> weapons;
	int numberOfTechnicians;
	static final int BASE_ANNUAL_COST = 5000;
	
	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
	
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
		this.numberOfTechnicians = numberOfTechnicians;
	}

	@Override
	public List<Weapon> getWeapons() {
		return this.weapons;
	}
	
	@Override
	public int getFirePower() {
		int totalFirePower = super.getFirePower();
		for (Weapon w : weapons) {
			totalFirePower += w.getFirePower();
		}
		
		return totalFirePower;
	}
	
	public int getNumberOfTechnicians() {
		return this.numberOfTechnicians;
	}
	
	@Override
	public int getAnnualMaintenanceCost() {
		int totalCost = BASE_ANNUAL_COST;
		int weaponsCost = 0;
		for (Weapon w : weapons) {
			weaponsCost += w.getAnnualMaintenanceCost();
		}
		
		weaponsCost = (int)Math.round((weaponsCost) *(1 - (0.1)*numberOfTechnicians));
		totalCost += weaponsCost;
		
		return totalCost;
		
	}
	
	@Override
	public String toString() {
		String s = super.toString() + "\n" +
				   "\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				   "\tWeaponArray=" + this.getWeapons() + "\n" +
				   "\tNumberOfTechnicians=" + this.getNumberOfTechnicians();
		return s;
	}
	
	
	
	


}
