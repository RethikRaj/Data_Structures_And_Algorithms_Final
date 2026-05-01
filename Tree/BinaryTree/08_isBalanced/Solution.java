class Info {
    int height;
    boolean isBalanced;
    Info(int height, boolean isBalanced) {
        this.height = height;
        this.isBalanced = isBalanced;
    }
}

class Solution {

    // Method 1 : O(n^2) — height() is called repeatedly for every node
    public int height(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean f(TreeNode root) {
        if(root == null) return true;

        int heightOfLeftSubTree = height(root.left);
        int heightOfRightSubTree = height(root.right);

        boolean isRootBalanced = Math.abs(heightOfLeftSubTree - heightOfRightSubTree) <= 1;

        return isRootBalanced && f(root.left) && f(root.right);
    }

    // Method 2 : O(n) — post-order, carry height + balance status up in one pass
    public Info g(TreeNode root) {
        if(root == null) return new Info(0, true);

        Info left = g(root.left);
        Info right = g(root.right);

        int currNodeHeight = 1 + Math.max(left.height, right.height);
        // unbalanced if: height diff > 1, OR either subtree already reported unbalanced
        boolean currNodeBalanced = Math.abs(left.height - right.height) <= 1 && left.isBalanced && right.isBalanced;

        return new Info(currNodeHeight, currNodeBalanced);
    }

    public boolean isBalanced(TreeNode root) {
        // return f(root);

        return g(root).isBalanced;
    }
}