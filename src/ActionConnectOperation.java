import java.awt.event.MouseEvent;

public class ActionConnectOperation extends ActionStrategy {
    @Override
    public void mouseDragged(MouseEvent e) {
        if (clickedCity != null) {
            City ghostCity = CityFactory.getInstance().getCity("Ghost", e.getX(), e.getY());
            Route route = RouteFactory.getInstance().getRoute(clickedCity, ghostCity);
            RouteRepository.getInstance().setDanglingRoute(route);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickedCity != null) {
            City destinationCity = CityRepository.getInstance().getCityList().stream()
                    .filter(x -> x.contains(e.getX(), e.getY()))
                    .findFirst().orElse(null);

            if (destinationCity != null && destinationCity != clickedCity) {
                Route route = RouteFactory.getInstance().getRoute(clickedCity, destinationCity);
                RouteRepository.getInstance().addNewRoute(route);
            }
            RouteRepository.getInstance().setDanglingRoute(null);
            clickedCity = null;
        }
    }
}
