/**
 * This class is the abstract handler in the chain of responsibility pattern. The chain of responsibility pattern is
 * used to apply the decorations to the city shape
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public abstract class CityDecorationHandler {

    protected CityDecorationHandler successor;

    /**
     * Sets the <code>successor</code> instance, which is the next-in-line handler.
     *
     * @param successor next-in-line handler
     */
    public void setSuccessor(CityDecorationHandler successor) {
        this.successor = successor;
    }

    /**
     * Decorates the city shape based on the decoration flags specified via <code>options</code>
     *
     * @param shape city shape
     * @param options array of decoration flags
     * @return decorated city shape
     */
    public abstract ShapeInterface decorateCityShape(ShapeInterface shape, boolean[] options);
}
