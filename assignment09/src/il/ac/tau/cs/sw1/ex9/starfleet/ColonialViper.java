package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter {
	
	static final int BASE_ANNUAL_COST = 4000;

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int totalCost = BASE_ANNUAL_COST;
		for (Weapon w : weapons) {
			totalCost += w.getAnnualMaintenanceCost();
		}
		totalCost += 500 * crewMembers.size();
		totalCost += (int)Math.round(500 * maximalSpeed);
		
		return totalCost;
	}
	
	
}
