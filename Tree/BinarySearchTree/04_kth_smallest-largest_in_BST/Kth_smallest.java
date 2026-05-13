
class Solution {
    private int answer;
    private int inorderCount;
    private void inorder(TreeNode root, int k) {
        if(root == null || inorderCount >= k) return;

        // L
        inorder(root.left, k); 
        // N
        inorderCount += 1;
        if(inorderCount == k) answer = root.val;
        
        // R
        inorder(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        answer = Integer.MIN_VALUE;
        inorderCount = 0;

        inorder(root, k);
        return answer;
    }
}