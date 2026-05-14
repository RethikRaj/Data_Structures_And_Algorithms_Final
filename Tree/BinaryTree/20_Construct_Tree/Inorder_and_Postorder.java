class Solution {
    private int index;
    // Reverse postorder
    private TreeNode buildTreeUtil(int[] postorder, int start, int end, Map<Integer, Integer> inorderMap) {
        if(index == -1) return null;
        if(start > end) return null;

        // N
        int element = postorder[index];
        TreeNode root = new TreeNode(element);
        int inorderIndex = inorderMap.get(element);
        index -= 1;

        // R
        root.right = buildTreeUtil(postorder, inorderIndex + 1, end, inorderMap);
        // L
        root.left = buildTreeUtil(postorder, start, inorderIndex - 1, inorderMap);
        
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        index = postorder.length - 1;
        return buildTreeUtil(postorder, 0, inorder.length - 1, inorderMap);
    }
}