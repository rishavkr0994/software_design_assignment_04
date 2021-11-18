import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class RouteRepository extends Observable implements Container{
    private List<List<Route>> routeList = new ArrayList<>();
    private Route danglingRoute = null;

    private RouteRepository() { }

    public static RouteRepository _instance = null;
    public static synchronized RouteRepository getInstance() {
        if (_instance == null)
            _instance = new RouteRepository();
        return _instance;
    }

    public void addNewRoute(Route route) {
        if (routeList.size() == 0)
            routeList.add(new ArrayList<>());
        routeList.get(0).add(route);

        setChanged();
        notifyObservers();
    }

    /**
     * Get the route information.
     * @return route information
     */
    public List<List<Route>> getRouteList() {
        return routeList;
    }

    /**
     * Set the route information.
     * @param routeList route information
     */
    public void setRouteList(List<List<Route>> routeList) {
        this.routeList = routeList;
        setChanged();
        notifyObservers();
    }

    /**
     * Get the dangling route.
     * @return dangling route
     */
    public Route getDanglingRoute() {
        return danglingRoute;
    }

    /**
     * Set the dangling route.
     * @param route dangling route
     */
    public void setDanglingRoute(Route route) {
        this.danglingRoute = route;
        setChanged();
        notifyObservers();
    }

    @Override
    public Iterator getIterator() {
        return new Iterator() {
            int index;

            @Override
            public boolean hasNext() {
                if(index < routeList.size()){
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                if(this.hasNext()){
                    return routeList.get(index++);
                }
                return null;
            }
        };
    }
}
