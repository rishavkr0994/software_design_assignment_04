public class CityCircleDecorationHandler extends CityDecorationHandler {
    @Override
    public ShapeInterface decorateCityShape(ShapeInterface shape, boolean[] options) {
        ShapeInterface decoratedShape = shape;
        if (options[0]) {
            Logger.getInstance().info("[Decorator] Decorating city with circle");
            decoratedShape = new Circle(decoratedShape);
        }

        if (successor != null)
            decoratedShape = successor.decorateCityShape(decoratedShape, options);
        return decoratedShape;
    }
}
