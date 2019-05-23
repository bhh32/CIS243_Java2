public class IntTree {
   public IntTreeNode overallRoot;
   
   // pre: numberOfNode >= 0
   // post: tree with that number of nodes
   public IntTree(int numberOfNodes) {
      assert numberOfNodes >= 0;
      
      overallRoot = buildTree(1, numberOfNodes);
   }
   
   private IntTreeNode buildTree(int n, int numberOfNodes) {
      if (n > numberOfNodes) {
         return null;
      }
      else {
         IntTreeNode left = buildTree(2 * n, numberOfNodes);
         IntTreeNode right = buildTree(2 * n + 1, numberOfNodes);
         IntTreeNode root = new IntTreeNode(n, left, right);
         return root;
      }
   }
   
   public void printPreorder() {
      _printPreorder(overallRoot);
   }
   
   // pre: ... (have an instance, which goes without sayaing)
   // post: printed root's data, then the left ST then the right ST
   private void _printPreorder(IntTreeNode root) {
      if (root != null) {
         System.out.print(" " + root.data);
         _printPreorder(root.left);
         _printPreorder(root.right);
      }
   }
   
   public void printPostorder() {
      _printPostorder(overallRoot);
   }
   
   private void _printPostorder(IntTreeNode root) {
      if (root != null) {
         _printPostorder(root.left);
         _printPostorder(root.right);
         System.out.print(" " + root.data);
      }
   }
   
   public void printInOrder() {
      _printInOrder(overallRoot);
   }
   
   private void _printInOrder(IntTreeNode root) {
      if (root != null) {
         _printInOrder(root.left);
         System.out.print(" " + root.data);
         _printInOrder(root.right);
      }
   }
   
   public String toString() {
      return _toString(overallRoot);
   }
   
   private String _toString(IntTreeNode root) {
      if (root == null) {
         return "";
      }
      else {
         String leftToString = _toString(root.left);
         String rightToString = _toString(root.right);
         return (leftToString + " " + root.data + " " + rightToString + " ").trim();
      }      
   }
   
   public IntTreeNode bubbleLeft() {
      overallRoot = _bubbleLeft(overallRoot);
   }
   
   private IntTeeNode _bubbleLeft(IntTreeNode root) {
      if (root == null) {
         return null;
      }
      
      IntTreeNode left = _bubbleLeft(root.left);
      
      if (left == null) {
         return root;
      }
      
      IntTreeNode rootsRight = root.right;
      IntTreeNode leftsRight = left.right;
      
      left.right = rootsRight;
      root.right = leftsRight;
      
      root.left = left.left;
      
      left.left = root;
      
      return left;
   }

   public static class IntTreeNode {
       private final int data;
       public IntTreeNode left;
       public IntTreeNode right;
    
       public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
           this.data = data;
           this.left = left;
           this.right = right;
       }
    
       public IntTreeNode(int data){
           this(data, null, null);
       }
   }
}

