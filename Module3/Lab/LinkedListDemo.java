public class LinkedListDemo {
   private ListNode front;
   
   public int get(int idx) {
      checkIndex(idx, size() - 1);
      
      ListNode getter = walkToNode(idx);
      
      return getter.data;
   }
   
   public void add(int value) {
      add(size(), value);
   }
   
   public void add(int idx, int value) {
      checkIndex(idx, size());
      
      ListNode previous = walkToNode(idx - 1);
      
      // Previous is null if either front is null
      // or idx == 0
      if (previous == null) {
         front = new ListNode(value, front);
      }
      else {
         ListNode newNode = new ListNode(value);
         newNode.next = previous.next;
         previous.next = newNode;
      }
   }
   
   // pre: index is at least 1 less than size.
   private ListNode walkToNode(int idx) {
      if (front == null || idx < 0) {
         return null;
      }
      
      ListNode current = front;
      for (int i = 0; i < idx; ++i) {
         current = current.next;
      }
      
      return current;
   }
   
   private static void checkIndex(int idx, int max) {
      if (idx > max || idx < 0) {
         throw new IndexOutOfBoundsException("Can't have and index beyond the end of the list, or less than 0");
      }
   }
   
   public void remove(int idx) {
      checkIndex(idx, size() - 1);
      
      ListNode previous = walkToNode(idx - 1);
      
      if (previous == null) {
         front = front.next;
      }
      else {
         previous.next = previous.next.next;
      }
   }
   
   public void set(int idx, int value) {
      checkIndex(idx, size() - 1);
      ListNode previous = walkToNode(idx - 1);
      if (previous == null) {
         front = new ListNode(value, front.next);
      }
      else {      
         ListNode newNode = new ListNode(value, previous.next.next);
         previous.next = newNode;
      }
   }
   
   public int indexOf(int value) {      
      if (front != null) {
         ListNode current = front;
         int currentIdx = 0;
         
         while (current != null) {
            if (current.data == value) {
               return currentIdx;
            }            
            current = current.next;
            currentIdx++;
         }
      }      
      // Either empty list or not in our list
      return -1;
   }
   
   // post: returns the number of nodes in the list.
   public int size() {
      ListNode current = front;
      int size = 0;
      
      while (current != null) {
         size++;
         current = current.next;
      }
      
      return size;
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
      LinkedListDemo list = new LinkedListDemo();
      list.add(10);
      list.add(34);
      list.add(1, 42);
      list.set(1, 12);
      list.set(0, 24);
      list.set(2, -42);
      System.out.println(list);
      
      list.reorder();
      System.out.println(list);
   }
}