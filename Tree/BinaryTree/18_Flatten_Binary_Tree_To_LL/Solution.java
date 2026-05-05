/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public void flatten(TreeNode root) {
        // MethodOne - store preorder , then iterate and insert at tail

        // Method two - inplace
        TreeNode curr = root;
        while(curr != null) {
            if(curr.left == null) {
                curr = curr.right;
            } else {
                // 1. Link leftmost mode of right sub tree to curr.right;
                TreeNode temp = curr.left;
                while(temp.right != null) {
                    temp = temp.right;
                }
                temp.right = curr.right;
                // 2. Point right to left , and left to null
                curr.right = curr.left;
                curr.left = null;
                // 3. Move to next node.
                curr = curr.right;
            }
        }
    }
}