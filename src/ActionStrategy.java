import java.awt.event.MouseEvent;

public abstract class ActionStrategy {
    protected City clickedCity = null;

    void mousePressed(MouseEvent e) {
        clickedCity = WorkSpace.getInstance().getCityList().stream()
                .filter(x -> x.contains(e.getX(), e.getY()))
                .findFirst().orElse(null);
    }

    void mouseDragged(MouseEvent e) {
    }

    void mouseReleased(MouseEvent e) {
    }
}
