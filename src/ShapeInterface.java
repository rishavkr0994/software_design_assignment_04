import java.awt.*;

interface ShapeInterface {

    int getX();

    int getY();

    Dimension getDimension();

    void move(int x, int y);

    void setDimension(int height, int width);

    boolean contains(int x, int y);

    void draw(Graphics2D g, Color color, String label);
}
