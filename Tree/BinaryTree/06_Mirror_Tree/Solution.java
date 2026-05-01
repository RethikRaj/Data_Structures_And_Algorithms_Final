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
    // Post-order | void — recurse both sides first, then swap at current node
    public void f(TreeNode root) {
        if(root == null) return;

        f(root.left);
        f(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    // Post-order | return TreeNode — invert subtrees first, then attach them swapped to current node
    public TreeNode g(TreeNode root) {
        if(root == null) return null;

        TreeNode leftInverted = g(root.left); // invert left subtree
        TreeNode rightInverted = g(root.right); // invert right subtree
        // swap
        root.left = rightInverted;
        root.right = leftInverted;

        return root;
    }

    // Pre-order | void — swap first, then recurse (children are already in new positions)
    public void f_pre(TreeNode root){
        if(root == null) return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        f_pre(root.left);
        f_pre(root.right);
    }

    // Pre-order | return — swap at current node first, then recurse down the swapped children
    public TreeNode g_pre(TreeNode root) {
        if(root == null) return null;

        // swap
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        TreeNode leftInverted = g_pre(root.left); // invert left subtree
        TreeNode rightInverted = g_pre(root.right); // invert right subtree
        

        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        // f(root);
        // return root;

        // TreeNode invertedRoot = g(root);
        // return invertedRoot;

        // f_pre(root);
        // return root;

        TreeNode invertedRoot = g_pre(root);
        return invertedRoot;

        
    }
}