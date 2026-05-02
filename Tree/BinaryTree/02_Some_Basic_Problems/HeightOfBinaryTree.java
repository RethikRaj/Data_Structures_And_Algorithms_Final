public class HeightOfBinaryTree {
    // f(root) = no.of.nodes along the longest path between root node to the farthest leaf node (both start, end included)
    public int f(TreeNode root) {
        if(root == null) return 0;

        return 1 + Math.max(f(root.left), f(root.right));
    }

    // g(root) = no.of.edges along the longest path between root node to the farthest leaf node
    public int g(TreeNode root) {
        if(root == null) return -1;

        return 1 + Math.max(g(root.left), g(root.right));
    }
}
