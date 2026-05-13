
class Solution {
    private int minimumDiff;
    private int prev;
    
    // Inorder
    private void f(TreeNode root) {
        if(root == null) return;

        // L
        f(root.left);
        // N
        if(prev != Integer.MIN_VALUE) {
            int currDiff = Math.abs(prev - root.val);
            minimumDiff = Math.min(minimumDiff, currDiff);
        } 
        prev = root.val;
        // R
        f(root.right);
    }

    public int minDiffInBST(TreeNode root) {
        prev = Integer.MIN_VALUE;   
        minimumDiff = Integer.MAX_VALUE;
        f(root);
        return minimumDiff;
    }
}