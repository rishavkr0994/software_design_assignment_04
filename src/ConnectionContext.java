import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ConnectionContext extends Observable implements Observer {
	private ConnectionStrategy connectionStrategy;

	public ConnectionContext() {
	}
	
	public void setStrategy(ConnectionStrategy connectionStrategy) {
		WorkSpace.getInstance().getRouteList().clear();
		this.connectionStrategy = connectionStrategy;
		executeStrategy();
	}
	
	public void executeStrategy() {
		if (connectionStrategy != null) {
			List<City> cityList = WorkSpace.getInstance().getCityList();
			List<List<Route>> routeList = cityList.size() > 0 ? connectionStrategy.solve(cityList) : new ArrayList<>();
			WorkSpace.getInstance().setRouteList(routeList);
		}
		setChanged();
		notifyObservers();
	}

	@Override
    public void update(Observable o, Object arg) {
		executeStrategy();
    }
}
