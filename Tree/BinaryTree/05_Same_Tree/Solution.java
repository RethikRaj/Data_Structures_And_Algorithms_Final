class Solution {
    public boolean f(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null && q != null) return false;
        if(p != null && q == null) return false;

        return p.val == q.val && f(p.left, q.left) && f(p.right, q.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return f(p, q);
    }
}
