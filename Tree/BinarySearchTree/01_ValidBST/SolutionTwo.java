

class Solution {
    private long prev;
    
    private boolean checkSortedInorder(TreeNode root) {
        if(root == null) return true;

        // Left sub tree
        boolean isLeftSubTreeInorderSorted = checkSortedInorder(root.left);
        if(isLeftSubTreeInorderSorted == false) return false;
        // current node
        if(prev >= root.val) return false;
        prev = root.val;
        // right sub tree
        return checkSortedInorder(root.right);
    }

    public boolean isValidBST(TreeNode root) {

        prev = Long.MIN_VALUE;
        return checkSortedInorder(root);
    }
}