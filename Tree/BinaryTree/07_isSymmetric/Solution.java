class Solution {
    // f(p, q) - returns true if the root having root node as p is `mirror image` of root node having q.
    public boolean f(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null && q != null) return false;
        if(p != null && q == null) return false;

        return (p.val == q.val) && f(p.left, q.right) && f(p.right, q.left);
    }
    public boolean isSymmetric(TreeNode root) {
        return f(root.left, root.right);
    }
}