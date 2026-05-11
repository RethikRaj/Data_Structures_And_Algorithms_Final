/**
 * Holds height and maxDiameter info for a subtree.
 * height   = number of nodes from this node to its deepest leaf
 * maxDiameter = longest path (in edges) found within this subtree
 */
class Info {
    int height; 
    int maxDiameter;

    Info(int height, int maxDiameter) {
        this.height   = height;
        this.maxDiameter = maxDiameter;
    }
}

class Solution {
    // METHOD 1 — Brute Force 
    // TC: O(N²)  SC: O(H)
    // height() is called independently at every node → redundant traversals.

    /** Returns height as node count of the subtree rooted at 'node'. */
    private int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Returns the diameter (in edges) of the binary tree rooted at 'root'. */
    private int f(TreeNode root) {
        if (root == null) return 0;

        int lh = height(root.left);  
        int rh = height(root.right);

        // Diameter through root (in edges):
        int diameterPassingThroughRoot = lh + rh; 
        int diameterWithoutPassingthroughRoot =  Math.max(f(root.left), f(root.right));
        return Math.max(diameterPassingThroughRoot, diameterWithoutPassingthroughRoot);
    }

    // METHOD 2 — Optimised
    // TC: O(N)  SC: O(H)
    // A single post-order traversal computes both height and diameter together, so each node is visited exactly once
    private Info g(TreeNode root) {
        if (root == null) return new Info(0, 0);

        Info left  = g(root.left);
        Info right = g(root.right);

        int currHeight = 1 + Math.max(left.height, right.height);

        // maxDiameter either crosses this node or was already found deeper
        // diameter through root (edges) = left.height + right.height
        int diameterPassingThroughRoot = left.height + right.height;
        // diameter already found deeper
        int diameterWithoutPassingthroughRoot = Math.max(left.diameter, right.diameter);
        
        int maxDiameter = Math.max(diameterPassingThroughRoot, diameterWithoutPassingthroughRoot);

        return new Info(currHeight, maxDiameter);
    }

    // Method 3 : Optimised
    // TC: O(N)  SC: O(H)
    private int maxDiameter;

    // returns height of tree rooted at root.
    private int z(TreeNode root) {
        if(root == null) return 0;

        int leftHeight = z(root.left);
        int rightHeight = z(root.right);

        // Diameter through this root = left height + right height
        int diameterThroughRoot = leftHeight + rightHeight;
        // Update maxDiameter 
        maxDiameter = Math.max(maxDiameter, diameterThroughRoot);

        return 1 + Math.max(leftHeight, rightHeight);
    }


    public int diameterOfBinaryTree(TreeNode root) {
        return f(root);       // Method 1 — O(N²)
        // return g(root).diameter; // Method 2 — O(N)

        maxDiameter = 0;
        z(root);
        return maxDiameter;                 // Method 3 — O(N)
    }
}