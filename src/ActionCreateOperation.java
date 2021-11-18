import javax.swing.*;
import java.awt.event.MouseEvent;

/** A strategy implementation for mouse action listeners of the <code>WorkSpacePanel</code> class to create a new city
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class ActionCreateOperation extends ActionStrategy {

    private final JFrame parentFrame;

    /**
     * Initializes the <code>parentFrame</code> member variable with the <code>parent</code> parameter
     *
     * @param parent the parent <code>JFrame</code> object
     */
    public ActionCreateOperation(JFrame parent) {
        parentFrame = parent;
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * <p>
     * It invokes this method in the parent class and if no city was clicked, it creates a new city by requesting the
     * user for a city name (through an input dialog)
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (clickedCity == null) {
            String cityName = JOptionPane.showInputDialog(parentFrame, "Enter City Name");
            if (cityName != null && !cityName.isEmpty()) {
                City city = CityFactory.getInstance().getCity(cityName, e.getX(), e.getY());
                CityRepository.getInstance().addNewCity(city);
            }
        }
    }
}
