public class IntNode {
   public final int data;
   public IntNode next; // Holds a reference to a node NOT a node
   
   public IntNode(int data) {
      this(data, null);
   }
   
   public IntNode(int data, IntNode next) {
      this.data = data;
      this.next = next;
   }
}