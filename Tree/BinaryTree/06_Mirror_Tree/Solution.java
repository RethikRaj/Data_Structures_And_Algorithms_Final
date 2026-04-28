class Solution {
    void f(Node root) {
        if(root == null) return;
        
        f(root.left);
        f(root.right);
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    
    void mirror(Node root) {
        f(root);
    }
}