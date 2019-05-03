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

    // Get the longer numbers size
    int longest = new String(num1).size() >= new String(num2).size()
      ? new String(num1).size() : new String(num2).size();

    // call recursive version
    return digitDiff(num1, num2, longest, 0);
  }

  private static int digitDiff(int num1, int num2, int longest, int index) {
    int diff = 0;

    return diff;
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

    _addDashes(n);
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
}
