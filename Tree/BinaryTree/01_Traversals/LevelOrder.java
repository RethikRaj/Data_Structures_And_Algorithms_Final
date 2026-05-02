import java.util.ArrayList;
import java.util.Queue;
import java.util.List;

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
    // Using DFS
    public void  levelOrderUsingDFS(TreeNode root, int depth, List<List<Integer>> ans) {
        if(root == null) return;

        if(depth == ans.size()) {
            // First time reaching this level, create a new list
            ans.add(new ArrayList<>(List.of(root.val)));
        }else {
            // depth < ans.size() . Note depth > ans.size() is not possible
            ans.get(depth).add(root.val);
        }
        // Recurse left first to maintain left-to-right order
        levelOrderUsingDFS(root.left , depth + 1, ans);
        levelOrderUsingDFS(root.right, depth + 1, ans);
    }

    // Using BFS ( best )
    // TC: O(n) - each node is enqueued and dequeued exactly once
    // SC: O(n) - deque holds at most O(w) nodes where w is max width (≤ n/2 for last level)
    public List<List<Integer>> levelOrderUsingBFS(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) return ans;

        // BFS queue seeded with root
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();          // nodes at current level
            List<Integer> level = new ArrayList<>();

            // Process elements of one level of the tree.
            for (int i = 0; i < levelSize; i++) {
                TreeNode front = q.poll();
                level.add(front.val);

                // !enqueue children for next level only if not null.
                if (front.left  != null) q.offer(front.left);
                if (front.right != null) q.offer(front.right);
            }

            ans.add(level);
        }

        return ans;
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        return levelOrderUsingBFS(root);


        // DFS ;
        // List<List<Integer>> ans = new ArrayList<>();
        // levelOrderUsingDFS(root, 0, ans);
        // return ans;
    }
}