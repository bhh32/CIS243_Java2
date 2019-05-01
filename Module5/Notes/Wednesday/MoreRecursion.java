public class MoreRecursion {
   public static void main(String[] args) {
      int[] numbers = { 4, 3, -9, 44, 204, 157 };
      
      System.out.println(sumIterative(numbers));
      System.out.println(sumRecursive(numbers));
   }
   
   // add up the entries in an array
   public static int sumIterative(int[] list) {
      int sum = 0;
      for (int i = list.length - 1; i > - 1; --i) {
         sum += list[i];
      }
      
      return sum;
   }
   
   // This is a "gatekeeper" method for the one below
   public static int sumRecursive(int[] list) {   
      if (list == null) {
         return 0;
      }
      
      return sumRecursive(list, 0);
   }
   // pre: index > 0
   // return the sum of the values in the array from index to length - 1   
   private static int sumRecursive(int[] list, int index) {
      if (index == list.length) {
         return 0;
      }
   
      return list[index] + sumRecursive(list, index + 1);
   }
}