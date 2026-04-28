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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        
        if(root == null) return ans;

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();

            int maxValue = Integer.MIN_VALUE;
            for(int i = 0; i < size ;i++) {
                TreeNode front = q.poll();
                maxValue = Math.max(maxValue, front.val);

                if(front.left != null) q.offer(front.left);
                if(front.right != null) q.offer(front.right);
            }

            ans.add(maxValue);
        }

        return ans;
    }
}