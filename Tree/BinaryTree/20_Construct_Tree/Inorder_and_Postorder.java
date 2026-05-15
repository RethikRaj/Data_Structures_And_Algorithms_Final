class Solution {
    private int postorderIndex;

    // Build tree using reverse postorder traversal 
    private TreeNode buildTreeUtil(int[] postorder, int inorderStart, int inorderEnd, Map<Integer, Integer> inorderMap){
        // Base cases:
        if (postorderIndex == -1) return null;
        if (inorderStart > inorderEnd) return null;

        // N
        int element = postorder[postorderIndex];
        TreeNode root = new TreeNode(element);
        postorderIndex--;

        // Find where this root sits in the inorder traversal
        int inorderIndex = inorderMap.get(element);

        // ! first build right sub tree, then left sub tree -> Why ? do dry run
        // R - Elements to the right of inorderIndex in inorder belong to the right subtree
        root.right = buildTreeUtil(postorder, inorderIndex + 1, inorderEnd, inorderMap);
        // L - Elements to the left of inorderIndex in inorder belong to the left subtree
        root.left = buildTreeUtil(postorder, inorderStart, inorderIndex - 1, inorderMap);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Map each value to its index in inorder for O(1) lookups
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Start from the last element of postorder (the root of the full tree)
        postorderIndex = postorder.length - 1;
        return buildTreeUtil(postorder, 0, inorder.length - 1, inorderMap);
    }
}