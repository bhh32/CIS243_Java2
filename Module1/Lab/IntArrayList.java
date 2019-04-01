import java.util.*;

public class IntArrayList
{
   private int size;
   private int capacity;
   private int currentIdx;
   private int[] array;

   // No Parameter Constructor
   public IntArrayList()
   {
      this(10);
   }
   
   // Constructor takes in the initial capacity
   public IntArrayList(int initialCapacity)
   {
      setCapacity(initialCapacity);
      array = new int[initialCapacity];
      currentIdx = 0;
      size = 0;
   }
   
   public void add(Integer element)
   {
      if(currentIdx <= capacity)
      {
         array[currentIdx] = element;
         size++;
      }
      else
      {
         int[] temp = new int[capacity + 10];
         for(int i = 0; i < array.length; ++i)
         {
            temp[i] = array[i];
            currentIdx = i;
         }
         
         array = temp;
         array[currentIdx + 1] = element;
         size++;
      }
   }
   
   public void add(int index, Integer element)
   {
      if(index <= currentIdx + 1 && index < capacity)
      {
         if(index != 0)
         {
            int[] temp = new int[capacity];
         
            for(int i = 0; i < index; ++i)
               temp[i] = array[i];
            
            temp[index] = element;
         
            for(int j = index + 1; j < size + 1; ++j)
            {
               temp[j] = array[j - 1];
               currentIdx = j;
            }
         }
         else
         {
            int [] temp = new int[capacity];
            temp[0] = element;
            
            for(int i = 1; i < array.length; ++ i)
               temp[i] = array[0];
         }
             
         size++;
      }
      else
      {
         int[] temp = new int[capacity + 10];
         
         for(int i = 0; i < index; ++i)
            temp[i] = array[i];
         
         temp[index] = element;
         
         for(int j = index + 1; j < size + 1; ++j)
         {
            temp[j] = array[j - 1];
            currentIdx = j;
         }
             
         size++;
      }
   }
   
   public void clear()
   {
      array = new int[10];
      setCapacity(10);
      size = 0;
   }
   
   public boolean contains(Integer value)
   {
      return false;
   }
   
   public boolean equals(List<Integer> c)
   {
      return false;
   }
   
   public Integer get(int index)
   {
      return array[index];
   }
   
   public int indexOf(Integer value)
   {
      return -1;
   }
   
   public boolean isEmpty()
   {
      return this.size == 0 ? true : false;
   }
   
   public int lastIndexOf(Integer value)
   {
      return -1;
   }
   
   public Integer remove(int index)
   {
      return 1;
   }
   
   public boolean remove(Integer value)
   {
      return false;
   }
   
   public boolean removeAll(List<Integer> c)
   {
      return true;
   }
   
   public Integer set(int index, Integer element)
   {
      return 1;
   }
   
   public int size()
   {
      return this.size;
   }
   
   public int[] toArray()
   {
      return array;
   }
   
   public Integer[] toArray(Integer[] a)
   {
      return a;
   }
   
   private void setCapacity(int newValue)
   {
      this.capacity = newValue;
   }
   
   public static void main(String[] args)
   {
      IntArrayList intList = new IntArrayList();
      System.out.println(intList.isEmpty());
      intList.add(10);
      intList.add(0, 20);
      System.out.println(intList.isEmpty());
      System.out.println(intList.get(0));
      intList.clear();
      System.out.println(intList.isEmpty());
      System.out.println(intList.get(0));    
   }
}