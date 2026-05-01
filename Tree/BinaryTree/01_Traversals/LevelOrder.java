import java.util.Queue;

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
    // TC: O(n) - each node is enqueued and dequeued exactly once
    // SC: O(n) - deque holds at most O(w) nodes where w is max width (≤ n/2 for last level)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) return ans;

        // BFS queue seeded with root
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();          // nodes at current level
            List<Integer> level = new ArrayList<>();

            // Process elements of one level of the tree.
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);

                // !enqueue children for next level only if not null.
                if (node.left  != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            ans.add(level);
        }

        return ans;
    }
}