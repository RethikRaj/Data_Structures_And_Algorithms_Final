class Solution {

    // MethodOne : TC: O(N) | SC: O(Width)~O(N) - queue holds at most one full level
    public ArrayList<Integer> leftViewBFS(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            // First node in each level is visible from the left
            ans.add(q.peek().data);

            for (int i = 0; i < levelSize; i++) {
                Node front = q.poll();
                if (front.left != null) q.offer(front.left);
                if (front.right != null) q.offer(front.right);
            }
        }
        return ans;
    }

    // MethodTwo : TC: O(N) | SC: O(H)~O(N) - recursive stack, H = height of tree
    public void leftViewDFS(Node root, int level, ArrayList<Integer> ans) {
        if (root == null) return;

        // First time reaching this level -> leftmost node
        if (ans.size() == level) ans.add(root.data);

        leftViewDFS(root.left, level + 1, ans);
        leftViewDFS(root.right, level + 1, ans);
    }

    public ArrayList<Integer> leftView(Node root) {
        // return leftViewBFS(root);

        ArrayList<Integer> ans = new ArrayList<>();
        leftViewDFS(root, 0, ans);
        return ans;
    }
}