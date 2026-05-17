
class Solution {
    private void morrisInorder(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;

        TreeNode first = null;
        TreeNode second = null;

        while(curr != null) {
            // left sub tree exists
            if(curr.left != null) {
                TreeNode temp = curr.left;
                while(temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }

                if(temp.right == null) { // Link doesn't exist
                    temp.right = curr;
                    curr = curr.left;
                }else { // link exists
                    temp.right = null; // 1. remove link

                    // 2. Process node
                    if(prev != null) {
                        if(prev.val > curr.val) {
                            if(first == null) {
                                first = prev;
                                second = curr;
                            }else {
                                second = curr;
                            }
                        }
                    }
                    // 3. move right 
                    prev = curr;
                    curr = curr.right;
                }
            }
            else { // left sub tree doesn't exist
                // 1. Process node and move right
                if(prev != null) {
                    if(prev.val > curr.val) {
                        if(first == null) {
                            first = prev;
                            second = curr;
                        }else {
                            second = curr;
                        }
                    }
                }
                // 2. move right 
                prev = curr;
                curr = curr.right;
            }
        }

        // Swap first and second
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void recoverTree(TreeNode root) {
        morrisInorder(root);
    }
}