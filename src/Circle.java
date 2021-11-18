import java.awt.*;
import java.awt.geom.Ellipse2D;

/** Some Text Here
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public class Circle extends ShapeDecorator {

    private final Ellipse2D circle;

    public Circle(ShapeInterface shape) {
        super(shape);
        this.circle = new Ellipse2D.Float(super.getX(), super.getY(), super.getDimension().width,
                super.getDimension().height);
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        this.circle.setFrame(super.getX(), super.getY(), super.getDimension().width, super.getDimension().height);
    }

    @Override
    public void setDimension(int width, int height) {
        super.setDimension(width, height);
    }

    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y) || this.circle.contains(x, y);
    }

    @Override
    public void draw(Graphics2D g, Color color, String label) {
        super.draw(g, color, label);
        g.setColor(Color.white);
        g.drawOval((int)circle.getX(), (int)circle.getY(), super.getDimension().width, super.getDimension().height);
        g.fill(circle);
    }
}
