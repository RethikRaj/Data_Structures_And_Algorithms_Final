class Solution {
    public TreeNode getInorderSuccessor(TreeNode root) {
        root = root.right;
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }

    public TreeNode searchAndDeleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.val < key) {
            root.right = searchAndDeleteNode(root.right, key);
            return root;
        }else if(root.val > key) {
            root.left = searchAndDeleteNode(root.left, key);
            return root;
        }else {
            
            if(root.left == null && root.right == null) {
                return null;
            } else if(root.left != null && root.right == null) {
                return root.left;
            } else if(root.left == null && root.right != null) {
                return root.right;
            } else {
                TreeNode inorderSuccessor = getInorderSuccessor(root);
                int temp = root.val;
                root.val = inorderSuccessor.val;
                inorderSuccessor.val = temp;
                root.right = searchAndDeleteNode(root.right, key);
                return root;
            }
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        return searchAndDeleteNode(root, key);
    }
}