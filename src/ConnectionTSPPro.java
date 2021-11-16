import java.util.ArrayList;
import java.util.List;

public class ConnectionTSPPro extends ConnectionStrategy {
	public List<List<Route>> solve(List<City> cityList) {
		TSPPro tspPro = new TSPPro(cityList);
		List<City> shortestPath = tspPro.calculateShortestPath();
		List<List<Route>> routeList2 = new ArrayList<>();
		routeList2.add(super.getRouteList(shortestPath));
		return routeList2;
	}
}
