/**
 * This class is the data structure for storing information about a route i.e. source and destination city.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class Route {

    private City src;
    private City dest;

    /**
     * The constructor to create a new route.
     *
     * @param src source city
     * @param dest destination city
     */
    public Route(City src, City dest) {
        this.src = src;
        this.dest = dest;
    }

    /**
     * Gets the source city.
     * @return source city
     */
    public City getSrc() {
        return src;
    }

    /**
     * Sets the source city.
     * @param src source city
     */
    public void setSrc(City src) {
        this.src = src;
    }

    /**
     * Gets the destination city.
     * @return destination city
     */
    public City getDest() {
        return dest;
    }

    /**
     * Sets the destination city.
     * @param dest destination city
     */
    public void setDest(City dest) {
        this.dest = dest;
    }
}
