/*
  Author: Bryan Hyland
  Date:   3May19
  Lab#:   Lab Week 5
*/

public class LabWeek5 {
  // Returns how many numbers are different between the two test cases
  public static int digitDiff(int num1, int num2) {
    if (num1 < 0 || num2 < 0) {
      throw new IllegalArgumentException("One or both of your numbers are "
      + "negative!");
    }

    // call recursive version
    return _digitDiff(num1, num2);
  }

  private static int _digitDiff(int num1, int num2) {
      int diff = 0;
      int num1Length = new String(new Integer(num1).toString()).length();
      int num2Length = new String(new Integer(num2).toString()).length();
      int num1Rest = num1 / 10;
      int num1Last = num1 % 10;
      int num2Rest = num2 / 10;
      int num2Last = num2 % 10;
    
    // Base case for last number same length
    if (num1 == num2 && num1Rest == 0 && num2Rest == 0) {
      return diff;
    }
    
    // Increment diff one last time on the last pass
    if (num1Length == 1 && num2Length == 1
            && num1Rest == 0 && num2Rest == 0) {
         return ++diff;        
    }   
    
    // Add one to diff if they aren't the same
    if (num1Last != num2Last) {
      diff++;
    }
    
    // Add to and return diff with the recursive call
    return diff += _digitDiff(num1Rest, num2Rest);
  }


  /* This gatekeeper and its helper are recreations from what I remembered
     of us working on this problem from practiceIt! in class. The gatekeeper
     method addDashes has a single check of if the number is negative, prints
     out the first dash, and ensures num stays negative. Then it calls the
     recursive version of _addDashes.
  */
  public static void addDashes(int num) {
    if (num < 0) {
      System.out.print("-");
      num = -num;
    }

    _addDashes(num);
  }

  /* This is the recursive version of _addDashes. I checks to see if num is a
     single digit, and then prints it out. If it's not a single digit it pops
     off the first number from the parameter and prints it, then a dash, and
     then recurses doing the same thing for the rest of the number until the
     last digit is reached.
  */
  private static void _addDashes(int num) {
    if (num < 10) {
      System.out.print(num);
    }
    else {
      int first = num / 10;
      int rest = num % 10;
      _addDashes(first);
      System.out.print("-");
      _addDashes(rest);
    }
  }
  
  public static void main(String[] args) {
     // Test Negative number addDashes
     addDashes(-12); // Output -1-2
     
     System.out.println();
     
     // Test Zero addDashes
     addDashes(0); // 0
     
     System.out.println();
     
     // Test Positive number addDashes
     addDashes(456789); // 4-5-6-7-8-9
  }                                      
}
