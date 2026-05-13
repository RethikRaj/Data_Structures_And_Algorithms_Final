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

    public TreeNode recursiveSearch(TreeNode root, int val) {
        if(root == null) return null;

        if(root.val == val) return root;
        else if(root.val < val) return recursiveSearch(root.right, val);
        else return recursiveSearch(root.left, val);
    }

    public TreeNode iterativeSearch(TreeNode root, int val) {
        TreeNode curr = root;
        while(curr != null) {
            if(curr.val == val) {
                return curr;
            }else if(curr.val < val) {
                curr = curr.right;
            }else {
                curr = curr.left;
            }
        }

        return curr;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        return recursiveSearch(root, val);
        // return iterativeSearch(root, val);
    }
}