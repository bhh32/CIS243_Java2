public class NodeDemo {
   public static void main(String[] args) {
      IntNode node; // Holds a reference to a node, not a Node object
      node = new IntNode(7); // new creates object on the heap, node now holds a referenct to the new object  
      
      node.next = new IntNode(10);
      node.next.next = new IntNode(12);
      
      IntNode p = new IntNode(2, new IntNode(4));
      IntNode q = new IntNode(3);
      q.next = new IntNode(9);
      
      p.next.next = q;
      q = q.next;
      p.next.next.next = null;
      
      print(p);
      mystery(p);
      print(p);
      
      
      System.out.println(sum(p));   
   }
   
   // Removes the second node in a list of nodes, if there is one
   private static void mystery(IntNode node) {
      if (node != null && node.next != null) {
         node.next = node.next.next;
      }     
   }
   
   private static void print(IntNode current) {
      while (current != null) {
         System.out.print(current.data + " ");
         current = current.next;
      }
      
      System.out.println();
   }
   
   private static int sum(IntNode node) {
      // node is a passed by value
      int sum = 0;
      while (node != null) {
         sum += node.data;
         node = node.next;
      }
      
      return sum;
   }
}