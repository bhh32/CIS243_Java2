public class TreeIntSet {

   private IntTreeNode overallRoot;
   
   public TreeIntSet() {
   
   }
   
   // post: value is added in a new node in the appropriate leaf location
   public void add(int value) {
      overallRoot = add(value, overallRoot);
   }
   
   // post: returns the root of the tree with value in the right place
   private IntTreeNode add(int value, IntTreeNode root) {
      if (root == null) {
         return new IntTreeNode(value);
      }
      
      if (value < root.data) {
         root.left = add(value, root.left);
      }
      else if (value > root.data) {
         root.right = add(value, root.right);
      }
      
      return root;
   }
   
   public static class IntTreeNode {
   
      public final int data;
      public IntTreeNode left;
      public IntTreeNode right;
      
      public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
         this.data = data;
         this.left = left;
         this.right = right;
      }
      
      public IntTreeNode(int data) {
         this(data, null, null);
      }
   
   }
}