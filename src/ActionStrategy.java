import java.awt.event.MouseEvent;

/** An abstract strategy specification for mouse action listeners of the <code>WorkSpacePanel</code> class
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public abstract class ActionStrategy {

    protected City clickedCity = null;

    /**
     * Invoked when a mouse button has been pressed on a component.
     * <p>
     * Captures the city instance if a city is clicked.
     *
     * @param e the event to be processed
     */
    void mousePressed(MouseEvent e) {
        clickedCity = CityRepository.getInstance().getCityList().stream()
                .filter(x -> x.contains(e.getX(), e.getY()))
                .findFirst().orElse(null);
    }

    /**
     * Invoked when a mouse button is pressed on a component and then dragged. {@code MOUSE_DRAGGED} events will
     * continue to be delivered to the component where the drag originated until the mouse button is released
     * (regardless of whether the mouse position is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,{@code MOUSE_DRAGGED} events may not be delivered during
     * a native Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    void mouseDragged(MouseEvent e) {
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    void mouseReleased(MouseEvent e) {
    }
}
