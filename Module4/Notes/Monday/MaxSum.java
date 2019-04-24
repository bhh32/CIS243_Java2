import java.util.*;

public class MaxSum {

   private static final int START_INDEX = 0;
   private static final int STOP_INDEX = 1;
   private static final int SUM_INDEX = 2;
   
   
   public static void main(String[] args) {
   
   }
   
   public int[] findMaxSumBruteForce(int[] list) {
      int max = list[0];
      int bestStart = 0;
      int bestStop = 0;
      int bestSum = 0;
      
      // overall time is length^3/4 -> O(length^3)
      for (int start = 0; start < list.length; start++) { // Runs length times
         for (int stop = start; stop < list.length; ++stop) { // Runs length / 2 times
            int sum = 0;
            for (int i = start; i <= stop; ++i) { // average length of a subarry: lenght / 2 times
               sum += list[i];
            }
            if (sum > bestSum) {
               bestSum = sum;
               bestStart = start;
               bestStop = stop;
            }
         }
      }
      
      return new int[] { bestStart, bestStop, bestSum };
   }
   
   public int[] findMaxSumRememberPrevious(int[] list) {
      int max = list[0];
      int bestStart = 0;
      int bestStop = 0;
      int bestSum = 0;
      
      // overall time is length*2 / 2 -> O(length^2)
      for (int start = 0; start < list.length; start++) { // Runs length times
         int sum = 0;
         for (int stop = start; stop < list.length; ++stop) { // Runs length / 2 times
            sum += list[stop];
            bestSum = sum;
            bestStart = start;
            bestStop = stop;
         }
      }
      
      return new int[] { bestStart, bestStop, bestSum };
   }
   
   public int[] findMaxSumRememberBestRun(int[] list) {
      int max = list[0];
      int bestStart = 0;
      int bestStop = 0;
      int bestSum = 0;
   
      int start = 0;
      int sum = 0;
      
      for (int i = 0; i < list.length; ++i) { // O(n)         
         // if best sum up to previous item was negative, ignore all prior items
         if (sum < 0) {
            start = i;
            sum = 0;
         }
         
         sum += list[i];
         if (sum > bestSum) {
            bestSum = sum;
            bestStart = start;
            bestStop = i;
         }
      }
      
      return new int[] { bestStart, bestStop, bestSum };      
   }
   
   /*******Don't Use This!*******/
   /*public static void example(int n) {
      // O(n)
      for (int i = 0; i < n; i++) {
         System.out.println("hi");
      }
      
      // n * n/10 --> O(n*2)
      for (int i = 0; i < n; ++i) {
         for (int i = 0; i < n/10; ++i) {
            System.out.println("hi");
         }
      }
      
      // Above unrolled
      for (int i = 0; i < n; ++i) {
         System.out.println("hi\nhi\nhi\nhi\nhi\n");
      }
   }*/
}