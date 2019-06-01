import java.util.Scanner;

/*    Author: Bryan Hyland
 *    Class:  CIS143 - Java 2
 *    Date:   31May19
 *    Time Worked On: 30 Minutes
 *    
 *    This class takes in a *.short file and prints the bits
 *    in binary format for that file.
 */

public class LabWeek9 {
   /* Main method creates a new BitInputStream using the getFileName()
    * method, and the uses the printBits method to print each bit
    * in binary format.
    */
   public static void main(String[] args) {
      BitInputStream stream = new BitInputStream(getFileName());
      printBits(stream);
   }
   
   /* pre: Takes in a BitInputStream object
    * post: Prints the bits in binary format
    * NOTE: The order of the bits are the same as the example 
    *       in the homework spec-simple.example.short
    */
   public static void printBits(BitInputStream stream) {
      // Counter for printing spaces for readability.
      int count = 0;
      
      // Prints out each bit in binary format
      while (stream.hasNextBit()) {
         System.out.print(stream.nextBit());
         
         ++count;
         
         if (count == 4) {
            System.out.print(" ");
            count = 0;
         }
      }
   }
   
   // pre: Takes in a file name from the user using a
   //      Scanner object.
   // post: Returns the filename.
   private static String getFileName() {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter a valid file name: ");
      String fileName = in.nextLine();
      
      return fileName;
   }
}
