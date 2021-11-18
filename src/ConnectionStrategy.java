import java.util.ArrayList;
import java.util.List;
/**
 * Implements the Strategy pattern and acts as the base abstract class for enabling the solution to implement a strategy.
 * This also hosts a common function getRouteList which converts the cities to the required data structure.
 *
 * @author Aru Raghuwanshi
 */
public abstract class ConnectionStrategy {
	/**
	 * This abstract method is responsible executing the chosen Strategy for the connection.
	 * @param cityList
	 * @return
	 */
	public abstract List<List<Route>> solve(List<City> cityList);
	
	/**
	 * This method is responsible for converting a given list of cities into the required data-structure
	 * for processing the connection strategy.
	 * @param cityList
	 * @return
	 */
	public List<Route> getRouteList(List<City> cityList) {
		List<Route> routeList = new ArrayList<>();
		
		int prev = 0;
		for(int i = 1; i < cityList.size(); i++) {
			Route route = RouteFactory.getInstance().getRoute(cityList.get(prev), cityList.get(i));
			// route.setDist(TSPPro.getEuclideanDistance(cityList.get(prev), cityList.get(i)));
			routeList.add(route);
			prev = i;
		}
		return routeList;
 	}
	
//	public List<Route> sotspNNlveTSPNN(List<City> cityList) {
//		List<City> cityListCopy = new ArrayList<>(cityList);
//
//		int x1, x2, y1, y2;
//		float currentDistance = Float.MAX_VALUE;
//		City minCity = null;
//		float minDistance = Float.MAX_VALUE;
//		
//		if(cityListCopy.size() == 0)
//		{
//			return new ArrayList<Route>();
//		}
//		x1 = cityListCopy.get(0).getX();
//		y1 = cityListCopy.get(0).getY();
//		int counter = 0;
//		
//		List<City> resultCityList = new ArrayList<>();
//
//		while (cityListCopy.size() > 0) {
//			for (City city : cityListCopy) {
//				x2 = (int) city.getX();
//				y2 = (int) city.getY();
//
//				currentDistance = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)* (y1 - y2));
//				if (currentDistance < minDistance) {
//					minDistance = currentDistance;
//					minCity = city;
//				}
//			}
//
//			resultCityList.add(minCity);
//
//			cityListCopy.remove(minCity);
//
//			x1 = (int)minCity.getX();
//			y1 = (int)minCity.getY();
//
//			minDistance = Integer.MAX_VALUE;
//		}
//
//		return getRouteList(resultCityList);
//	}
}
