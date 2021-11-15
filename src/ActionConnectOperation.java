import java.awt.event.MouseEvent;

public class ActionConnectOperation extends ActionStrategy {
    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickedCity != null) {
            City destinationCity = WorkSpace.getInstance().getCityList().stream()
                    .filter(x -> x.contains(e.getX(), e.getY()))
                    .findFirst().orElse(null);

            if (destinationCity != null && destinationCity != clickedCity) {
                Route route = new Route();
                route.setSrc(clickedCity);
                route.setDest(destinationCity);
                WorkSpace.getInstance().addNewRoute(route);
            }
            clickedCity = null;
        }
    }
}
