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

    // Method 3 : TC : O(n), SC : O(H)
    private boolean valid;
    // Returns height of tree rooted at root;
    private int z(TreeNode root) {
        if(root == null) return 0;
        if(!valid) return -1; // early exit . -1 is just a sentinel it doesn't hold a meaning

        int lh = z(root.left);
        int rh = z(root.right);
        
        if(Math.abs(lh - rh) > 1) {
            valid = false;
        }

        return 1 + Math.max(lh, rh);
    }
     

    public boolean isBalanced(TreeNode root) {
        // return f(root);

        // return g(root).isBalanced;

        valid = true;
        z(root);
        return valid;
    }
}