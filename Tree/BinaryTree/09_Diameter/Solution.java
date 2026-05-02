/**
 * Holds height and diameter info for a subtree.
 * height   = number of nodes from this node to its deepest leaf
 * diameter = longest path (in edges) found within this subtree
 */
class Info {
    int height; 
    int diameter;

    Info(int height, int diameter) {
        this.height   = height;
        this.diameter = diameter;
    }
}

class Solution {
    // METHOD 1 — Brute Force (O(N²))
    // For every node, independently compute the heights of its left and right
    // subtrees, then take the maximum of:
    //   • the diameter passing through this node  (leftH + rightH + edges)
    //   • the best diameter found in either subtree

    /** Returns height as node count of the subtree rooted at 'node'. */
    private int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Returns the diameter (in edges) of the binary tree rooted at 'root'. */
    private int f(TreeNode root) {
        if (root == null) return 0;

        int lh = height(root.left);  // node count of left subtree  (0 if absent)
        int rh = height(root.right); // node count of right subtree (0 if absent)

        // Diameter through root (in edges):
        int diameterPassingThroughRoot = lh + rh; 
        int diameterWithoutPassingthroughRoot =  Math.max(f(root.left), f(root.right));
        return Math.max(diameterPassingThroughRoot, diameterWithoutPassingthroughRoot);
    }

    // METHOD 2 — Optimised (O(N))
    // A single post-order traversal computes both height and diameter together, so each node is visited exactly once

    private Info g(TreeNode root) {
        if (root == null) return new Info(0, 0);

        Info left  = g(root.left);
        Info right = g(root.right);

        int currHeight = 1 + Math.max(left.height, right.height);

        // diameter through root (edges) = left.height + right.height
        int diameterPassingThroughRoot = left.height + right.height;
        int diameterWithoutPassingthroughRoot = Math.max(left.diameter, right.diameter);

        int currDiameter = Math.max(diameterPassingThroughRoot, diameterWithoutPassingthroughRoot);

        return new Info(currHeight, currDiameter);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return f(root);       // Method 1 — O(N²)
        // return g(root).diameter; // Method 2 — O(N)
    }
}