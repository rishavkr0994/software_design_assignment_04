import java.awt.*;

/** Some Text Here
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public abstract class ShapeDecorator implements ShapeInterface {

    private final ShapeInterface shape;

    public ShapeDecorator(ShapeInterface shape) {
        this.shape = shape;
    }

    public int getX() {
        return this.shape.getX();
    }

    public int getY() {
        return this.shape.getY();
    }

    public Dimension getDimension() {
        return this.shape.getDimension();
    }

    public void move(int x, int y) {
        this.shape.move(x, y);
    }

    public void setDimension(int width, int height) {
        this.shape.setDimension(width, height);
    }

    public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
    }

    public void draw(Graphics2D g, Color color, String label) {
        this.shape.draw(g, color, label);
    }

    public ShapeInterface getBaseShape() {
        return shape != null ? shape.getBaseShape() : null;
    }
}
