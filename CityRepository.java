//https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm
public class CityRepository implements Container {
   ArrayList<City> cityList = new ArrayList<>;

   @Override
   public Iterator getIterator() {
      return new CityIterator();
   }

   private class CityIterator implements Iterator {

      int index;

      public boolean hasNext() {

         if(index < cityList.size()){
            return true;
         }
         return false;
      }

      public Object next() {

         if(this.hasNext()){
            return cityList[index++];
         }
         return null;
      }
   }
}
