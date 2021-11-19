import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for solving the connection strategy using the clustering algorithm.
 *
 * @author Aru Raghuwanshi
 * @version 1.0
 * @since 2021-11-12
 */
public class ConnectionClustering extends ConnectionStrategy {

	/**
	 *
	 * @param cityList
	 * @return
	 */
	public List<List<Route>> solve(List<City> cityList) {
		int maxX = cityList.get(0).getX();
		int maxY = cityList.get(0).getY();
		int minX = cityList.get(0).getX();
		int minY = cityList.get(0).getY();
		
		for(int i = 1; i < cityList.size(); i++) {
			maxX = Math.max(maxX, cityList.get(i).getX());
			maxY = Math.max(maxY, cityList.get(i).getY());
			minX = Math.min(minX, cityList.get(i).getX());
			minY = Math.min(minY, cityList.get(i).getY());
		}
		
		List<List<Route>> routeList2 = new ArrayList<>();
		ConnectionTSPNearestNeighbour tspNN = new ConnectionTSPNearestNeighbour();
//		List<City> leftTopCluster = getCluster(cityList, minX, (maxY+minY)/2, (maxX+minX)/2, maxY);
//		List<City> rightTopCluster = getCluster(cityList, (maxX+minX)/2, (maxY+minY)/2, maxX, maxY)
//		List<City> 
		routeList2.add(tspNN.solveTSPNN(getCluster(cityList, minX, (maxY+minY)/2, (maxX+minX)/2, maxY)));
		routeList2.add(tspNN.solveTSPNN(getCluster(cityList, (maxX+minX)/2, (maxY+minY)/2, maxX, maxY)));
		routeList2.add(tspNN.solveTSPNN(getCluster(cityList, minX, minY, (maxX+minX)/2, (maxY+minY)/2)));
		routeList2.add(tspNN.solveTSPNN(getCluster(cityList, (maxX+minX)/2, minY, maxX, (maxY+minY)/2)));
		
		return routeList2;
	}
	
	private List<City> getCluster(List<City> cityList, int minX, int minY, int maxX, int maxY) {
		List<City> cluster = new ArrayList<>();
		for (City city : cityList) {
			if (city.getX() <= maxX && city.getX() >= minX && city.getY() <= maxY && city.getY() >= minY) {
				cluster.add(city);
			}
		}
		return cluster;
	}
}
