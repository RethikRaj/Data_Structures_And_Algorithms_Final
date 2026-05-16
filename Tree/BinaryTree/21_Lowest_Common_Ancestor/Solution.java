class Solution {
    public TreeNode f(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p) return p;
        if(root == q) return q;

        TreeNode left = f(root.left, p, q);
        TreeNode right = f(root.right, p, q);

        if(left == null && right == null) {
            return null;
        }else if(left != null && right == null) {
            return left;
        }else if(left == null && right != null) {
            return right;
        }else {
            return root;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return f(root, p, q);
    }
}