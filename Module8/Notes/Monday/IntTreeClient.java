public class IntTreeClient {
    public static void main(String[] args) {
        IntTree tree = new IntTree(5);

        tree.overallRoot = new IntTree.IntTreeNode(7);
        tree.overallRoot.left = new IntTree.IntTreeNode(14);
        tree.overallRoot.right = new IntTree.IntTreeNode(42);
        tree.overallRoot.left.left = new IntTree.IntTreeNode(22);
        tree.overallRoot.right.right = new IntTree.IntTreeNode(9);
        
        System.out.println("Preorder");
        tree.printPreorder();
        System.out.println();
        
        System.out.println("Postorder");
        tree.printPostorder();
        System.out.println();
        
        System.out.println("In-order");
        tree.printInOrder();
        
        System.out.println();
    }
}