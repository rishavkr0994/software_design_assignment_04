import java.awt.*;

/** This class creates the square decorators and positions them according to the user's choice.
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public class SquareDecorator extends ShapeDecorator {
    
    private Rectangle rect;
    private final int type;

    /**
     * Constructor for square decorator. Different type based on selection.
     * @param shape base implementation to decorate.
     * @param type which side should the square be on.
     */
    public SquareDecorator(ShapeInterface shape, int type) {
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

    /**
     * Moves the decoration.
     * @param x x coordinate.
     * @param y y coordinate.
     */
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

    /**
     * Change the decoration's size.
     * @param width new width.
     * @param height new height.
     */
    @Override
    public void setDimension(int width, int height) {
        super.setDimension(width, height);
        this.rect.width = width;
        this.rect.height = height;
    }

    /**
     * Used to check if mouse click lands in decoration.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return if mouse has clicked in shape or not.
     */
    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y) || this.rect.contains(x, y);
    }

    /**
     * Plots the shape on the GUI.
     * @param g the graphics panel.
     * @param color color of the shape.
     * @param label name of the city.
     */
    @Override
    public void draw(Graphics2D g, Color color, String label) {
        super.draw(g, color, label);
        g.setColor(color);
        g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        g.fill(rect);
    }
}
