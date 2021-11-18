/** An abstract factory specification for implementing a factory which generates <code>City</code> instances
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public abstract class AbstractCityFactory {

    /**
     * Creates an instance of <code>City</code> with the specified attributes and returns it
     *
     * @param label city name
     * @param x x-coordinate of the upper left corner of the city rectangle
     * @param y y-coordinate of the upper left corner of the city rectangle
     * @return instance of <code>City</code>
     */
    public abstract City getCity(String label, int x, int y);
}
