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
class BSTIterator {
    private List<Integer> inorder;
    private int index;

    private void getInorder(TreeNode root, List<Integer> inorder) {
        if(root == null) return;

        getInorder(root.left, inorder);
        inorder.add(root.val);
        getInorder(root.right, inorder);
    }

    public BSTIterator(TreeNode root) {
        inorder = new ArrayList<>();

        getInorder(root, inorder);
        index = 0;
    } 
    
    public int next() {
        int value = inorder.get(index);
        index++;

        return value;
    }
    
    public boolean hasNext() {
        return index != inorder.size();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */