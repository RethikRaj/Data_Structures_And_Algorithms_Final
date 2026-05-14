public class Solution {

    // Brute force
    private void inorder(TreeNode root, List<TreeNode> inorderList) {
        if(root == null) return;

        inorder(root.left, inorderList);
        inorderList.add(root.val);
        inotder(root.right, inorderList);
    }
    

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
    }
}
