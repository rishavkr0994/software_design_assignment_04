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
                City city = new City(cityName, e.getX(), e.getY(), WorkSpace.DEFAULT_CITY_WIDTH,
                        WorkSpace.DEFAULT_CITY_HEIGHT, WorkSpace.DEFAULT_CITY_COLOR);
                WorkSpace.getInstance().addNewCity(city);
            }
        }
    }
}
