public class CitySquareDecorationHandler extends CityDecorationHandler {
    @Override
    public ShapeInterface decorateCityShape(ShapeInterface shape, boolean[] options) {
        ShapeInterface decoratedShape = shape;
        for (int i = 1; i < 5; i++) {
            if (options[i])
                decoratedShape = new Square(decoratedShape, i);
        }

        if (successor != null)
            decoratedShape = successor.decorateCityShape(decoratedShape, options);
        return decoratedShape;
    }
}