import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

/**
 * A panel to display the cities and the route between them. It allows marking of a new city with a mouse click and also
 * allows to move an existing city to a new location by clicking the city and dragging it to a new location.
 * <p>
 * It implements the <tt>Observer</tt> interface so that it can observe an <tt><Observable</tt> (in our case, it is the
 * TSP class) and redraw the panel.
 *
 * @author Zhuoran Li, Rishav Kumar, Aru Raghwanshi
 * @version 1.0
 * @since 2021-10-02
 */
public class WorkSpacePanel extends JPanel implements MouseListener, MouseMotionListener, Observer {
    private ActionStrategy mouseActionStrategy;

    /**
     * Default constructor. It initializes the <code>workSpace</code> object and defines the listeners for the mouse
     * actions.
     */
    public WorkSpacePanel() {
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void setMouseActionStrategy(ActionStrategy mouseActionStrategy) {
        this.mouseActionStrategy = mouseActionStrategy;
    }

    /**
     * Plots the cities (as rectangles) and routes (as lines) onto the display area. The city names are also displayed
     * alongside the cities.
     *
     * @param g graphics object to plot the content
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        List<List<Route>> routeList2 = WorkSpace.getInstance().getRouteList();

        for(List<Route> routeList : routeList2) {
        	if (routeList != null && routeList.size() > 0) {
        		for (Route route : routeList)
        			route.getSrc().drawConnect(route.getDest(), g2);
        	}
        }

        for (City city : WorkSpace.getInstance().getCityList())
            city.draw(g2);

    }

    /**
     * This method is called whenever the observed object is changed. An application calls an <tt>Observable</tt>
     * object's <code>notifyObservers</code> method to have all the object's observers notified of the change.
     * <p>
     * This function gets the route information from an <tt>Observable</tt> and call the <code>repaint</code> function
     * to re-draw the panel.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            City clickedCity = WorkSpace.getInstance().getCityList().stream()
                    .filter(x -> x.contains(e.getX(), e.getY()))
                    .findFirst().orElse(null);

            if (clickedCity != null) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                EditCityDialog editCityDialog = new EditCityDialog(parentFrame, clickedCity);
                editCityDialog.setVisible(true);
                if (editCityDialog.getUpdated())
                    repaint();
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * <p>
     * A new city is marked when the user clicks on an empty space, else if the user clicks on a city, the city movement
     * operation is initiated.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mouseActionStrategy.mousePressed(e);
    }

    /**
     * Invoked when a mouse button is pressed on a component and then dragged. {@code MOUSE_DRAGGED} events will
     * continue to be delivered to the component where the drag originated until the mouse button is released
     * (regardless of whether the mouse position is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,{@code MOUSE_DRAGGED} events may not be delivered during
     * a native Drag&amp;Drop operation.
     * <p>
     * If a city movement is in progress, it updates the co-ordinates of the moving city based on the current cursor
     * location on screen.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseActionStrategy.mouseDragged(e);
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) { }

    /**
     * Invoked when a mouse button has been released on a component.
     * <p>
     * Commits the city movement operation and updates the city co-ordinates to the current cursor location on screen.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseActionStrategy.mouseReleased(e);
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) { }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) { }
}
