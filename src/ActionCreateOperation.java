import javax.swing.*;
import java.awt.event.MouseEvent;

public class ActionCreateOperation extends ActionStrategy {
    private final JFrame parentFrame;

    public ActionCreateOperation(JFrame parent) {
        parentFrame = parent;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (clickedCity == null) {
            String cityName = JOptionPane.showInputDialog(parentFrame, "Enter City Name");
            if (cityName != null && !cityName.isEmpty()) {
                City city = CityFactory.getInstance().getCity(cityName, e.getX(), e.getY());
                WorkSpace.getInstance().addNewCity(city);
            }
        }
    }
}
