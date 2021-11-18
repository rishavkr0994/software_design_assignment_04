public class CitySquareDecorationHandler extends CityDecorationHandler {
    @Override
    public ShapeInterface decorateCityShape(ShapeInterface shape, boolean[] options) {
        ShapeInterface decoratedShape = shape;
        for (int i = 1; i < 5; i++) {
            if (options[i]) {
                Logger.getInstance().info("[Decorator] Decorating city with square " + i);
                decoratedShape = new SquareDecorator(decoratedShape, i);
            }
        }

        if (successor != null)
            decoratedShape = successor.decorateCityShape(decoratedShape, options);
        return decoratedShape;
    }
}
