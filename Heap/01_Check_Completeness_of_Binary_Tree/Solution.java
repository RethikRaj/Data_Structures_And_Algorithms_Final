class Solution {
    // Approach 1: If we ever see a null child before a non-null child, tree is incomplete
    private boolean solutionOne(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        boolean isNullFound = false;

        while(!q.isEmpty()) {
            int levelSize = q.size();

            for(int i = 0; i < levelSize; i++) {
                TreeNode front = q.poll();

                if(front.left == null) {
                    isNullFound = true;
                } else {
                    if(isNullFound) return false; // non-null after a null → incomplete
                    else q.offer(front.left);
                }

                if(front.right == null) {
                    isNullFound = true;
                } else {
                    if(isNullFound) return false; // non-null after a null → incomplete
                    else q.offer(front.right);
                }
            }
        }

        return true;
    }

    // Approach 2: In a complete binary tree, BFS indices must be 0,1,2,3... with no gaps
    // left child index = 2*i+1, right child index = 2*i+2
    static class Pair {
        TreeNode node;
        int levelIndex;

        Pair(TreeNode node, int levelIndex) {
            this.node = node;
            this.levelIndex = levelIndex;
        }
    }

    private boolean solutionTwo(TreeNode root) {
        int expectedIndex = 0;

        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0));

        while(!q.isEmpty()) {
            int levelSize = q.size();

            for(int i = 0; i < levelSize; i++) {
                Pair front = q.poll();

                if(front.levelIndex != expectedIndex) return false; // gap found
                expectedIndex += 1;

                if(front.node.left != null) q.offer(new Pair(front.node.left, 2*front.levelIndex + 1));
                if(front.node.right != null) q.offer(new Pair(front.node.right, 2*front.levelIndex + 2));
            }
        }

        return true;
    }

    public boolean isCompleteTree(TreeNode root) {
        return solutionOne(root);
        // return solutionTwo(root);
    }
}