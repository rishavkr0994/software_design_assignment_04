import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for executing the connection strategy using the TSP Nearest Neighbour algorithm.
 *
 * @author Aru Raghuwanshi
 * @version 1.0
 * @since 2021-11-12
 */
public class ConnectionTSPNearestNeighbour extends ConnectionStrategy {
	
	/**
	 * This method solves the TSP Nearest Neighbour and returns it in a desired data structure format.
	 *
	 * @param cityList
	 * @return nested list of routes
	 */
	public List<List<Route>> solve(List<City> cityList) {
		List<List<Route>> routeList2 = new ArrayList<>();
		routeList2.add(solveTSPNN(cityList));
		return routeList2;
	}

	/**
	 * This method is a helper for solving the TSP Nearest Neighbour using a Greedy Approach.
	 *
	 * @param cityList
	 * @return list of Routes
	 */
	public List<Route> solveTSPNN(List<City> cityList) {
		List<City> cityListCopy = new ArrayList<>(cityList);

		int x1, x2, y1, y2;
		float currentDistance = Float.MAX_VALUE;
		City minCity = null;
		float minDistance = Float.MAX_VALUE;
		
		if (cityListCopy.size() == 0) {
			return new ArrayList<Route>();
		}
		x1 = cityListCopy.get(0).getX();
		y1 = cityListCopy.get(0).getY();
		int counter = 0;
		
		List<City> resultCityList = new ArrayList<>();

		while (cityListCopy.size() > 0) {
			for (City city : cityListCopy) {
				x2 = (int) city.getX();
				y2 = (int) city.getY();

				currentDistance = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)* (y1 - y2));
				if (currentDistance < minDistance) {
					minDistance = currentDistance;
					minCity = city;
				}
			}

			resultCityList.add(minCity);

			cityListCopy.remove(minCity);

			x1 = (int) minCity.getX();
			y1 = (int) minCity.getY();

			minDistance = Integer.MAX_VALUE;
		}

		return super.getRouteList(resultCityList);
	}
}
