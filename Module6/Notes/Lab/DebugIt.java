import java.util.*;

public class DebugIt {
   public static void main(String[] args) {
      // isConsecutive Testing
      /*
      Stack<Integer> stk = addToStackConsec(6);
      System.out.println("stk is consectutive: " + isConsecutive(stk));
      System.out.println(stk);
      */
      
      ArrayIntList list = new ArrayIntList();
      list.add(1, 2, 3, 4, 5, 6, 7, 8);
      
      list.print();
   }
   
   public static boolean isConsecutive(Stack<Integer> s) {
    if (s.size() < 2) { return true; }
    
    Queue<Integer> holdingQ = new LinkedList<Integer>();
    int size = s.size();
    boolean isConsec = true;
    
    // Put Queue in the right order
    while (!s.isEmpty()) {
        holdingQ.add(s.pop());   
    }    
    while (!holdingQ.isEmpty()) {
      s.push(holdingQ.remove());
    }    
    while (!s.isEmpty()) {
      holdingQ.add(s.pop());
    }
    
    // Put the stack back together and check if the bottom of the
    // stack is consecutive with the next number to be added that
    // is still in the queue.
    while (!holdingQ.isEmpty()) {
        s.push(holdingQ.remove());
        
        if (!holdingQ.isEmpty() && !(holdingQ.peek() == s.peek() + 1)) {
            isConsec = false;
        }
    }
    
    return isConsec;
   }
   
   private static Stack<Integer> addToStackConsec(int numOfElements) {
      Stack<Integer> tempStack = new Stack<Integer>();
      
      for (int i = 0; i < numOfElements; ++i) {
         tempStack.push(i);   
      }
      
      return tempStack;
   }
   
   public static void mystery(int x) {
       if (x < 10)
           System.out.print(x);
       else {
           int y = x % 10;
           System.out.print(y);
           mystery(x / 10);
           System.out.print(y);
       }
   }
}