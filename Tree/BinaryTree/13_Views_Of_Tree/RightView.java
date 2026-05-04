class Solution {

    // MethodOne : TC: O(N) | SC: O(N) - queue holds at most one full level
    public List<Integer> rightViewBFS(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            // First node in each level (right-first order)
            ans.add(q.peek().val);

            for (int i = 0; i < levelSize; i++) {
                TreeNode front = q.poll();

                // !Enqueue right before left so rightmost node is at front
                if (front.right != null) q.offer(front.right);
                if (front.left != null) q.offer(front.left);
            }
        }

        return ans;
    }

    // MethodTwp : TC: O(N) | SC: O(H) - recursive stack, H = height of tree
    public void rightViewDFS(TreeNode root, int level, List<Integer> ans) {
        if (root == null) return;

        // First time reaching this level (right-first) -> rightmost node
        if (ans.size() == level) ans.add(root.val);

        // !Traverse right before left
        rightViewDFS(root.right, level + 1, ans);
        rightViewDFS(root.left, level + 1, ans);
    }

    public List<Integer> rightSideView(TreeNode root) {
        // return rightViewBFS(root);

        List<Integer> ans = new ArrayList<>();
        rightViewDFS(root, 0, ans);
        return ans;
    }
}