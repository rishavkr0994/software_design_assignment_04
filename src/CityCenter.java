import java.awt.*;

public class CityCenter implements ShapeInterface {

    private Rectangle rect;

    public CityCenter(int x, int y, int height, int width) {
        this.rect = new Rectangle(x, y, height, width);
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

    public void setDimension(int height, int width) {
        this.rect.width = width;
        this.rect.height = height;
    }
    
    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    public void draw(Graphics2D g, Color color, String label) {
        g.setColor(color);
        g.drawRect(rect.x, rect.y, rect.height, rect.width);
        g.fill(rect);
        g.setColor(Color.black);
        g.drawString(label, rect.x -10, rect.y-8);
    }
}
