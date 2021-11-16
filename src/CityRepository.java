import java.util.ArrayList;

//https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm
public class CityRepository implements Container {
   private final ArrayList<City> cityList = new ArrayList<>();

   @Override
   public Iterator getIterator() {
      return new CityIterator();
   }

   private class CityIterator implements Iterator {
      int index;

      public boolean hasNext() {
         return index < cityList.size();
      }

      public Object next() {
         if (this.hasNext())
            return cityList.get(index++);
         return null;
      }
   }
}
