class Solution {
    private int preorderIndex;

    private TreeNode buildBST(int[] preorder, int lowerRange, int upperRange) {
        if(preorderIndex == preorder.length) return null;

        int element = preorder[preorderIndex];
        if(element < lowerRange || element > upperRange) return null;

        // Now , preorder[index] is in range : [lowerRange, upperRange]
        TreeNode root = new TreeNode(element);
        preorderIndex += 1;

        root.left = buildBST(preorder, lowerRange, element);
        root.right = buildBST(preorder, element, upperRange);
        return root;
    } 

    public TreeNode bstFromPreorder(int[] preorder) {
        preorderIndex = 0;
        return buildBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}