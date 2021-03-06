// Class ArrayIntList can be used to store a list of integers.
// based on UW CSE143 demo code

public class ArrayIntList implements IntList {
   private int[] data; // list of integers
   private int size; // current number of elements in the list
   
   private static final int DEFAULT_CAPACITY = 100;
   
   // post: constructs an empty list of default capacity
   public ArrayIntList() {
      this(DEFAULT_CAPACITY);
   }
   
   // pre : capacity >= 0 (throws IllegalArgumentException if not)
   // post: constructs an empty list with the given capacity
   public ArrayIntList(int capacity) {
      if (capacity < 0) {
         throw new IllegalArgumentException("capacity: " + capacity);
      }
      data = new int[capacity];
      size = 0;
   }
   
   // post: returns the current number of elements in the list
   public int size() {
      return size;
   }
   
   // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
   // post: returns the integer at the given index in the list
   public int get(int index) {
      checkIndex(index);
      return data[index];
   }
   
   // post: creates a comma-separated, bracketed version of the list
   public String toString() {
      if (size == 0) {
         return "[]";
      } else {
         String result = "[" + data[0];
         for (int i = 1; i < size; i++) {
            result += ", " + data[i];
         }
         result += "]";
         return result;
      }
   }
   
   // post : returns the position of the first occurrence of the given
   // value (-1 if not found)
   public int indexOf(int value) {
      for (int i = 0; i < size; i++) {
         if (data[i] == value) {
            return i;
         }
      }
      return -1;
   }
   
   // post: returns true if the given value is contained in the list,
   // false otherwise
   public boolean contains(int value) {
      return indexOf(value) >= 0;
   }
   
   // pre : size() < capacity (throws IllegalStateException if not)
   // post: appends the given value to the end of the list
   public void add(int value) {
      checkCapacity(size + 1);
      data[size] = value;
      size++;
   }
   
   // pre : size() < capacity (throws IllegalStateException if not) &&
   // 0 <= index <= size() (throws IndexOutOfBoundsException if not)
   // post: inserts the given value at the given index, shifting subsequent
   // values right
   public void add(int index, int value) {
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException("index: " + index);
      }
      checkCapacity(size + 1);
      for (int i = size; i > index; i--) {
         data[i] = data[i - 1];
      }
      data[index] = value;
      size++;
   }
   
   // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
   // post: removes value at the given index, shifting subsequent values left
   public void remove(int index) {
      checkIndex(index);
      for (int i = index; i < size - 1; i++) {
         data[i] = data[i + 1];
      }
      size--;
   }
   
   // pre: size() + other.size() <= capacity (throws IllegalStateException
   // if not)
   // post: appends all values in the given list to the end of this list
   public void addAll(ArrayIntList other) {
      checkCapacity(size + other.size);
      for (int i = 0; i < other.size; i++) {
         add(other.data[i]);
      }
   }
   
   // post: throws an IndexOutOfBoundsException if the given index is
   // not a legal index of the current list
   private void checkIndex(int index) {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }
   
   // post: checks that the underlying array has the given capacity,
   // throwing an IllegalStateException if it does not
   private void checkCapacity(int capacity) {
      if (capacity > data.length) {
         throw new IllegalStateException("would exceed list capacity");
      }
   }
   
   public void insertAt(int index, int copies, int value) {
    if (index < 0 || index > size || copies < 0) { throw new IllegalArgumentException(); }
    if (copies == 0) { return; }
    size += copies;
    int count = 1;
    
    if (copies == 1) {
        for (int i = index; i < size; ++i) {
            int temp = data[i];
            data[i] = value;
            value = temp;
        }
    }
    else {
        for (int i = size - 1; i > index - 1; --i) {
            int temp = data[i];
            data[i + 1] = temp;
               
            if (i == index && count < copies) {
                ++count;
                data[i] = value;
                i = size - 1;
            }
        }
     }
   }
   
   public static void main(String[] args) {
      ArrayIntList list = new ArrayIntList();
      
      //list.add(10);
      //list.add(11);
      //list.add(14);
      //list.add(19);
      
      list.insertAt(0, 5, 1337);
      
      System.out.println(list.toString());
   }
}
