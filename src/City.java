import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;

/**
 * This class is the data structure for storing information about a city i.e. name, color and shape. It defines the
 * operations to draw the city, draw the path between two cities. It also uses the chain of responsibility to apply the
 * decorations to the city shape.
 *
 * @author Krishna Sandeep Rupaakula, Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class City {

    public static final int DEFAULT_SIZE = 20;
    public static final Color DEFAULT_COLOR = Color.RED;
    public static final int DECORATION_OPTION_COUNT = 5;

    private String label;
    private Color color;
    private boolean[] options = new boolean[DECORATION_OPTION_COUNT];
    private ShapeInterface shape; 

    /**
     * The constructor to create a new city.
     *
     * @param label city name
     * @param x x-coordinate of the upper left corner of the city rectangle
     * @param y y-coordinate of the upper left corner of the city rectangle
     */
    public City(String label, int x, int y) {
        this.label = label;
        this.color = DEFAULT_COLOR;
        this.shape = new CityCenter(x, y, DEFAULT_SIZE, DEFAULT_SIZE);
        Arrays.fill(options, false);
    }

    /**
     * Gets the x-coordinate of the upper left corner of the city's central rectangle
     * @return x-coordinate of the upper left corner of the city's central rectangle
     */
    public int getX() { return this.shape.getX(); }

    /**
     * Gets the y-coordinate of the upper left corner the city's central rectangle
     * @return y-coordinate of the upper left corner the city's central rectangle
     */
    public int getY() { return this.shape.getY(); }

    /**
     * Gets the city name.
     * @return city name.
     */
    public String getLabel() { return label; }

    /**
     * Sets the city name.
     * @param label city name.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets the city color.
     * @return city color.
     */
    public Color getColor() { return color; }

    /**
     * Sets the city color.
     * @param color city color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the city dimension.
     * @return city dimension.
     */
    public Dimension getDimension() {
        return shape.getDimension();
    }

    /**
     * Sets the city dimension.
     * @param dimension city dimension.
     */
    public void setDimension(Dimension dimension) {
        shape.setDimension(dimension.width, dimension.height);
    }

    /**
     * Gets the city shape decoration options.
     * @return city shape decoration options.
     */
    public boolean[] getOptions() {
        return options;
    }

    /**
     * Sets the city shape decoration options.
     * <p>
     * Uses chain of responsibility pattern to apply the shape decorations based on <code>options</code>
     *
     * @param options city shape decoration options.
     */
    public void setOptions(boolean[] options) {
        if (options.length == DECORATION_OPTION_COUNT) {
            this.options = Arrays.copyOf(options, options.length);

            CityDecorationHandler circleDecorationHandler = new CityCircleDecorationHandler();
            CityDecorationHandler squareDecorationHandler = new CitySquareDecorationHandler();
            circleDecorationHandler.setSuccessor(squareDecorationHandler);

            Logger.getInstance().info("[Chain of Responsibility] Decorating city using chain of responsibility");
            this.shape = circleDecorationHandler.decorateCityShape(shape.getBaseShape(), options);
        }
    }

    /**
     * Draws the city using a <code>Graphics</code> object.
     * @param g graphics object
     */
    public void draw(Graphics2D g) {
        shape.draw(g, this.color, label);
    }

    /**
     * Moves the city to new co-ordinates.
     * @param x new x-coordinate of the upper left corner of the city's central rectangle
     * @param y new y-coordinate of the upper left corner of the city central rectangle
     */
    public void move(int x, int y) {
        this.shape.move(x, y);
    }

    /**
     * Draws the route between this city and the destination city using a <code>Graphics</code> object.
     * @param dest destination city
     * @param g graphics object
     */
    public void drawConnect(City dest, Graphics2D g) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        g.draw(new Line2D.Float(center().x, center().y, dest.center().x, dest.center().y));
    }

    /**
     * Checks if the co-ordinate value belong to this city and returns a boolean indicator for the same.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return whether this city contains the co-ordinates
     */
    public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
    }

    private Point center() {
        return new Point(this.shape.getX() + this.shape.getDimension().width / 2, this.shape.getY() +
                this.shape.getDimension().height / 2);
    }
}
