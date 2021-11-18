import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is responsible of being an observer for <code>CityRepository</code>, to get notified about every city
 * addition / movement. It also implements the strategy pattern to select the algorithm for connecting the cities at
 * runtime.
 *
 * @author Aru Raghuwanshi
 * @version 1.0
 * @since 2021-11-12
 */
public class ConnectionContext implements Observer {

	private ConnectionStrategy connectionStrategy;

	public ConnectionContext() {
	}
	
	/**
	 * This is responsible for initializing which strategy would be used.
	 *
	 * @param connectionStrategy
	 */
	public void setStrategy(ConnectionStrategy connectionStrategy) {
		RouteRepository.getInstance().getRouteList().clear();
		this.connectionStrategy = connectionStrategy;
		executeStrategy();
	}
	
	/**
	 * This is responsible to execute the selected strategy and update the connections in <code>RouteRepository</code>
	 */
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
