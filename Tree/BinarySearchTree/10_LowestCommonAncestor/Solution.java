
class Solution {
    private TreeNode f(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p) return p;
        if(root == q) return q;

        // p and q both are in left sub tree
        if(root.val > p.val && root.val > q.val) {
            return f(root.left, p, q);
        }

        // p and q both are in right sub tree
        if(root.val < p.val && root.val < q.val) {
            return f(root.right, p, q);
        }

        // p and q both are in opposite sub tree => current node is the LCA
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return f(root, p, q);
    }
}