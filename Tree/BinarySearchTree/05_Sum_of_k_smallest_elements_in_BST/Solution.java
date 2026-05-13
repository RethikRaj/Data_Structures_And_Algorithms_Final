class Solution {
    private int answer;
    private int inorderCount;
    
    private void f(Node root, int k) {
        if(root == null || inorderCount >= k) return; // early exit
        
        // L
        f(root.left, k);
        
        // N
        inorderCount += 1;
        if(inorderCount <= k) answer += root.data;
        
        // R
        f(root.right, k);
    }
    
    int sum(Node root, int k) {
        answer = 0;
        inorderCount = 0;
        f(root, k);
        return answer;
        
    }
}