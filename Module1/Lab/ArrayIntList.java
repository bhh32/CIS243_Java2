/*
   Class: CIS143 
   Assignment/Week: Lab Week #1
   Name: Bryan Hyland
   Date: 6Apr19
*/

public class ArrayIntList {
   private static final int NOT_FOUND = -1;
   
   private int[] data;
   private int size;   
   
   public ArrayIntList() {
      this(10);
   }
   
   public ArrayIntList(int capacity) {
      data = new int[capacity];
   }
   
   // add
   // throws IndexOutOfBounds if index < 0 || >= size + 1
   public void add(int value) {
      // add an element to the end of the ArrayIntList
      checkIndex(size + 1, data.length);
      data[size] = value;
      size++;
   }
   
   public void add(int index, int value) {
      checkIndex(size, data.length);
      checkIndex(index, size + 1);
      
      if (index == size - 1) {
         add(value);
      }
      else {
         for (int i = size; i > index; --i) {
            data[i] = data[i - 1];
         }
      
         data[index] = value;
         size++;
      }
      
   }
   
   // contains
   public boolean contains(int value) {
      // returns true if the value is in the ArrayIntList, false otherwise
      return indexOf(value) != -1;
   }
   
   // remove
   // throws IndexOutOfBounds if index < 0 || >= size
   public void remove(int index) {
      checkIndex(index, size);
      
      for (int i = index; i < size - 1; ++i) {
         data[i] = data[i + 1];
      }  
      size--;
   }
   
   // indexOf
   public int indexOf(int value) {
      
      for (int i = 0; i < size; ++i) {
         if (data[i] == value) {
            return i;
         }
      }
      
      return NOT_FOUND;
   }
   
   // set
   // throws IndexOutOfBounds if index < 0 || >= size + 1 || >= data.length
   public void set(int index, int value) {
      checkIndex(index, Math.min(size + 1, data.length));
      
      //if index == size add the value at the end
      data[index] = value;
      if (index == size) {
         size++;
      }
   }
   
   // get
   // throws IndexOutOfBounds if index < 0 || >= size
   public int get(int index) {
      checkIndex(index, size);
      return data[index];
   }
   
   // size
   public int size() {
      return size;
   }
   
   // toString
   @Override
   public String toString() {
      String returnString = "[";
      if (size > 0)
      {
         returnString += data[0];
         for (int i = 1; i < size; ++i) {
            returnString += ", " + data[i];
         }
      }
      
      return returnString + "]"; 
   }
   
   /* Compares two ArrayIntLists to see if they have the same values.
      Returns 0 if equal, -index + 1 if other lists size is bigger and aren't
      equal, index + 1 if this lists' size is bigger values aren't equal,
      -size + 1 if this lists' size is smaller and values are equal, 
      or size + 1 if the other lists' size is smaller and the values are equal.
   */
   public int compareTo(ArrayIntList list) {
      if (list == null) {
         throw new NullPointerException("List is null");
      }
      
      // Get the smaller list size.
      int minLength = list.size <= this.size ? list.size : this.size;
      int returnValue = 0;
      int index = 0;
      boolean isEqual = true;
      
      while (isEqual && index < minLength) {
         // if this element isn't the same as list element
         // set isEqual to false and set returnValue to the
         // appropriate index + 1 value.
         if (list.data[index] != this.data[index]) {            
            isEqual = false; 
            if (list.data[index] < this.data[index]) {
               returnValue = index + 1;
            }
            else if (list.data[index] > this.data[index]) {
               returnValue = -(index + 1);
            }
         }
         else {
            index++;
         }            
      }  
      
      // If everything is equal return 0
      if (list.size == this.size && isEqual) {
         returnValue = 0;
      }
      // If this.size is less than list.size,
      // and the values up to the smaller size were
      // the same, returnValue =  -(this.size + 1)   
      else if (this.size < list.size && isEqual) {
         returnValue = -(this.size + 1);
      }
      // If this.size is bigger than list.size,
      // and the values up to the smaller size were
      // the same, returnValue = list.size + 1.
      else if (this.size > list.size && isEqual) {
         returnValue = list.size + 1;
      }
         
      return returnValue;
   }
   
   // Adds a mirror image of the values to the end of the ArrayIntList.
   public void reflect()
   {
      // Check to see if the ArrayIntList is big enough to hold
      // the required additions.
      if (data.length < (size * 2)) {
         throw new IllegalStateException("The array is not "
            + "big enough for reflect()");
      }
      
      // Add the mirror image of the values to the end of the ArrayIntList
      // and increase the size as an element is added.
      for (int i = size - 1; i > -1; --i) {
         data[size] = data[i];
         size++;
      }
   }
   
   // Helper Methods
   // throws IndexOutOfBounds if index not within limits
   private void checkIndex(int index, int limit) {
      if (index < 0 || index >= limit) {
         throw new IndexOutOfBoundsException("Index greater "  
            + "than available space: " + index);
      }
   }
}  
