package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		List<Spaceship> temp = new ArrayList<Spaceship>();
		for (Spaceship s : fleet) {
			temp.add(s);
		}
		Collections.sort(temp, new MySpaceshipComparator());
		
		List<String> res = new ArrayList<String>();
		for (Spaceship s : temp) {
			res.add(s.toString());
		}
		return res;
	}
	
	
	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> res = new HashMap<String, Integer>();
		
		for (Spaceship s : fleet) {
			String sClass = s.getClass().getSimpleName();
			if (res.containsKey(sClass)) {
				res.put(sClass, res.get(sClass)+1);
			}
			else {
				res.put(sClass, 1);
			}
		}
		
		return res;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int res = 0;
		for (Spaceship s : fleet) {
			res += s.getAnnualMaintenanceCost();
		}
		
		return res;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> res = new HashSet<String>();
		for (Spaceship s : fleet) {
			if (s.getWeapons() != null) {
				for (Weapon w : s.getWeapons()) {
					res.add(w.getName());
				}
			}
		}
		return res;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int res = 0;
		for (Spaceship s : fleet) {
			res += s.getCrewMembers().size();
		}
		
		return res;

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		float sumAge = 0;
		float count = 0;
		for (Spaceship s : fleet) {
			Set<? extends CrewMember> crew = s.getCrewMembers();
			for (CrewMember m : crew) {
				if (m.getClass().getSimpleName().equals("Officer")) {
					sumAge += m.getAge();
					count++;
				}
			}
		}
		
		return sumAge/count;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> res = new HashMap<Officer, Spaceship>();
		for (Spaceship s : fleet) {
			List<Officer> officers = new ArrayList<Officer>();
			for (CrewMember m : s.getCrewMembers()) {
				if (m.getClass().getSimpleName().equals("Officer")) {
					officers.add((Officer) m);
				}
			}
			if (officers.size() > 0) {
				// Sorting in a descending order
				officers.sort((Officer o1, Officer o2) -> o2.getRank().compareTo(o1.getRank()));
				res.put(officers.get(0), s);
			}
		}
		return res;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		Map<OfficerRank, Integer> rankCount = new HashMap<OfficerRank, Integer>();
		for (Spaceship s : fleet) {
			for (CrewMember m : s.getCrewMembers()) {
				if (m.getClass().getSimpleName().equals("Officer")) {
					Officer o = (Officer)m;
					OfficerRank currRank = o.getRank();
					if (rankCount.containsKey(currRank)) {
						rankCount.put(currRank, rankCount.get(currRank)+1);
					} else { // Rank doesn't in map
						rankCount.put(currRank, 1);
					}
				}
			}
		}
		// Now rankCount Map is fully initialiaze
		List<Map.Entry<OfficerRank, Integer>> res = new ArrayList<Map.Entry<OfficerRank, Integer>>();
		for (Map.Entry<OfficerRank,Integer> entry : rankCount.entrySet()) {
			res.add(entry);
		}
		
		res.sort(new MyOfficerComparator());
		
		return res;
	}
}
