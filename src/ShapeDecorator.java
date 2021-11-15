import java.awt.*;

public abstract class ShapeDecorator implements ShapeInterface {

    public ShapeInterface shape;

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

    public void setDimension(int height, int width) {
        this.shape.setDimension(height, width);
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
