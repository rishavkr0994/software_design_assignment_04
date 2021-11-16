import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//Observer for 
public class ConnectionContext extends Observable implements Observer {

	private ConnectionStrategy connectionStrategy;
	
	List<List<Route>> routeList;
	
	public ConnectionContext()
	{
		this.connectionStrategy = null;
		this.routeList = new ArrayList<>();
	}
	
	public void setStrategy(ConnectionStrategy connectionStrategy)
	{
		this.connectionStrategy = connectionStrategy;
	}
	
	public void executeStrategy(ConnectionStrategy connectionStrategy, List<City> cityList)
	{
		connectionStrategy.solve(cityList);
	}

	@Override
    public void update(Observable o, Object arg) {
        List<City> cityList = ((WorkSpace)o).getCityList();
        if (cityList.size() > 0)
        {
//        	routeList = calculateShortestRoute(cityList);
        	routeList = connectionStrategy.solve(cityList);
        }
        else routeList = new ArrayList<>();

        setChanged();
        notifyObservers();
    }
	
	public List<List<Route>> getRouteList() {
        return routeList;
    }
}
