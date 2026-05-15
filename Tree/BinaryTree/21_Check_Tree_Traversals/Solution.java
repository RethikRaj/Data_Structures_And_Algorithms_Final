class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    private static int index;
    // Preorder
    private static TreeNode buildTree(int[] preorder, int start, int end, Map<Integer, Integer> inorderMap) {
        if(index == preorder.length) return null;
        if(start > end) return null;

        // N
        int element = preorder[index];
        TreeNode root = new TreeNode(element);
        int inorderIndex = inorderMap.get(element);
        index += 1;
        // L
        root.left = buildTree(preorder, start, inorderIndex - 1, inorderMap);
        // R
        root.right = buildTree(preorder, inorderIndex + 1, end, inorderMap);
        return root;
    }
    
    private static int i;
    private static boolean isValid;
    private static void checkValidPostorder(TreeNode root, int[] postorder) {
        if(root == null || isValid == false) return;
        
        checkValidPostorder(root.left, postorder);
        checkValidPostorder(root.right, postorder);
        if(root.val != postorder[i]) {
            isValid = false;
        }
        i += 1;
    }
    
    static boolean checktree(int preorder[], int inorder[], int postorder[], int N) {
        // Step 1 : Build tree using preorder and inorder - we get a unique tree
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        index = 0;
        TreeNode root = buildTree(preorder, 0, inorder.length - 1, inorderMap);
        
        // Step 2 : Do postorder on the constructed tree and then check it with the given postorder.
        i = 0;
        isValid = true;
        checkValidPostorder(root, postorder);
        return isValid;
    }
}

/*
Other solutions :
    solution 2 : 
        - Build tree using postorder and inorder - we get a unique tree
        - Do preorder on the constructed tree and then check it with the given preorder.

    solution 3 : 
        - Build tree using preorder and inorder - we get a unique tree - (Tree1)
        - Build tree using postorder and inorder - we get a unique tree - (Tree2)
        - Check whether Tree1 and Tree2 are same/identical.

 */