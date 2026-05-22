class Solution {
    private static void getInorder(Node root, List<Integer> inorder) {
        if(root == null) return;
        
        getInorder(root.left, inorder);
        inorder.add(root.data);
        getInorder(root.right, inorder);
    }
    
    private static int inorderIndex;
    private static void fillInPostOrder(Node root, List<Integer> inorder) {
        if(root == null) return;
        
        fillInPostOrder(root.left, inorder);
        fillInPostOrder(root.right, inorder);
        root.data = inorder.get(inorderIndex);
        inorderIndex++;
    }
    
    public static void convertToMaxHeapUtil(Node root) {
        List<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);
        inorderIndex = 0;
        fillInPostOrder(root, inorder);
    }
}