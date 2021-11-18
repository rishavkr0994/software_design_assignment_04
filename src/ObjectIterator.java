import java.util.List;

/**
 * A generic iterator for a list of objects. It implements the Iterator interface
 * @param <T>
 *
 * @author Krishna Sandeep Rupaakula, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class ObjectIterator<T> implements Iterator {

    private final List<T> objectList;
    private int currentIndex;

    /**
     * Creates a new instance of <code>ObjectIterator</code> with a list of objects
     * @param objectList list of objects
     */
    public ObjectIterator(List<T> objectList) {
        this.objectList = objectList;
        this.currentIndex = 0;
    }

    /**
     * Returns true if there are more items available in the list else false
     * @return whether more items are available in the list
     */
    @Override
    public boolean hasNext() {
        return objectList != null && currentIndex < objectList.size();
    }

    /**
     * Returns the next item in the list or null if there are no more available items
     * @return the next item in the list or null
     */
    @Override
    public T next() {
        return this.hasNext() ? objectList.get(currentIndex++) : null;
    }
}
