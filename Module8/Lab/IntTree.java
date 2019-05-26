/*
   Bryan Hyland
   CIS143
   25MAY19
   
   I spent about 6 hours on this. I almost had it quite a few times, but they would be
   out of order or missing one, or if I had one test work the other would be unbalanced.
*/

public class IntTree {

	private IntTreeNode overallRoot;
   
   public static void main(String[] args) {
      IntTree tree = new IntTree(7);
   }
   
   public IntTree(int n) {
      if (n < 0) { 
         throw new NullPointerException("The parameter " + n + " was negative!");
      }
      overallRoot = buildTree(n - 1, (n - 1) / 2, 0);
   }
   
   private IntTreeNode buildTree(int upperLimit, int midIdx, int idx) {
      if (upperLimit < 0 || idx > upperLimit || idx < 0) {
         return null;
      }
      
      if (upperLimit == 1) {
         return new IntTreeNode(idx);
      }
            
      IntTreeNode left = _buildTree(upperLimit, midIdx, 1, new IntTreeNode(0), null);
      IntTreeNode right = _buildTree(upperLimit, midIdx, midIdx + 1, new IntTreeNode(midIdx), new IntTreeNode(midIdx));         
      IntTreeNode root = new IntTreeNode(midIdx, left, right);
     
      return root;
   }
   
   private IntTreeNode _buildTree(int upperLimit, int midIdx, int idx, IntTreeNode prevNode, IntTreeNode tempRoot) {
      if (idx > upperLimit) {
         return null;
      }
      
      IntTreeNode newNode = new IntTreeNode(idx);
      
      // Left Side of Tree
      if (newNode.data < midIdx) {
         if (prevNode.data == 0) {
            newNode.left = prevNode;
         }
         else {
            prevNode.right = newNode;
         }
      }
      // Skip Root
      else if (idx == midIdx) {
         return null;
      }
      // Right Side Of Tree
      else {
         if (midIdx % 2 == 0) {
            if (prevNode.data == midIdx) {
               prevNode.right = new IntTreeNode(prevNode.data + 3);
               prevNode.right.left = newNode;
               tempRoot = prevNode;
            }
            
            if (newNode.data < tempRoot.right.data && newNode.data != tempRoot.right.left.data) {
               prevNode.right = newNode;
               
               if (newNode.data + 1 == tempRoot.right.data) {
                  prevNode = tempRoot.right;
                  idx += 2;
                  newNode = new IntTreeNode(idx);
                  tempRoot.right.right = newNode;   
               }
               else if (newNode.data > tempRoot.right.data) {
                  prevNode.right = newNode;                 
               } 
            }              
         }        
         else {
            if (prevNode.data == midIdx) {
               prevNode.right = new IntTreeNode(prevNode.data + 2);
               prevNode.right.left = newNode;
               tempRoot = prevNode;
            }
            else {
               if (prevNode.right != null && newNode.data < prevNode.right.data && newNode.data != tempRoot.right.left.data) {
                  prevNode.right = newNode;
                  
                  if (newNode.data + 1 == tempRoot.right.data) {
                     prevNode = tempRoot.right;
                     idx += 2;
                     newNode = new IntTreeNode(idx);
                     tempRoot.right.right = newNode;
                  }
               }
               else {
                  if (newNode.data > tempRoot.right.data) {
                     prevNode.right = newNode;
                  }
               }
            }
         }
         
               
         IntTreeNode temp = _buildTree(upperLimit, midIdx, idx + 1, newNode, tempRoot);
         
         if (temp == null) {
            newNode = tempRoot == null ? prevNode : tempRoot.right;
         }
         
         if (temp != null && newNode == temp.right || 
             temp != null && newNode == temp.left) {
            newNode = temp;
         }
      }
      
      return newNode;
   }
   
   // your methods go here
   // you may also need to add constructors and add methods
   // from class to have a fully functioning tree
   
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
         int rightNulls = _countEmpty(root.right);
         return leftNulls + rightNulls;
      }
   }

   // post: prints tree contents, one per line, with an inorder traversal,
   //       indenting to indicate node depth; prints right-to-left so
   //       tree looks correct when output is rotated.
   public void printSideways() {
      _printSideways(overallRoot, 0);
   }

   // post: prints in reversed preorder the tree with given root,
   //       indenting each line to the given level
   private void _printSideways(IntTreeNode root, int level) {
      if (root != null) {
         _printSideways(root.right, level + 1);
         for (int i = 0; i < level; i++) {
            System.out.print("    ");
         }
         System.out.println(root.data);
         _printSideways(root.left, level + 1);
      }
   }

   private static class IntTreeNode {
       public final int data;    // data stored in this node
       public IntTreeNode left;  // reference to left subtree
       public IntTreeNode right; // reference to right subtree

       public IntTreeNode(int data) {
           this(data,null,null);
       }
        
       public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
           this.data = data;
           this.left = left;
           this.right = right;
       }
   }

}