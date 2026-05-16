/*
class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
}*/

class Solution {
    private boolean f(Node root, int low, int high) {
        if(root.left == null && root.right == null) {
            return high - low - 1 == 1;
        } 
        
        boolean hasDeadEndInLeft = root.left != null ? f(root.left, low, root.data) : false;
        boolean hasDeadEndInRight = root.right != null ? f(root.right, root.data, high) : false;
        
        return hasDeadEndInLeft || hasDeadEndInRight;
    }
    
    // Same as f function , just null case is handled at base case.
    private boolean g(Node root, int low, int high) {
        if(root == null) return false;
        if(root.left == null && root.right == null) {
            return high - low - 1 == 1;
        } 
        
        return g(root.left, low, root.data) || g(root.right, root.data, high);
    }
    
    
    public boolean isDeadEnd(Node root) {
        // return f(root, 0, Integer.MAX_VALUE);
        
        return g(root, 0, Integer.MAX_VALUE);
    }
}