class Solution {
    private int preorderIndex;

    private TreeNode buildTreeUtil(int[] preorder, int inorderStart, int inorderEnd, Map<Integer, Integer> inorderMap) {
        // Base cases
        if (preorderIndex == preorder.length) return null;
        if (inorderStart > inorderEnd) return null;

        
        int element = preorder[preorderIndex];
        TreeNode root = new TreeNode(element);
        preorderIndex++;
        // Find where this element sits in the inorder traversal
        int inorderIndex = inorderMap.get(element);

        // Elements to the left of inorderIndex in inorder belong to the left subtree
        root.left = buildTreeUtil(preorder, inorderStart, inorderIndex - 1, inorderMap);
        // Elements to the right of inorderIndex in inorder belong to the right subtree
        root.right = buildTreeUtil(preorder, inorderIndex + 1, inorderEnd, inorderMap);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map each value to its index in inorder for O(1) lookups
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        preorderIndex = 0;
        return buildTreeUtil(preorder, 0, inorder.length - 1, inorderMap);
    }
}