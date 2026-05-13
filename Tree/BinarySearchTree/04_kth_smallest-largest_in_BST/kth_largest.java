
class Solution {
    // Method 1 : inorder
    // Way 1 : 
        // 1. First calculate number of nodes 
        // 2. And then find (n-k)th node from start 
    // or
    // way 2 :
        // 1. Store inorder in array.
        // 2. return arr[n-k]
    
    
    
    // Method 2 : Reverse Inorder
    private int answer;
    private int reverseInorderCount;
    
    private void f(Node root, int k) {
        if(root == null || reverseInorderCount >= k) return; // early exit
        
        // R
        f(root.right, k); 
        // N
        reverseInorderCount += 1;
        if(reverseInorderCount == k) answer = root.data;
        // L
        f(root.left, k);
    }
    
    public int kthLargest(Node root, int k) {
        answer = Integer.MAX_VALUE;
        reverseInorderCount = 0;
        f(root, k);
        return answer;
    }
}