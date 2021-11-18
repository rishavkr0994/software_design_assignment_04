/** A concrete factory which extends <code>AbstractRouteFactory</code>. It also implements the Singleton pattern to
 * provide for common access to a factory for generating <code>Route</code> instances.
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class RouteFactory extends AbstractRouteFactory {

    private static RouteFactory _instance = null;

    private RouteFactory() {
    }

    /**
     * Gets the common instance of <code>RouteFactory</code>.
     * <p>
     * NOTE: When this function is first invoked, a new instance is created and the same is returned. In future
     * invocations, the previously created instance is returned.
     *
     * @return instance of <code>RouteFactory</code>
     */
    public static RouteFactory getInstance(){
        if (_instance == null)
            _instance = new RouteFactory();
        return _instance;
    }

    /**
     * Creates an instance of <code>Route</code> between the specified source and destination cities and returns it
     *
     * @param src source city
     * @param dest destination city
     * @return instance of <code>Route</code>
     */
    @Override
    public Route getRoute(City src, City dest) {
        return new Route(src, dest);
    }
}
