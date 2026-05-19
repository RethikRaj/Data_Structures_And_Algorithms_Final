class BSTIterator {
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        TreeNode temp = root;
        while(temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
    }
    
    public int next() {
        TreeNode top = stack.pop();
        
        TreeNode temp = top.right;
        while(temp != null) {
            stack.push(temp);
            temp = temp.left;
        }

        return top.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
