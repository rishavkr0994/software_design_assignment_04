import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/** Responsible for managing the route database. Also implements the iterator.
 *
 * @author
 * @version 1.0
 * @since 2021-11-12
 */

public class RouteRepository extends Observable implements Container{
    private List<List<Route>> routeList = new ArrayList<>();
    private Route danglingRoute = null;

    private RouteRepository() { }

    public static RouteRepository _instance = null;

    /**
     * Used to make the route repo a singleton.
     * @return the same instance of route repo.
     */
    public static synchronized RouteRepository getInstance() {
        if (_instance == null)
            _instance = new RouteRepository();
        return _instance;
    }

    /**
     * Adds a new route to the route list.
     * @param route a route object.
     */
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

    /**
     * Returns the iterator for the route repo.
     * @return a new instance of the iterator.
     */
    @Override
    public Iterator getIterator() {
        return new ObjectIterator<>(routeList);
    }
}
