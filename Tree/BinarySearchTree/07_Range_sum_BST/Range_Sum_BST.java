class Solution {
    // Brute force : any order we can use.
    // TC: O(N) - visits every node regardless of BST property
    // SC: O(H) - recursion stack, H = height (O(logN) balanced, O(N) skewed)
    private int brute(TreeNode root, int low, int high) {
        if(root == null) return 0;

        int sum = 0;
        if(root.val >= low && root.val <= high) sum += root.val;
        sum += brute(root.left, low, high);
        sum += brute(root.right, low, high);

        return sum;
    }

    // Optimal DFS :
    // TC: O(N) worst case, but prunes irrelevant subtrees using BST property → faster in practice
    // SC: O(H) - recursion stack, H = height (O(logN) balanced, O(N) skewed)
    private int f(TreeNode root, int low, int high) {
        if(root == null) return 0;

        // Current node is below range, only right subtree can have in-range values
        if(root.val < low) {
            return f(root.right, low, high);
        }
        // Current node is above range, only left subtree can have in-range values
        if(root.val > high) {
            return f(root.left, low, high);
        } 

        // Current node is in range, include it and check both subtrees
        return root.val + f(root.left, low, high) + f(root.right, low, high);
        
    }

    // optimal BFS :
    // TC: O(N) worst case, but prunes irrelevant subtrees using BST property → faster in practice
    // SC: O(W) - queue holds at most one level, W = max width (O(N) worst case for complete tree)
    private int levelOrder(TreeNode root, int low, int high) {
        if (root == null) return 0;

        int sum = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for(int i = 0; i < levelSize; i++) {
                TreeNode front = queue.poll();

                if(front.val >= low && front.val <= high) sum += front.val;

                if(front.val > low && front.left != null) queue.offer(front.left);
                if(front.val < high && front.right != null) queue.offer(front.right); 
            }
        }

        return sum;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        // return brute(root, low, high);

        // return f(root, low, high);

        return levelOrder(root, low, high);
    }
}