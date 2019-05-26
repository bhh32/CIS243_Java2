public class IntTree {
 
   private IntTreeNode overallRoot;
   
   // pre: numberOfNodes >= 0 
   // post: tree with that number of nodes
   public IntTree(int numberOfNodes) {
      assert numberOfNodes >= 0;
      
      // x = change(x)
      overallRoot = buildTree(1, numberOfNodes);
   }
   
   private IntTreeNode buildTree(int n, int numberOfNodes/*, IntTreeNode root*/) {
       if (n > numberOfNodes) {
          return null;
       } else {
          IntTreeNode left = buildTree(2 * n, numberOfNodes);
          IntTreeNode right = buildTree(2 * n + 1, numberOfNodes);
          IntTreeNode root = new IntTreeNode(n, left, right);
          return root;
       }
   }
 
   // return the post-order traversal of the tree
   public String toString() {
      return _toString(overallRoot);
   }

   // return the post-order traversal starting at root   
   private String _toString(IntTreeNode root) {
      if (root == null) {
          return "";
      } else {
          String leftToString = _toString(root.left);
          String rightToString = _toString(root.right);
          return leftToString + rightToString + " " + root.data;
      } 
   }
   
   public void printPreorder() {
      _printPreorder(overallRoot);
   }
   
   // pre: ... (have an instance, which goes without saying)
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
   
   // pre: ... (have an instance, which goes without saying)
   // post: printthe left ST then the right ST then root's data
   private void _printPostorder(IntTreeNode root) {
      if (root != null) {
          _printPostorder(root.left);
          _printPostorder(root.right);
          System.out.print(" " + root.data);
      }
   }
   
   public void printInorder() {
      _printInorder(overallRoot);
   }
   
   // pre: ... (have an instance, which goes without saying)
   // post: print the left ST then root's data then the right ST 
   private void _printInorder(IntTreeNode root) {
      if (root != null) {
          _printInorder(root.left);
          System.out.print(" " + root.data);
          _printInorder(root.right);
      }
   }
   
   public int countEmpty() {
       return _countEmpty(overallRoot);
   }
   
   // pre: well-formed binary tree
   // post: returns # of nulls starting at root
   private int _countEmpty(IntTreeNode root) {
       if (root == null) {
           return 1;
       } else {
           int leftNulls = _countEmpty(root.left);
           //int rightNulls = _countEmpty(root.right);
           return leftNulls + rightNulls;
       }
   }
   
   public IntTreeNode bubbleLeft() {
      overallRoot = _bubbleLeft(overallRoot);
   }
   
   private IntTeeNode _bubbleLeft(IntTreeNode root) {
      if (root == null) {
         return null;
   }
      
   public void remove(int value) {
      overallRoot = remove(value, overallRoot);
   }
   
   private IntTreeNode(int value, IntTreeNode root) {
      if (root != null) {
         if (value < root.data) {
            root.left = remove(value, root.left);
         }
         else if (value > root.data) {
            root.right = remove(value, root.right);
         }
         else {
            IntTreeNode leftChild = root.left;
            IntTreeNode rightChild = root.right;
            
            root = rightChild;
            add(rightChild.leftChild);
         }
      }
      
      return root;
   }
   
   public void add(int value) {
      overallRoot = _add(overallRoot, new IntTreeNode(value));
   }
   
   // Node is put in the right place for the given root and node
   private void add(IntTreeNode root, IntTreeNode node) {
      root.left = add(root.left, node);
   } 
   
   // pre: node may be a repeat (used for both add)
   // post: node is in the proper location
   private IntTreeNode _add(IntTreeNode root, IntTreeNode node) {
      if (root == null) {
         root = node;
      }
      else if (node.data < root.data) {
         root.left = _add(root.left, node);
      }
      else if (node.data > root.data) {
         root.right = _add(root.right, data);
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