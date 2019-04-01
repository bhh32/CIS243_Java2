import java.util.*;
public class ArrayListDemo
{
   public static void main(String[] args)
   {
      ArrayList<String> list = new ArrayList(157);
      String[] array = new String[157];
         
      System.out.println("array.length: " + array.length);
      System.out.println("list length: " + list.size());
      
      array[0] = "Hello";
      list.add("World");
      
      list.add(0, "Hi");
      System.out.println(Arrays.toString(array));
      System.out.println(list);
      
      list.remove(0);
      System.out.println(Arrays.toString(array));
      
      int[] intArray = new int[20];
      ArrayList<Integer> intList = new ArrayList<>();
      
      for(int i = 0; i < intArray.length; ++i)
      {
         intArray[i] = i * 10;
         intList.add(i * 10);
      }
      
      System.out.println(Arrays.toString(intArray));
      System.out.println(intList);
      
      System.out.println("Contains 140: " + intList.contains(140));
      System.out.println("Contains 142: " + intList.contains(142));
      
      System.out.println("Index of 140: " + intList.indexOf(140));
      System.out.println("Index of 142: " + intList.indexOf(142));
      
      intArray[12] = -99;
      intList.set(12, -88);
      
      System.out.println(Arrays.toString(intArray));
      System.out.println(intList);
      
      ArrayList<String> stringList = new ArrayList<>();
      stringList.add("duplicate");
      stringList.add("duplicate");
      stringList.add("duplicate");
      stringList.add("duplicate");
      
      removeDuplicates(stringList);
      System.out.println(stringList);
   }
   
   static void removeDuplicates(ArrayList<String> list)
   {
      for(int i = 1; i < list.size(); ++i)
      {
         if(list.get(i - 1).equals(list.get(i)))
            list.remove(i);
            --i;
      }
   }
}