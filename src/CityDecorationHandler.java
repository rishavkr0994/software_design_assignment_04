public abstract class CityDecorationHandler {
    protected CityDecorationHandler successor;

    public void setSuccessor(CityDecorationHandler successor) {
        this.successor = successor;
    }

    public abstract ShapeInterface decorateCityShape(ShapeInterface shape, boolean[] options);
}
