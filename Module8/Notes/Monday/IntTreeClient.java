public class IntTreeClient {
    public static void main(String[] args) {
        IntTree tree = new IntTree(5);

        tree.overallRoot = new IntTree.IntTreeNode(7);
        tree.overallRoot.left = new IntTree.IntTreeNode(14);
        tree.overallRoot.right = new IntTree.IntTreeNode(42);
        tree.overallRoot.left.left = new IntTree.IntTreeNode(22);
        tree.overallRoot.right.right = new IntTree.IntTreeNode(9);
        
        tree.printPreorder();
        System.out.println();
        tree.printPostorder();
        System.out.println();
        tree.printInOrder();
        
        System.out.println();
        
        tree.printSideways();
        System.out.println();
        tree.bubbleLeft();
        tree.printSideways();
    }
}