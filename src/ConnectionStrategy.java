import java.util.ArrayList;
import java.util.List;

public abstract class ConnectionStrategy {
	public abstract List<List<Route>> solve(List<City> cityList);
	
	public List<Route> getRouteList(List<City> cityList) {
		List<Route> routeList = new ArrayList<>();
		
		int prev = 0;
		for(int i = 1; i < cityList.size(); i++) {
			Route route = new Route(cityList.get(prev), cityList.get(i));
			// route.setDist(TSPPro.getEuclideanDistance(cityList.get(prev), cityList.get(i)));
			routeList.add(route);
		}
		return routeList;
 	}
	
	public List<Route> solveTSPNN(List<City> cityList) {
		List<City> cityListCopy = new ArrayList<>(cityList);

		int x1, x2, y1, y2;
		float currentDistance = Float.MAX_VALUE;
		City minCity = null;
		float minDistance = Float.MAX_VALUE;
		
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

			x1 = (int)minCity.getX();
			y1 = (int)minCity.getY();

			minDistance = Integer.MAX_VALUE;
		}

		return getRouteList(resultCityList);
	}
}
