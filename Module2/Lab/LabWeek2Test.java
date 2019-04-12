/*
   Bryan Hyland
   12Apr19
   Week 2 Lab
*/

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class LabWeek2Test {

   @Test public void testIsPalindromeNull() {
      Stack<Integer> intStack = null;
      
      assertTrue("isPalindrome was false when it should be true", LabWeek2.isPalindrome(intStack));      
   }
   
   @Test public void testIsPalindromeEmpty() {
      Stack<Integer> intStack = new Stack<>();
      
      assertTrue("isPalindrome was false when it should be true", LabWeek2.isPalindrome(intStack));      
   }
   
   @Test public void testIsPalindromeFalse() {
      Stack<Integer> intStack = new Stack<>();
      addValues(intStack, 1, 2, 3);
      
      assertFalse("isPalindrome was true when it should be false", LabWeek2.isPalindrome(intStack));
   }
   
   @Test public void testIsPalindromeTrue() {
      Stack<Integer> intStack = new Stack<>();
      addValues(intStack, 1, 2, 3, 4, 3, 2, 1);
      
      assertTrue("isPalindrome was false when it should be true", LabWeek2.isPalindrome(intStack));
   }
   
   @Test public void testCountCommon4Common() {
      ArrayList<Integer> list = new ArrayList<>();
      ArrayList<Integer> list2 = new ArrayList<>();
      
      addValues(list, 3, 7, 3, -1, 2, 3, 7, 2, 15, 15);
      addValues(list2, -5, 15, 2, -1, 7, 15, 36);
      
      int count = LabWeek2.countCommon(list, list2);
      
      assertEquals("There were " + count + " when there should be 4", 4, count);
   }
   
   @Test public void testCountCommonNoneCommon() {
      ArrayList<Integer> list = new ArrayList<>();
      ArrayList<Integer> list2 = new ArrayList<>();
      
      addValues(list, 1, 2, 3, 4);
      addValues(list2, -5, 15, -1, 7, 15, 36);
      
      int count = LabWeek2.countCommon(list, list2);
      
      assertEquals("There were " + count + " when there should be 0", 0, count);
   }
   
   // Utility Programs
   private static void addValues(Stack<Integer> stack, Integer ... nums) {
      for (int i = 0; i < nums.length; ++i) {
         stack.push(nums[i]);
      }
   }
   
   private static void addValues(List<Integer> c, Integer ... nums) {
      for (int i = 0; i < nums.length; ++i) {
         c.add(nums[i]);
      }
   }
}
