import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ConnectionContext implements Observer {
	private ConnectionStrategy connectionStrategy;

	public ConnectionContext() {
	}
	
	public void setStrategy(ConnectionStrategy connectionStrategy) {
		RouteRepository.getInstance().getRouteList().clear();
		this.connectionStrategy = connectionStrategy;
		executeStrategy();
	}
	
	public void executeStrategy() {
		if (connectionStrategy != null) {
			List<City> cityList = CityRepository.getInstance().getCityList();
			List<List<Route>> routeList = cityList.size() > 0 ? connectionStrategy.solve(cityList) : new ArrayList<>();
			RouteRepository.getInstance().setRouteList(routeList);
		}
	}

	@Override
    public void update(Observable o, Object arg) {
		executeStrategy();
    }
}
