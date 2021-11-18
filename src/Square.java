import java.awt.*;

/** Some Text Here
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public class Square extends ShapeDecorator {
    
    private Rectangle rect;
    private final int type;

    public Square(ShapeInterface shape, int type) {
        super(shape);
        this.type = type;
        if (type == 1) {
            this.rect = new Rectangle(super.getX(), super.getY() - super.getDimension().height,
                    super.getDimension().width, super.getDimension().height);
        } else if (type == 2) {
            this.rect = new Rectangle(super.getX() + super.getDimension().width, super.getY(),
                    super.getDimension().width, super.getDimension().height);
        } else if (type == 3) {
            this.rect = new Rectangle(super.getX() - super.getDimension().width, super.getY(),
                    super.getDimension().width, super.getDimension().height);
        } else if (type == 4) {
            this.rect = new Rectangle(super.getX(), super.getY() + super.getDimension().height,
                    super.getDimension().width, super.getDimension().height);
        }
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        if (this.type == 1) {
            this.rect = new Rectangle(super.getX(), super.getY() - super.getDimension().height,
                    super.getDimension().width, super.getDimension().height);
        } else if (this.type == 2) {
            this.rect = new Rectangle(super.getX() + super.getDimension().width, super.getY(),
                    super.getDimension().width, super.getDimension().height);
        } else if (this.type == 3) {
            this.rect = new Rectangle(super.getX() - super.getDimension().width, super.getY(),
                    super.getDimension().width, super.getDimension().height);
        } else if (this.type == 4) {
            this.rect = new Rectangle(super.getX(), super.getY() + super.getDimension().height,
                    super.getDimension().width, super.getDimension().height);
        }
    }

    @Override
    public void setDimension(int width, int height) {
        super.setDimension(width, height);
        this.rect.width = width;
        this.rect.height = height;
    }

    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y) || this.rect.contains(x, y);
    }

    @Override
    public void draw(Graphics2D g, Color color, String label) {
        super.draw(g, color, label);
        g.setColor(color);
        g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        g.fill(rect);
    }
}
