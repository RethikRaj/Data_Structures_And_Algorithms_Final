class Info {
    TreeNode node;
    TreeNode parent;
    
    Info(TreeNode node, TreeNode parent) {
        this.node = node;
        this.parent = parent;
    }
}

class Solution {
    // Method One : (Best)
    // TC: O(N) - visits every node once via BFS
    // SC: O(W) - queue holds at most one full level (W = max width of tree)
    public boolean methodOne(TreeNode root, int x, int y) {
        // BFS queue storing each node alongside its parent
        Queue<Info> queue = new ArrayDeque<>();
        queue.offer(new Info(root, null));

        Info targetX = null;
        Info targetY = null;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Process all nodes at the current depth level
            for (int i = 0; i < levelSize; i++) {
                Info front = queue.poll();

                if (front.node.val == x) targetX = front;
                if (front.node.val == y) targetY = front;

                if (front.node.left != null) queue.offer(new Info(front.node.left, front.node));
                if (front.node.right != null) queue.offer(new Info(front.node.right, front.node));
            }

            // Check whether we found x or y or both and then check parents
            if(targetX != null && targetY != null) {
                // both found -> check parent
                return targetX.parent != targetY.parent;
            } else if(targetX == null && targetY == null) {
                // none found 
                continue;
            } else {
                // either x or y found -> they do not belong to same level
                return false;
            }
        }

        return false;
    }

    // Method Two :
    // TC: O(N) - isSameLevel is O(N) BFS + hasSameParent is O(N) DFS -> O(N) overall
    // SC: O(N) - O(W) for BFS queue + O(H) for DFS recursion stack (W=width, H=height); O(N) worst case
    public boolean areSameLevel(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int levelOfX = -1;
        int levelOfY = -1;

        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for(int i = 0; i < levelSize; i++) {
                TreeNode front = queue.poll();

                if(front.val == x) levelOfX = level;
                if(front.val == y) levelOfY = level;

                if(front.left != null) queue.offer(front.left);
                if(front.right != null) queue.offer(front.right);
            }

            level += 1;

            // Check whether x or y or both found
            if(levelOfX != -1 && levelOfY != -1) {
                return true;
            } else if(levelOfX == -1 && levelOfY == -1) {
                continue; 
            } else {
                return false;
            }
        }

        return false;
    }

    public boolean hasSameParent(TreeNode root, int x, int y) {
        if(root == null) return false;

        // Check if curr node is parent of x and y .
        if(root.left != null && root.right != null) {
            if(root.left.val == x && root.right.val == y) return true;
            if(root.left.val == y && root.right.val == x) return true;
        }

        return hasSameParent(root.left, x, y) || hasSameParent(root.right, x, y);
    }

    public boolean methodTwo(TreeNode root, int x, int y) {
        return areSameLevel(root, x, y) && !hasSameParent(root, x, y);
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        // return methodOne(root, x, y);

        return methodTwo(root, x, y);
    }
}