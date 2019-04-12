/*
   Bryan Hyland
   12Apr19
   Week 2 Lab
*/

import java.util.*;

public class LabWeek2 {
   public static boolean isPalindrome(Stack<Integer> intStack) {
      if (intStack == null || intStack.isEmpty()) {
         return true;
      }
   
      Stack<Integer> clone = new Stack<Integer>();
      Stack<Integer> reverse = new Stack<Integer>();
      Stack<Integer> putBack = new Stack<Integer>();
      Stack<Integer> intStack2 = new Stack<Integer>();
      
      // Put intStack into reverse, putBack, and intStack2
      reverse(intStack, reverse, putBack, intStack2);
      // Put putBack into clone in the original order
      reverse(putBack, clone);
      
      /* 
         Put the values back into intStack to make 
         it seem "unchanged"
      */
      reverse(intStack2, intStack);
      
      // Check the clone elements against the reverse elements
      while (!clone.isEmpty()) {
         if (clone.peek() == reverse.peek()) {
            clone.pop();
            reverse.pop();
         }
         else {
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
   
   public static void reverse(Stack<Integer> from, Stack<Integer> target1, Stack<Integer> target2, Stack<Integer> target3) {
      while (!from.isEmpty()) {
         Integer num = from.pop();
         target1.push(num);
         target2.push(num);
         target3.push(num);
      }
   }
   
   public static void reverse(Stack<Integer> from, Stack<Integer> target) {
      while (!from.isEmpty()) {
         target.push(from.pop());
      }
   }
}