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
    public void f(TreeNode root, List<Integer> ans) {
        if(root == null) return;

        f(root.left, ans);
        f(root.right, ans);
        ans.add(root.val);
    }

    public List<Integer> iterative(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if(root == null) return ans;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode top = stack.pop();

            ans.add(top.val);

            if(top.left != null) stack.push(top.left);
            if(top.right != null) stack.push(top.right);
        }

        Collections.reverse(ans);
        // Another way is to use LinkedList and insert at head(prepend)
        return ans;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        // List<Integer> ans = new ArrayList<>();
        // f(root, ans);
        // return ans;

        return iterative(root);
    }
}