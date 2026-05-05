

public class MorrisTraversals {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        TreeNode curr = root;

        while(curr != null) {
            // Check if left subtree exists or not ?
            if(curr.left == null ) {
                // left subtree does not exist
                ans.add(curr.val);
                curr = curr.right;
            } else {
                // Check whether left part already traversed or not
                // No link exists -> not  traversed(1st time visit) -> link the rightmost node in left sub tree to curr and move to curr node's left sub tree.
                // link exists -> already traversed(2nd time visit) - remove link , process curr node, move to curr node's right sub tree;

                TreeNode temp = curr.left;
                while(temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }

                if(temp.right == curr) {
                    // link exists
                    temp.right = null; // remove link
                    ans.add(curr.val); // process curr node
                    curr = curr.right;
                } else if(temp.right == null) {
                    // link does not exist
                    temp.right = curr;
                    curr = curr.left;
                }
            } 
        }

        return ans;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        TreeNode curr = root;

        while(curr != null) {
            // Check if left subtree exists or not ?
            if(curr.left == null) { // left subtree does not exist
                ans.add(curr.val);
                curr = curr.right;
            }else {
                // Check whether left part already traversed or not
                // No link exists -> not  traversed(1st time visit) -> process curr node, link the rightmost node in left sub tree to curr and move to curr node's left sub tree.
                // link exists -> already traversed(2nd time visit) - remove link, move to curr node's right sub tree;

                TreeNode temp = curr.left;
                while(temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }

                if(temp.right == curr) { // link exists
                    temp.right = null; // remove link
                    curr = curr.right;
                }else if(temp.right == null) { // link does not exist
                    ans.add(curr.val);
                    temp.right = curr; // create link
                    curr = curr.left;
                }
            }
        }
        return ans;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        TreeNode curr = root;

        while(curr != null) {
            // Check if right subtree exist or not ?
            if(curr.right == null) {
                ans.add(curr.val); // Procees curr
                curr = curr.left; // Move to left subtree
            }else {
                // Check whether right part already traversed or not
                // No link exists -> not  traversed(1st time vist) -> Process curr node, link the leftmost node in right sub tree to curr and move to curr node's right sub tree.
                // link exists -> already traversed(2nd time visit) -> remove link , move to curr node's left sub tree;

                TreeNode temp = curr.right;
                while(temp.left != null && temp.left != curr) {
                    temp = temp.left;
                }

                if(temp.left == curr) {
                    temp.left = null; // Remove link
                    curr = curr.left; // Move to left sub tree.
                }else if(temp.left == null) {
                    ans.add(curr.val); // Process curr node
                    temp.left = curr; // Create link
                    curr = curr.right; // Move to right subtree
                }
            }
        }

        Collections.reverse(ans);
        return ans;
    }

}