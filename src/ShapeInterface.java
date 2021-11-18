import java.awt.*;

/** Some Text Here
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
