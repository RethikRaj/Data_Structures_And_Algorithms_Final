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
class Solution {
    // MethodOne : 
    public boolean isIdentical(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null && q != null) return false;
        if(p != null && q == null) return false;

        return p.val == q.val && (isIdentical(p.left, q.left) && isIdentical(p.right, q.right));
    }

     // TC : O(M * N), SC : O(M + N), M = no of nodes in root , N = no.of.nodes in subroot
    public boolean searchSubTree(TreeNode root, TreeNode subRoot) {
        if(root == null) return false;

        if(root.val == subRoot.val) {
            // check identical 
            boolean _isIdentical = isIdentical(root, subRoot);
            if(_isIdentical) return true;
            // else continue checking , because tree might contain duplicate values
        }

        return searchSubTree(root.left, subRoot) || searchSubTree(root.right, subRoot);
    }

    // MethodTwo : Serialization
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#null");
            return;
        }

        sb.append("#").append(root.val);
        serialize(root.left, sb);
        serialize(root.right, sb);  
    }

    // TC : O(M + N + M*N)
    private boolean methodTwo(TreeNode root, TreeNode subRoot) {
        StringBuilder rootSb = new StringBuilder();
        serialize(root, rootSb);
        StringBuilder subRootSb = new StringBuilder();
        serialize(subRoot, subRootSb);

        return rootSb.toString().contains(subRootSb.toString());
        // Should use KMP to get O(M + N + (M+N));
    }


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // return searchSubTree(root, subRoot);

        return methodTwo(root, subRoot);
    }
}