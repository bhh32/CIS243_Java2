// For Week 3's Lab exercises, a minimal LinkedIntList

public class LinkedIntList {

   private ListNode front;

   // add your code here
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
   
   public void nodeFlip() {
      if (front == null) {
         add(0);
      }
      
      ListNode current = front;
      boolean firstFlip = true;
      while (current.next != null) {
         ListNode temp = flip(current, current.next);
         if (firstFlip) {
            front = temp;
            firstFlip = false;
         }
         else {
            temp.next = current.next;
            current.next = temp;
         }
         current = current.next;
         System.out.println();
      }
   }
   
   private static ListNode flip(ListNode first, ListNode second) {      
      ListNode temp = second;
      ListNode secondTemp = second.next;
      second.next = first;
      first = second;
      
      second.next.next = secondTemp;
      second.next = first.next;
      
      return first;
   }

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
   
   private void printLinkedIntList() {
      ListNode current = front;
      
      while (current.next != null) {
         System.out.print(current.data + " ");
            
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
      
      System.out.println(list.sum());
      list.nodeFlip();
      System.out.println(list); // output: 2, 3, 5, 10, 12
   }

}