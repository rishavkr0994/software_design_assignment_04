import java.awt.*;

/** Some Text Here
 *
 * @author Krishna Sandeep Rupaakula
 * @version 1.0
 * @since 2021-11-12
 */
public class CityCenter implements ShapeInterface {

    private final Rectangle rect;

    public CityCenter(int x, int y, int width, int height) {
        this.rect = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return this.rect.x;
    }

    public int getY() {
        return this.rect.y; 
    }

    public Dimension getDimension() {
        return this.rect.getSize();
    }

    public void move(int x, int y) {
        this.rect.x = x;
        this.rect.y = y;
    }

    public void setDimension(int width, int height) {
        this.rect.width = width;
        this.rect.height = height;
    }
    
    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    public void draw(Graphics2D g, Color color, String label) {
        g.setColor(color);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.fill(rect);
        g.setColor(Color.black);
        g.drawString(label, rect.x + rect.width + 5, rect.y - 5);
    }

    public ShapeInterface getBaseShape() { return this; }
}
