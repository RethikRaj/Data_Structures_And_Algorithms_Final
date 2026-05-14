class Solution {
    private int index;
    // Preorder
    private TreeNode buildTreeUtil(int[] preorder, int start, int end, Map<Integer, Integer> inorderMap) {
        if(index == preorder.length) return null;
        if(start > end) return null;

        // N
        int element = preorder[index];
        TreeNode root = new TreeNode(element);
        int inorderIndex = inorderMap.get(element);
        index += 1;
        // L
        root.left = buildTreeUtil(preorder, start, inorderIndex - 1, inorderMap);
        // R
        root.right = buildTreeUtil(preorder, inorderIndex + 1, end, inorderMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        index = 0;
        return buildTreeUtil(preorder, 0, inorder.length - 1, inorderMap);
    }
}