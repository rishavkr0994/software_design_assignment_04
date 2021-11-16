public class RouteFactory extends AbstractRouteFactory {
    private static RouteFactory _instance = null;

    private RouteFactory() {
    }

    public static RouteFactory getInstance(){
        if (_instance == null)
            _instance = new RouteFactory();
        return _instance;
    }

    @Override
    public Route getRoute(City src, City dest) {
        return new Route(src, dest);
    }
}
