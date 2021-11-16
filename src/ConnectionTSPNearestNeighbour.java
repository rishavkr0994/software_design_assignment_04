import java.util.ArrayList;
import java.util.List;

public class ConnectionTSPNearestNeighbour extends ConnectionStrategy{

	@Override
	public List<List<Route>> solve(List<City> cityList) {
		
		List<List<Route>> routeList2 = new ArrayList<>();
		routeList2.add(super.solveTSPNN(cityList));
		return routeList2;
	}
	
	

}
