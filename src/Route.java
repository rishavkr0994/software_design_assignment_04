/**
 * This class is the data structure for route information between two cities i.e. the source, destination cities and
 * the distance between them.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class Route {
    private City src;
    private City dest;

    public Route(City src, City dest) {
        this.src = src;
        this.dest = dest;
    }

    /**
     * Get the source city.
     * @return source city
     */
    public City getSrc() {
        return src;
    }

    /**
     * Set the source city.
     * @param src source city
     */
    public void setSrc(City src) {
        this.src = src;
    }

    /**
     * Get the destination city.
     * @return destination city
     */
    public City getDest() {
        return dest;
    }

    /**
     * Set the destination city.
     * @param dest destination city
     */
    public void setDest(City dest) {
        this.dest = dest;
    }
}
