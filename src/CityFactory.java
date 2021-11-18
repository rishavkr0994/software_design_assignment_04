/** A concrete factory which extends <code>AbstractCityFactory</code>. It also implements the Singleton pattern to
 * provide for common access to a factory for generating <code>City</code> instances.
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class CityFactory extends AbstractCityFactory {
    private static CityFactory _instance = null;

    private CityFactory() {
    }

    /**
     * Gets the common instance of <code>CityFactory</code>.
     * NOTE: When this function is first invoked, a new instance is created and the same is returned. In future
     * invocations, the previously created instance is returned.
     *
     * @return instance of <code>CityFactory</code>
     */
    public static CityFactory getInstance(){
        if (_instance == null)
            _instance = new CityFactory();
        return _instance;
    }

    /**
     * Creates an instance of <code>City</code> with the specified attributes and returns it
     *
     * @param label city name
     * @param x x-coordinate of the upper left corner of the city rectangle
     * @param y y-coordinate of the upper left corner of the city rectangle
     * @return instance of <code>City</code>
     */
    @Override
    public City getCity(String label, int x, int y) {
        return new City(label, x, y);
    }
}
