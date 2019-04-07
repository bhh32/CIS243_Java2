import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArrayIntListTest {
   // Returns -(firstList.size + 1)
   @Test public void testCompareToFirstListShorterThanSecondListEqual() {
      ArrayIntList list = createArrayIntList(10, 12, 23, 54);
      ArrayIntList list2 = createArrayIntList(10, 12, 23, 54, 90, 100);
      
     assertEquals("The first array was longer or equal to the second array", -5, list.compareTo(list2));      
   }
   
   // Returns 0
   @Test public void testCompareToListsAreEqualAndSameLength() {
      ArrayIntList list = createArrayIntList(34, 22, 12, 5, 90);
      ArrayIntList list2 = createArrayIntList(34, 22, 12, 5, 90);
      
      assertEquals("The arrays are not the same", 0, list.compareTo(list2));
   }
   
   // Returns secondList.size + 1
   @Test public void testCompareToFirstListLongerThanSecondListEqual() {
      ArrayIntList list = createArrayIntList(1, 2, 3, 4, 5);
      ArrayIntList list2 = createArrayIntList(1, 2, 3);
      
      assertEquals("The first array was shorter or equal to the second array", 4, list.compareTo(list2));
   }
   
   // Returns -(firstList index + 1)
   @Test public void testCompareToFirstListShorterThanSecondListNotEqual() {
      ArrayIntList list = createArrayIntList(1, 2, 3, 4, 5);
      ArrayIntList list2 = createArrayIntList(1, 2, 3, 5, 4, 6);
      
      assertEquals("The first array was longer, equal, or the arrays were the same", -4, list.compareTo(list2));
   }
   
   // Returns secondlist index + 1
   @Test public void testCompareToFirstListLongerThanSecondListNotEqual() {
      ArrayIntList list = createArrayIntList(3, 2, 3, 4, 5);
      ArrayIntList list2 = createArrayIntList(2, 3, 4);
      
      assertEquals("The first array was shorter, equal, or the arrays were the same", 1, list.compareTo(list2));
   }
   
   // throws NullPointerException
   @Test(expected = NullPointerException.class)
   public void testCompareToOneListIsNull() {
      ArrayIntList list = createArrayIntList(1, 2, 3);
      ArrayIntList list2 = null;
      
      list.compareTo(list2);
   }
   
   // throws IllegalStateException
   @Test(expected = IllegalStateException.class)
   public void testReflectNotEnoughRoom() {
      ArrayIntList list = createArrayIntList(1, 2, 3, 4, 5, 6, 7);
      
      list.reflect();
   }
   
   // Tests the last element is the same as the first after reflect has been called
   @Test public void testReflect() {
      ArrayIntList list = createArrayIntList(23, 12, 4, 5);
      list.reflect();
      
      assertEquals("The ArrayIntList didn't reflect properly", 23, list.get(list.size() - 1));
   }
   
   // Helper Methods
   private static ArrayIntList createArrayIntList(int... values) {
      ArrayIntList temp = new ArrayIntList();
      
      for (int i = 0; i < values.length; ++i) {
         temp.add(values[i]);
      }
      
      return temp;
   }

}
