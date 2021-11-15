import java.awt.*;

public class Square extends ShapeDecorator {

    private Rectangle rect;

    public Square(ShapeInterface shape, int type) {
        super(shape);
        if (type == 2) {
            this.rect = new Rectangle(super.getX() + super.getDimension().width, super.getY(), super.getDimension().width, super.getDimension().height);
        } else if (type == 3) {
            this.rect = new Rectangle(super.getX() - super.getDimension().width, super.getY(), super.getDimension().width, super.getDimension().height);
        } else if (type == 4) {
            this.rect = new Rectangle(super.getX(), super.getY() + super.getDimension().height, super.getDimension().width, super.getDimension().height);
        } else if (type == 5) {
            this.rect = new Rectangle(super.getX(), super.getY() - super.getDimension().height, super.getDimension().width, super.getDimension().height);
        } else if (type == 6) {
            this.rect = new Rectangle(super.getX() + super.getDimension().width, super.getY(), super.getDimension().width, super.getDimension().height);
        }
    }

    public void move(int x, int y) {
        super.move(x, y);
        this.rect.x = x;
        this.rect.y = y;
    }

    public void setDimension(int height, int width) {
        super.setDimension(height, width);
        this.rect.height = height;
        this.rect.width = width;
    }

    public boolean contains(int x, int y) {
        return super.contains(x, y) || this.rect.contains(x, y);
    }

    public void draw(Graphics2D g, Color color, String label) {
        super.draw(g, color, label);
        g.setColor(color);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.fill(rect);
    }
}
