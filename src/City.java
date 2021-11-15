import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;

/**
 * This class is the data structure for storing information about a city i.e. the city name and its bounds. It also
 * defines functions to update the city bounds as well as fdr graphics operations to draw the city / draw the path
 * between two cities using a Graphics object.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class City {
    public static final int DECORATION_OPTION_COUNT = 5;

    private String label;
    private Color color;
    private boolean[] options = new boolean[DECORATION_OPTION_COUNT];
    private ShapeInterface shape; 

    /**
     * The constructor to create a new city.
     *
     * @param label city name
     * @param x x-coordinate of the upper left corner of the city rectangle to be drawn
     * @param y y-coordinate of the upper left corner of the city rectangle to be drawn
     * @param w width of the city rectangle to be drawn
     * @param h height of the city rectangle to be drawn
     */
    public City(String label, int x, int y, int w, int h, Color color) {
        this.label = label;
        this.color = color;
        this.shape = new CityCenter(x, y, h, w);
        Arrays.fill(options, false);
    }

    /**
     * Get the x-coordinate of the upper left corner of the city rectangle
     * @return x-coordinate of the upper left corner of the city rectangle
     */
    public int getX() { return this.shape.getX(); }

    /**
     * Get the y-coordinate of the upper left corner the city rectangle
     * @return y-coordinate of the upper left corner the city rectangle
     */
    public int getY() { return this.shape.getY(); }

    /**
     * Get the city name.
     * @return city name.
     */
    public String getLabel() { return label; }

    /**
     * Set the city name.
     * @param label city name.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Get the city color.
     * @return city color.
     */
    public Color getColor() { return color; }

    /**
     * Set the city color.
     * @param color city color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the city dimension.
     * @return city dimension.
     */
    public Dimension getDimension() {
        return shape.getDimension();
    }

    /**
     * Set the city dimension.
     * @param dimension city dimension.
     */
    public void setDimension(Dimension dimension) {
        shape.setDimension(dimension.height, dimension.width);
    }

    /**
     * Get the city decoration options.
     * @return city decoration options.
     */
    public boolean[] getOptions() {
        return options;
    }

    /**
     * Set the city decoration options.
     * @param options city decoration options.
     */
    public void setOptions(boolean[] options) {
        if (options.length == DECORATION_OPTION_COUNT)
            this.options = Arrays.copyOf(options, options.length);
    }

    /**
     * Set the city shape.
     * @param choice city shape.
     */
    public void setShape(int choice) {
        //reference - 0: remove, 1: circle, 2: right square, 3: left square, 4: top square, 5: bottom square
        switch (choice) {
            case 0: {
//                this.shape = this.shape.super;
                break;
            }
            case 1: {
                this.shape = new Circle((ShapeInterface) this.shape);
                break;
            }
            case 2: {
                this.shape = new Square((ShapeInterface) this.shape, 2);
                break;
            }
            case 3: {
                this.shape = new Square((ShapeInterface) this.shape, 3);
                break;
            }
            case 4: {
                this.shape = new Square((ShapeInterface) this.shape, 4);
                break;
            }
            case 5: {
                this.shape = new Square((ShapeInterface) this.shape, 5);
                break;
            }
        }

    }

    /**
     * Plots the city (as a rectangle) using a Graphics object.
     * @param g graphics object to plot the content
     */
    public void draw(Graphics2D g) {
        shape.draw(g, this.color, label);
    }

    /**
     * Update the bounds of this city.
     * @param x, new x-coordinate of the upper left corner of the city rectangle
     * @param y, new y-coordinate of the upper left corner the city rectangle
     */
    public void move(int x, int y) {
        this.shape.move(x, y);
    }

    private Point center() {
        return new Point(this.shape.getX() + this.shape.getDimension().width / 2, this.shape.getY() + this.shape.getDimension().height / 2);
    }

    /**
     * Plots the route (as a line) between this city and the destination city <code>b</code> using a Graphics object.
     * @param b destination city
     * @param g graphics object to plot the content
     */
    public void drawConnect(City b, Graphics2D g) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        g.draw(new Line2D.Float(center().x, center().y, b.center().x, b.center().y));
    }

    /**
     * Checks if a particular co-ordinate belongs to this city and returns a boolean indicator for the same.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return whether this city contains the input co-ordinates
     */
    public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
    }
}
