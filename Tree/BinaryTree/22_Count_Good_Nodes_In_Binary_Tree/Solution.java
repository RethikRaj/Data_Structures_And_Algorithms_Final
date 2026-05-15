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
    private int f(TreeNode root, int maximumInCurrentPath) {
        if(root == null) return 0;

        int count = 0;
        if(root.val >= maximumInCurrentPath) {
            count += 1;
        }

        count += f(root.left, Math.max(maximumInCurrentPath, root.val));
        count += f(root.right, Math.max(maximumInCurrentPath, root.val));

        return count;
    }

    public int goodNodes(TreeNode root) {
        return f(root, Integer.MIN_VALUE);
    }
}