import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Strategy pattern and acts as the base abstract class for enabling the solution to implement a strategy.
 * This also hosts a common function getRouteList which converts the cities to the required data structure.
 *
 * @author Aru Raghuwanshi
 * @version 1.0
 * @since 2021-11-12
 */
public abstract class ConnectionStrategy {

	/**
	 * This abstract method is responsible executing the chosen Strategy for the connection.
	 *
	 * @param cityList
	 * @return
	 */
	public abstract List<List<Route>> solve(List<City> cityList);
	
	/**
	 * This method is responsible for converting a given list of cities into the required data-structure
	 * for processing the connection strategy.
	 *
	 * @param cityList
	 * @return
	 */
	public List<Route> getRouteList(List<City> cityList) {
		List<Route> routeList = new ArrayList<>();
		
		int prev = 0;
		for(int i = 1; i < cityList.size(); i++) {
			Route route = RouteFactory.getInstance().getRoute(cityList.get(prev), cityList.get(i));
			routeList.add(route);
			prev = i;
		}
		return routeList;
 	}
}
