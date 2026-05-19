

class Solution {
    // Method 1 - DFS - Recursion : TC : O(n), SC : O(height)
    private void inorderRecursion(TreeNode root, List<Integer> ans) {
        if(root == null) return;

        inorderRecursion(root.left, ans);
        ans.add(root.val);
        inorderRecursion(root.right, ans);
    }

    // Method 2 - Using Stack : TC : O(n) , SC : O(height)
    static class Pair {
        TreeNode node;
        int numberOfTimesVisited;

        Pair(TreeNode node, int numberOfTimesVisited) {
            this.node = node;
            this.numberOfTimesVisited = numberOfTimesVisited;
        }
    }

    private List<Integer> inorderStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        ArrayDeque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(root, 1));

        while(!stack.isEmpty()) {
            Pair top = stack.pop();

            if(top.numberOfTimesVisited == 2) {
                ans.add(top.node.val);
            }else {
                if(top.node.right != null) stack.push(new Pair(top.node.right, 1));
                stack.push(new Pair(top.node, 2));
                if(top.node.left != null) stack.push(new Pair(top.node.left, 1));
            }
        }

        return ans;
    }

    // Method 3 - Using stack but differently (helpful in find common nodes in two bst, merge two bsts) - TC : O(n), SC:O(height)
    // Generally this method is used when you don't know need whole inorder upfront instead we need the next element on demand - that is - "give me your next element".
    // One stack.pop() + right-subtree push = "give me next element"
    private List<Integer> inorderStackVersionTwo(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        // Push all leftmost nodes in stack
        TreeNode temp = root;
        while(temp != null) {
            stack.push(temp);
            temp = temp.left;
        }

        // 
        while(!stack.isEmpty()) {
            TreeNode top = stack.pop();
            ans.add(top.val);

            // Push all leftmost node of right sub tree
            TreeNode ptr = top.right; 
            while(ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            }
        }
        return ans;
    }

    // Method 4 - Using Morris Traversal : TC: O(n), SC : O(1)
    private List<Integer> inorderMorris(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        TreeNode curr = root;

        while(curr != null) {
            // Check if left subtree exists or not ?
            if(curr.left == null) { 
                // left subtree does not exist
                ans.add(curr.val); 
                curr = curr.right;
            }else {
                // Left subtree exists .
                // Now, check whether left part already traversed or not
                // No link exists -> not  traversed(1st time visit) -> link the rightmost node in left sub tree(inorder predecessor) to curr and move to curr node's left sub tree.
                // link exists -> already traversed(2nd time visit) - remove link , process curr node, move to curr node's right sub tree;
                TreeNode pred = curr.left;
                while(pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if(pred.right == null) { // link does not exists
                    pred.right = curr; // create link
                    curr = curr.left; // Move to left sub tree
                }else { // link exists
                    pred.right = null;  // remove link
                    ans.add(curr.val); // process curr node
                    curr = curr.right; // move to right sub tree
                }
            }
        }

        return ans;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        // method-one
        // List<Integer> ans = new ArrayList<>();
        // inorderRecursion(root, ans);
        // return ans;

        // method-two
        // return inorderStack(root);

        // method-three
        // return inorderStackVersionTwo(root);

        // method-four
        // return inorderMorris(root);
    }
}