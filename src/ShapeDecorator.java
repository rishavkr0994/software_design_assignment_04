import java.awt.*;

/**
 * The abstract decorator class in the Decorator pattern, which is used to create concrete decorators. It is used to
 * implement the Decorator pattern for the city shape.
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public abstract class ShapeDecorator implements ShapeInterface {

    private final ShapeInterface shape;

    /**
     * Constructor, sets the base implementation.
     * @param shape base implementation.
     */
    public ShapeDecorator(ShapeInterface shape) {
        this.shape = shape;
    }

    /**
     * Returns x coordinate of base.
     * @return x coordinate.
     */
    public int getX() {
        return this.shape.getX();
    }

    /**
     * Returns y coordinate of base.
     * @return y coordinate.
     */
    public int getY() {
        return this.shape.getY();
    }

    /**
     * Returns size of base.
     * @return dimensions of base.
     */
    public Dimension getDimension() {
        return this.shape.getDimension();
    }

    /**
     * Move the base to new coordinates.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public void move(int x, int y) {
        this.shape.move(x, y);
    }

    /**
     * Set new size for base.
     * @param width new width.
     * @param height new height.
     */
    public void setDimension(int width, int height) {
        this.shape.setDimension(width, height);
    }

    /**
     * Check if city contains mouse click.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return if city contains mouse click.
     */
    public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
    }

    /**
     * Plots the city on the GUI.
     * @param g the graphics panel.
     * @param color color of the shape.
     * @param label name of the city.
     */
    public void draw(Graphics2D g, Color color, String label) {
        this.shape.draw(g, color, label);
    }

    /**
     * Return the base implementation.
     * @return base implementation.
     */
    public ShapeInterface getBaseShape() {
        return shape != null ? shape.getBaseShape() : null;
    }
}
