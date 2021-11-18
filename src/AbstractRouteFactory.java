/** An abstract factory specification for implementing a factory which generates <code>Route</code> instances
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public abstract class AbstractRouteFactory {
    /**
     * Creates an instance of <code>Route</code> between the specified source and destination cities and returns it
     *
     * @param src source city
     * @param dest destination city
     * @return instance of <code>Route</code>
     */
    public abstract Route getRoute(City src, City dest);
}
