import java.util.List;

public class ObjectIterator<T> implements Iterator {

    private final List<T> objectList;
    private int currentIndex;

    public ObjectIterator(List<T> objectList) {
        this.objectList = objectList;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return objectList != null && currentIndex < objectList.size();
    }

    @Override
    public T next() {
        return this.hasNext() ? objectList.get(currentIndex++) : null;
    }
}
