class Solution {
    private static int postorderIndex;
    
    private static Node buildBST(int[] postorder, int lowerRange, int upperRange) {
        if(postorderIndex == -1) return null;
        
        int element = postorder[postorderIndex];
        if(element < lowerRange || element > upperRange) return null;
        
        Node root = new Node(element);
        postorderIndex -= 1;
        
        // ! first build right sub tree, then left sub tree -> Why ? we are doing reverse postorder ( or do dry run)
        root.right = buildBST(postorder, element, upperRange);
        root.left = buildBST(postorder, lowerRange, element);
        
        return root;
    }
    
    public static Node constructTree(int post[], int n) {
        postorderIndex = post.length - 1;
        return buildBST(post, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}