public class TreeIntSetClient {

   public static void main(String[] args) {
      TreeIntSet intSet = new TreeIntSet();
      
      int[] nums = { 5, 3, 7, 1, 9, 4, 6, 22, -10 };
      
      for (int num : nums) {
         intSet.add(num);
      }     
   }
}