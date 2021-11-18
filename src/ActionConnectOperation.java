import java.awt.event.MouseEvent;

/** A strategy implementation for mouse action listeners of the <code>WorkSpacePanel</code> class to connect two cities.
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class ActionConnectOperation extends ActionStrategy {

    /**
     * Invoked when a mouse button is pressed on a component and then dragged. {@code MOUSE_DRAGGED} events will
     * continue to be delivered to the component where the drag originated until the mouse button is released
     * (regardless of whether the mouse position is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,{@code MOUSE_DRAGGED} events may not be delivered during
     * a native Drag&amp;Drop operation.
     * <p>
     * If a city was clicked, it displays a temporary (dangling) route from the co-ordinates of the clicked city to the
     * co-ordinates of the current cursor location.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (clickedCity != null) {
            City ghostCity = CityFactory.getInstance().getCity("Ghost", e.getX(), e.getY());
            Route route = RouteFactory.getInstance().getRoute(clickedCity, ghostCity);
            RouteRepository.getInstance().setDanglingRoute(route);
        }
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     * <p>
     * If a city was clicked and the click is released when the cursor is on another city, it draws a route between the
     * two cities.
     *
     * @param e the event to be processed
     */
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
