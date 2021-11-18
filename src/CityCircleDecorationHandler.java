/**
 * This class is a concrete handler in the chain of responsibility pattern. It applies the circle decoration to the city
 * shape, based on some conditions.
 *
 * @author Rishav Kumar
 * @version 1.0
 * @since 2021-11-12
 */
public class CityCircleDecorationHandler extends CityDecorationHandler {

    /**
     * Decorates the city shape using <code>CircleDecorator</code> based on the decoration flags specified via
     * <code>options</code>
     *
     * @param shape city shape
     * @param options array of decoration flags
     * @return decorated city shape
     */
    @Override
    public ShapeInterface decorateCityShape(ShapeInterface shape, boolean[] options) {
        ShapeInterface decoratedShape = shape;
        if (options[0]) {
            Logger.getInstance().info("[Decorator] Decorating city with circle");
            decoratedShape = new CircleDecorator(decoratedShape);
        }

        if (successor != null)
            decoratedShape = successor.decorateCityShape(decoratedShape, options);
        return decoratedShape;
    }
}
