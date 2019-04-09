import java.util.*;

public class LabWeek2 {
   public static boolean isPalindrome(Stack<Integer> intStack) {
      if (intStack == null || intStack.isEmpty()) {
         return true;
      }
   
      for (int i = 0, j = intStack.size() - 1; i < intStack.size(); ++i, --j) {
         if ((int)intStack.get(i) != (int)intStack.get(j)) {
            return false;
         }
      }
      
      return true;
   }
   
   public static int countCommon(List<Integer> list, List<Integer> list2) {
      
      TreeSet<Integer> beenChecked = new TreeSet<>();
      for(Integer element : list) {
         if(list2.contains(element) && !beenChecked.contains(element)) {
            beenChecked.add(element);
         }
      }
      return beenChecked.size();
   }
   
   public static void main(String[] args) {
   
      /*Stack<Integer> intStack = new Stack<>();
      Stack<Integer> intStack2 = null;
      
      // Check empty Stack
      System.out.println(isPalindrome(intStack)); // true
      
      // Check null Stack
      System.out.println(isPalindrome(intStack2)); // true
      System.out.println(isPalindrome(null)); // true
      
      // Make palindromic
      intStack.push(1);
      intStack.push(2);
      intStack.push(1);
      
      intStack2 = new Stack<>(); // Set to new Stack<Integer>();
      
      // Make non-palindromic
      intStack2.push(1);
      intStack2.push(2);
      intStack2.push(4);
      intStack2.push(1);
      
      System.out.println(isPalindrome(intStack)); // true
      System.out.println(isPalindrome(intStack2)); // false
      */
      
      ArrayList<Integer> list = new ArrayList<>();
      ArrayList<Integer> list2 = new ArrayList<>();
      
      add(list, 3, 7, 3, -1, 2, 3, 7, 2, 15, 15);
      add(list2, -5, 15, 2, -1, 7, 15, 36);
      
      System.out.println(countCommon(list, list2));
      System.out.println(countCommonTheHardWay(list, list2));
   }
   
   public static void add(List<Integer> list, Integer ... nums) {
      for(Integer i : nums) {
         list.add(i);
      }
   }
}