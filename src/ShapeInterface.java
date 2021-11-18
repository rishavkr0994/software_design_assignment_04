import java.awt.*;

/**
 * The component interface in the Decorator pattern. It is used to implement the Decorator pattern for the city shape.
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
interface ShapeInterface {

    int getX();

    int getY();

    Dimension getDimension();

    void move(int x, int y);

    void setDimension(int width, int height);

    boolean contains(int x, int y);

    void draw(Graphics2D g, Color color, String label);

    ShapeInterface getBaseShape();
}
