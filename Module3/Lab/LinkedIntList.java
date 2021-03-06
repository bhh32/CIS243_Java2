// For Week 3's Lab exercises, a minimal LinkedIntLis
/*
   Bryan Hyland
   CIS143 - Java 2
   LinkedIntList
*/
public class LinkedIntList {
   // First node in the linked list (reference)
   private ListNode front;
   
   // Adds up all of the data in the list of nodes
   public int sum() {
      ListNode current = front;
      
      if (current == null) {
         return 0;
      }
      
      int sum = 0;
      while (current != null) {
         sum += current.data;
         current = current.next;
      }
      
      return sum;
   }
   
   // Flips the nodes in the list leaving the
   // last one if the lists size is odd.
   public void nodeFlip() {
   
      // Adds one to the list is empty
      if (front == null) {
         add(0);
      }
      
      ListNode current = front;
      ListNode temp = current;
      
      // flag to know if it's the first two nodes
      // being flipped.
      boolean firstFlip = true;
      
      // Flip the nodes in the list
      while (current.next != null) {
         ListNode retNode = flip(current, current.next);
         if (firstFlip) {
            front = retNode;
            firstFlip = false;
         }
         else {
            temp.next = retNode;
            temp = current;
         }
         
         // Ensures that the new current isn't set to a null
         if (current.next != null) {
            current = current.next;
         }
      }
   }
   
   // Helper method that flips the given nodes, and returns the "first" one
   private static ListNode flip(ListNode first, ListNode second) {      
      ListNode temp = second.next;
      second.next = first;
      first = second;
      
      second.next.next = temp;
      second.next = first.next;
      
      return first;
   }


   // add your code here
   
   public void add(int data) {
      ListNode current = front;
      if (current == null) {
         front = new ListNode(data);
      } else {
         while (current.next != null) {
            current = current.next;
         }
         current.next = new ListNode(data);
      }
   }

   public String toString() {
      String result = "";
      if (front != null) {
         result += front.data;
         ListNode current = front.next;
         while (current != null) {
            result += ", "+current.data;
            current = current.next;
         }
      }
      return "["+result+"]";
   }

   private static class ListNode {
      public final int data;	   // data stored in this node
      public ListNode next;  // a link to the next node in the list

      public ListNode(int data) { this(data,null); }
      public ListNode(int data, ListNode next) {
         this.data = data;
         this.next = next;
      }
   }
   
   public static void main (String[] args) {
      LinkedIntList list = new LinkedIntList();
      list.add(3);
      list.add(2);
      list.add(10);
      list.add(5);
      list.add(12);
      list.add(42);
      list.add(51);
      
      System.out.println(list.sum());
      list.nodeFlip();
      System.out.println(list); // output: 2, 3, 5, 10, 12
   }

}
