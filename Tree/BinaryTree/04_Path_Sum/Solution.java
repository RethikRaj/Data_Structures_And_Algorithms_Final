class Solution {

    // f(root, remainingSum) -> returns true if there is a path starting from root == 'root' till leaf node such that the sum of all node values along the path equals remainingSum.
    public boolean f(TreeNode root, int remainingSum) {
        // Base case : node must be leafNode and remainingSum == leafNode.val
        if(root.left == null && root.right == null) return remainingSum == root.val;

        boolean leftPath = (root.left != null) ? f(root.left, remainingSum - root.val) : false;
        boolean rightPath = (root.right != null) ? f(root.right, remainingSum - root.val) : false;
        return leftPath || rightPath;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        return f(root, targetSum);    
    }
}
