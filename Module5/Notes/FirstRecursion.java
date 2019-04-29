public class FirstRecursion {   
   public static void main(String[] main) {
      //recurse(1);
      
      stop(775);
      System.out.println("... and again!");
      stop(777);
      System.out.println("... one more time!");
      stop(779);
   }
   
   public static void recurse(int x) {
      System.out.println(x);
      recurse(x + 1);
   }
   
   public static void stop(int x) {
      if (x == 777) {
         System.out.println("You've reached 777!");
      }
      else {
         if (x > 777) {
            stop(x - 1);
         }
         else {
            stop(x + 1);
         }
      }
      
      System.out.println(x);
   }
}