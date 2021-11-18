import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for computing the required permutations and calculating the shortest path using the actual
 * solution to the TSP.
 *
 * @author Aru Raghuwanshi
 * @version 1.0
 * @since 2021-11-12
 */
public class ConnectionTSPPro extends ConnectionStrategy {
	
	List<City> cityList;
	List<List<City>> permutations = new ArrayList<>();

    /**
     *
     * @param cityList
     * @return
     */
	public List<List<Route>> solve(List<City> cityList) {
		this.cityList = cityList;
		List<City> shortestPath = calculateShortestPath();
		List<List<Route>> routeList2 = new ArrayList<>();
		routeList2.add(super.getRouteList(shortestPath));
		return routeList2;
	}

	private void generatePermutations() {     
        permute(new ArrayList<City>());
    }
    
    private void permute(List<City> current) {
        if (current.size() == cityList.size()) {
            permutations.add(new ArrayList<>(current));
        } else {
            for (int i = 0; i < cityList.size(); i++) {
                if (!current.contains(cityList.get(i))) {
                    current.add(cityList.get(i));
                    permute(current);
                    current.remove(current.size()-1);
                }
            }
        }
    }
    
    /**
     * This method calculates the shortestPath by leveraging the generated permutations and distance algorithms.
     *
     * @return
     */
    private List<City> calculateShortestPath() {
    	List<City> shortestPath = new ArrayList<>();
    	
    	generatePermutations();
    	
    	double minDistance = Integer.MAX_VALUE;

    	for (List<City> cities : permutations) {
    		int prev = 0;
    		double currentDistance = 0;
    		for(int i = 1; i < cities.size(); i++) {
    			currentDistance += getEuclideanDistance(cities.get(prev),cities.get(i));
    			prev = i;
    		}
    		
    		if (currentDistance < minDistance) {
    			minDistance = currentDistance;
    			shortestPath = new ArrayList<>(cities);
    		}
    	}
    	
    	return shortestPath;
    }
    
    private double getEuclideanDistance(City src, City dest) {
        double x1 = src.getX(), y1 = src.getY(), x2 = dest.getX(), y2 = dest.getY();
        return Math.sqrt((x1 + x2) * Math.abs(x1 - x2) + (y1 + y2) * Math.abs(y1 - y2));
    }
}
