import java.awt.*;
import java.awt.geom.Ellipse2D; 

public class Circle extends ShapeDecorator {

    private Ellipse2D circle;

    public Circle(ShapeInterface shape) {
        super(shape);
        this.circle = new Ellipse2D.Float(super.getX(), super.getY(), super.getDimension().height,
                super.getDimension().width);
    }

    public void move(int x, int y) {
        super.move(x, y);
        this.circle.setFrame(super.getX(), super.getY(), super.getDimension().height, super.getDimension().width);
    }

    public void setDimension(int height, int width) {
        super.setDimension(height, width);
    }

    public boolean contains(int x, int y) {
        return super.contains(x, y) || this.circle.contains(x, y);
    }

    public void draw(Graphics2D g, Color color, String label) {
        super.draw(g, color, label);
        g.setColor(Color.white);
        g.drawOval((int)circle.getX(), (int)circle.getY(), super.getDimension().height, super.getDimension().width);
        g.fill(circle);
    }
}
