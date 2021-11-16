public class CityFactory extends AbstractCityFactory {
    private static CityFactory _instance = null;

    private CityFactory() {
    }

    public static CityFactory getInstance(){
        if (_instance == null)
            _instance = new CityFactory();
        return _instance;
    }

    @Override
    public City getCity(String label, int x, int y) {
        return new City(label, x, y);
    }
}
