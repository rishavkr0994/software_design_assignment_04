import java.awt.event.MouseEvent;

/** A strategy implementation for mouse action listeners of the <code>WorkSpacePanel</code> class to move an existing
 * city
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class ActionMoveOperation extends ActionStrategy {

    private int preX, preY;

    /**
     * Invoked when a mouse button has been pressed on a component.
     * <p>
     * It invokes this method in the parent class and if a city was clicked, it initiates the city move operation
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (clickedCity != null) {
            preX = clickedCity.getX() - e.getX();
            preY = clickedCity.getY() - e.getY();
            CityRepository.getInstance().moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
        }
    }

    /**
     * Invoked when a mouse button is pressed on a component and then dragged. {@code MOUSE_DRAGGED} events will
     * continue to be delivered to the component where the drag originated until the mouse button is released
     * (regardless of whether the mouse position is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,{@code MOUSE_DRAGGED} events may not be delivered during
     * a native Drag&amp;Drop operation.
     * <p>
     * If a city movement is in progress, it updates the co-ordinates of the moving city to the co-ordinates of the
     * current cursor location.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (clickedCity != null)
            CityRepository.getInstance().moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     * <p>
     * Commits the city movement operation and updates the city co-ordinates to the co-ordinates of the current cursor
     * location.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickedCity != null) {
            CityRepository.getInstance().moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
            clickedCity = null;
        }
    }
}
