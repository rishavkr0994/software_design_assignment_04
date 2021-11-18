import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is responsible of being an observer for Workspace, to get notified about every city addition.
 * This class also acts as an observable for WorkSpacePanel which basically draws everytime a city is updated.
 * @author Aru Raghuwanshi
 *
 */
public class ConnectionContext implements Observer {

	private ConnectionStrategy connectionStrategy;

	public ConnectionContext() {
	}
	
	/**
	 * This is responsible for initializing which strategy would be used.
	 * @param connectionStrategy
	 */
	public void setStrategy(ConnectionStrategy connectionStrategy) {
		RouteRepository.getInstance().getRouteList().clear();
		this.connectionStrategy = connectionStrategy;
		executeStrategy();
	}
	
	/**
	 * This is responsible to notify the observers and execute the selected strategy.
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
