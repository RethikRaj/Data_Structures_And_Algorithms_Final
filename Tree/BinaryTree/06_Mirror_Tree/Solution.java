class Solution {
    public void f(TreeNode root) {
        if(root == null) return;

        f(root.left);
        f(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public TreeNode invertTree(TreeNode root) {
        f(root);
        return root;
    }
}