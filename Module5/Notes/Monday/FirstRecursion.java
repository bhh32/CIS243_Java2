public class FirstRecursion {   
   public static void main(String[] main) {
      //recurse(1);
      
      /*stop(775);
      System.out.println("... and again!");
      stop(777);
      System.out.println("... one more time!");
      stop(779);*/
      
      //System.out.println(pow(2, 32));
      
      //System.out.println(digitDoubler(-123));
      
      //writeBinary(153);
      //System.out.println();
      //writeBinary(42);
      
      System.out.println(writeStars(3));
   }
   
   // write out 
   public static String writeStars(int numOfStars) {
      if (numOfStars < 0) { throw new IllegalArgumentException("Can't print negative stars");}
      
      if (numOfStars == 0) {
         return "*";
      }
      
      return writeStars(numOfStars - 1) + writeStars(numOfStars - 1);
   }
   
   // pre: num >= 0
   // Display num in binary format
   public static void writeBinary(int num) {
      // base case
      if (num <= 1) {
         System.out.print(num);
      }
      else {
         int lowestBit = num % 2;
         int restOfBits = num / 2; // same as >> 1
         
         writeBinary(restOfBits); // recursive case
         writeBinary(lowestBit); // re-use of the base case
      }
   }
   
   public static long pow(int base, int exponent) {
      if (exponent == 0) {
         return 1;
      }
      else {
         long num = pow(base, exponent - 1);
         return base * num;
      }
   }
   
   // pre: n >= 0
   // double the digits in a nmber: 1234 => 11223344
   public static int digitDoubler(int num) {
      // special case
      if (num < 0) {
         return -digitDoubler(-num);
      }
      //base case
      else if (num < 10) {
         return num * 11;
      }
      
      // recursive case
      int lowestDigit = num % 10;
      int highestDigit = num / 10;
         
      int lowDouble = digitDoubler(lowestDigit);
      int highDouble = digitDoubler(highestDigit);
         
      return highDouble * 100 + lowDouble;
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
   
   public static void stop2(int x) {
      System.out.println(x);
      
      if (x > 777) {
         stop(x - 1);
      }
      else if (x < 777) {
         stop(x + 1);
      }
   }
}