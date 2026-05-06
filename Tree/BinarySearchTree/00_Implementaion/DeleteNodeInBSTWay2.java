
class Solution {
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
                TreeNode parent = root;
                TreeNode temp = root.left;
                while(temp.right != null) {
                    parent = temp;
                    temp = temp.right;
                }

                if(parent == root) {
                    temp.right = root.right;
                    return temp;
                }else {
                    parent.right = temp.left;
                    temp.left = root.left;
                    temp.right = root.right;
                    return temp;
                }
            }
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        return searchAndDeleteNode(root, key);
    }
}