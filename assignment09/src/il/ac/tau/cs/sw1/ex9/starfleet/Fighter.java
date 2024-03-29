package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends MySpaceship{
	
	static final int BASE_ANNUAL_COST = 2500;
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
	}

	
	@Override
	public int getFirePower() {
		int totalFirePower = super.getFirePower();
		for (Weapon w : weapons) {
			totalFirePower += w.getFirePower();
		}
		
		return totalFirePower;
	}
	
	@Override
	public int getAnnualMaintenanceCost() {
		int totalCost = BASE_ANNUAL_COST;
		for (Weapon w : weapons) {
			totalCost += w.getAnnualMaintenanceCost();
		}
		totalCost += (int)Math.round(1000 * maximalSpeed);
		
		return totalCost;
		
	}
	
	@Override
	public String toString() {
		String s = super.toString() +"\n"+
				   "\tAnnualMaintenanceCost=" + this.getAnnualMaintenanceCost() + "\n" +
				   "\tWeaponArray=" + this.getWeapons();
		
		return s;
			
	}
	
}
