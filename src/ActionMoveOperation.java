import java.awt.event.MouseEvent;

public class ActionMoveOperation extends ActionStrategy {
    private int preX, preY;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (clickedCity != null) {
            preX = clickedCity.getX() - e.getX();
            preY = clickedCity.getY() - e.getY();
            CityRepository.getInstance().moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (clickedCity != null)
            CityRepository.getInstance().moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickedCity != null) {
            CityRepository.getInstance().moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
            clickedCity = null;
        }
    }
}
