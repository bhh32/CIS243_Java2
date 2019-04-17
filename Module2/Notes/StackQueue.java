import java.util.*;

public class StackQueue {
   public static void main(String[] args) {
      String[] words = { "To", "be", "or", "not", "to", new String("be") };
      
      Stack<String> stack = new Stack<String>();
      Queue<String> queue = new LinkedList<String>();
      
      for (String word : words) {
         stack.push(word); // added to top
         queue.add(word); // added to bottom
      }
      
      System.out.println("Stack is: " + stack);
      System.out.println("Queue is: " + queue);
      
      for (int i = 0; i < queue.size(); i++) {
         String word = queue.remove();
         queue.add(word);
      }      
      System.out.println("Queue is: " + queue);
   }
}