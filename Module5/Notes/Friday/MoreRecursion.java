public class MoreRecursion {

   public static void main(String[] args) {
      int[] numbers = { 4, 3, -9, 44, 204, 157 };
      
      System.out.println(sumIterative(numbers));
      System.out.println(sumRecursive(numbers));
      
      System.out.println(sumRecursive(new int[0]));
      System.out.println(sumRecursive(null));
   
   }

   // add up the entries in an array
   public static int sumIterative(int[] list) {
      int sum = 0;
      for (int i = list.length - 1; i >= 0; i--) {
         sum += list[i];
      }
      return sum;
   }
   
   // return the sum of the values in the array
   public static int sumRecursive(int[] list) {
      // gatekeeper checks the value before letting us recurse
      if (list == null) {
         return 0;
      }
   
      // gatekeeper kicks off the recursion
      return _sumRecursive(list, 0);
   }

   // pre: index > 0
   // return the sum of the values in the array from index to length - 1 
   private static int _sumRecursive(int[] list, int index) {
      /*
      if (index == list.length - 1) {
         return list[index];
      }
      */

      if (index == list.length) {
         return 0;
      }
      
      return list[index] + _sumRecursive(list, index+1);
   }

}