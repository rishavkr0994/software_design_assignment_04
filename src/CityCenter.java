import java.awt.*;

/**
 * The concrete component in the Decorator pattern. It is used to implement the base shape for the city, to which the
 * decorations can be applied.
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public class CityCenter implements ShapeInterface {

    private final Rectangle rect;

    /**
     * Constructor for city center.
     * @param x x coordinate.
     * @param y y coordinate.
     * @param width width of city.
     * @param height height of city.
     */
    public CityCenter(int x, int y, int width, int height) {
        this.rect = new Rectangle(x, y, width, height);
    }

    /**
     * Getter for x coordinate.
     * @return x coordinate.
     */
    public int getX() {
        return this.rect.x;
    }

    /**
     * Getter for y coordinate.
     * @return y coordinate.
     */
    public int getY() {
        return this.rect.y; 
    }

    /**
     * Getter for size.
     * @return dimension of city.
     */
    public Dimension getDimension() {
        return this.rect.getSize();
    }

    /**
     * Move the city.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public void move(int x, int y) {
        this.rect.x = x;
        this.rect.y = y;
    }

    /**
     * Set new size.
     * @param width new width.
     * @param height new height.
     */
    public void setDimension(int width, int height) {
        this.rect.width = width;
        this.rect.height = height;
    }

    /**
     * Check if city contains mouse click.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return if city contains mouse click.
     */
    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    /**
     * Plots the city on the GUI.
     * @param g the graphics panel.
     * @param color color of the shape.
     * @param label name of the city.
     */
    public void draw(Graphics2D g, Color color, String label) {
        g.setColor(color);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.fill(rect);
        g.setColor(Color.black);
        g.drawString(label, rect.x + rect.width + 5, rect.y - 5);
    }

    /**
     * Get the base implementation of city.
     * @return this city center object.
     */
    public ShapeInterface getBaseShape() { return this; }
}
