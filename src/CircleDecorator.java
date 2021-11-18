import java.awt.*;
import java.awt.geom.Ellipse2D;

/** This class creates the circle decorator.
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public class CircleDecorator extends ShapeDecorator {

    private final Ellipse2D circle;

    /**
     * Constructor for circle decorator.
     * @param shape the base implementation that we want to decorate.
     */
    public CircleDecorator(ShapeInterface shape) {
        super(shape);
        this.circle = new Ellipse2D.Float(super.getX(), super.getY(), super.getDimension().width,
                super.getDimension().height);
    }

    /**
     * Move the decorator.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    @Override
    public void move(int x, int y) {
        super.move(x, y);
        this.circle.setFrame(super.getX(), super.getY(), super.getDimension().width, super.getDimension().height);
    }

    /**
     * Change size of decorator.
     * @param width new width of shape.
     * @param height new height of shape.
     */
    @Override
    public void setDimension(int width, int height) {
        super.setDimension(width, height);
    }

    /**
     * Used to check if mouse click lands in shape.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return if mouse has clicked in shape or not.
     */
    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y) || this.circle.contains(x, y);
    }

    /**
     * Plots the shape on the GUI.
     * @param g the graphics panel.
     * @param color color of the shape.
     * @param label name of the city.
     */
    @Override
    public void draw(Graphics2D g, Color color, String label) {
        super.draw(g, color, label);
        g.setColor(Color.white);
        g.drawOval((int)circle.getX(), (int)circle.getY(), super.getDimension().width, super.getDimension().height);
        g.fill(circle);
    }
}
