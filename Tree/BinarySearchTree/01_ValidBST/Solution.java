

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

class Info {
    int maxValue;
    int minValue;
    boolean isBST;
    Info(int maxValue, int minValue, boolean isBST) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.isBST = isBST;
    }
}

class LongInfo {
    long minValue;
    long maxValue;
    boolean isBST;
    LongInfo(long maxValue, long minValue, boolean isBST) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.isBST = isBST;
    }
}

class Solution {
    public Info f(TreeNode root) {
        if(root.left == null && root.right == null) return new Info(root.val, root.val, true);
        
        if(root.left != null && root.right == null) {
            Info left = f(root.left);
            int maxValue = Math.max(root.val, left.maxValue);
            int minValue = Math.min(root.val, left.minValue);
            boolean isBST = left.isBST && left.maxValue < root.val;
            return new Info(maxValue, minValue, isBST);
        } else if(root.left == null && root.right != null) {
            Info right = f(root.right);
            int maxValue = Math.max(root.val, right.maxValue);
            int minValue = Math.min(root.val, right.minValue);
            boolean isBST = right.isBST && right.minValue > root.val;
            return new Info(maxValue, minValue, isBST);
        } else {
            Info left = f(root.left);
            Info right = f(root.right);
            int maxValue = Math.max(root.val, Math.max(left.maxValue, right.maxValue));
            int minValue = Math.min(root.val, Math.min(left.minValue, right.minValue));
            boolean isBST = left.isBST && left.maxValue < root.val && right.isBST && right.minValue > root.val;
            return new Info(maxValue, minValue, isBST);
        }
    }

    public LongInfo f2(TreeNode root) {
        if(root == null) return new LongInfo(Long.MIN_VALUE, Long.MAX_VALUE, true);

        LongInfo left = f2(root.left);
        LongInfo right = f2(root.right);
        
        long maxValue = Math.max(left.maxValue, Math.max(right.maxValue, root.val));
        long minValue = Math.min(left.minValue, Math.min(right.minValue, root.val));
        boolean isBST = left.isBST && right.isBST && left.maxValue < root.val && right.minValue > root.val;
        return new LongInfo(maxValue, minValue, isBST);
    }

    public boolean isValidBST(TreeNode root) {
        // return f(root).isBST;

        return f2(root).isBST;
    }
}