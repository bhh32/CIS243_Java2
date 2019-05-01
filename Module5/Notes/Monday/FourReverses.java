import java.util.Scanner;
import java.util.Stack;

public class FourReverses {

  public static void main(String[] args) {
    Scanner input;
    System.out.println("Reverse 0");
    input = new Scanner("reverse\nme\nnow\nplease\n");
    reverse0(input);
    System.out.println("Reverse 1");
    input = new Scanner("reverse\nme\nnow\nplease\n");
    reverse1(input);
    System.out.println("Reverse 2");
    input = new Scanner("reverse\nme\nnow\nplease\n");
    reverse2(input);
    System.out.println("Reverse 3");
    input = new Scanner("reverse\nme\nnow\nplease\n");
    reverse3(input);
  }

  // standard iterative solution: supporting Stack data structure
  public static void reverse0(Scanner input) {
    Stack<String> words = new Stack<>();
    while (input.hasNextLine()) {
      words.push(input.nextLine());
    }
    while (!words.empty()) {
      System.out.println(words.pop());
    }
  }

  // recursive solution: call stack handles "storing" the values
  public static void reverse1(Scanner input) {
    if (input.hasNextLine()) {
      // recursive case (nonempty file)
      String line = input.nextLine();
      reverse1(input);
      System.out.println(line);
    }
  }

  // what does this one output? why?
  public static void reverse2(Scanner input) {
    if (input.hasNextLine()) {
      // recursive case (nonempty file)
      String line = input.nextLine();
      System.out.println(line);
      // swapped order
      reverse2(input);
      // swapped order
    }
  }

  // what does this one output? why?
  public static void reverse3(Scanner input) {
    if (input.hasNextLine()) {
      // recursive case (nonempty file)
      reverse3(input);
      // moved this line
      String line = input.nextLine();
      System.out.println(line);
    }
  }
}


