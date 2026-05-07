

class Solution {
    ArrayList<Integer> getLeftBoundary(Node root) {
        ArrayList<Integer> leftBoundary = new ArrayList<>();
        if(root.left == null) {
            return leftBoundary;
        }
        
        Node curr = root.left;
        while( !(curr.left == null && curr.right == null)) {
            leftBoundary.add(curr.data);
            
            if(curr.left != null) {
                curr = curr.left;
            }else if(curr.right != null) {
                curr = curr.right;
            }
        }
        return leftBoundary;
    }
    
    void getLeafBoundary(Node root, ArrayList<Integer> ans) {
        if(root == null) return;
        
        if(root.left == null && root.right == null) ans.add(root.data);
        getLeafBoundary(root.left, ans);
        getLeafBoundary(root.right, ans);
    }
    
    ArrayList<Integer> getRightBoundary(Node root) {
        ArrayList<Integer> rightBoundary = new ArrayList<>();
        if(root.right == null) {
            return rightBoundary;
        }
        
        Node curr = root.right;
        while( !(curr.left == null && curr.right == null)) {
            rightBoundary.add(curr.data);
            
            if(curr.right != null) {
                curr = curr.right;
            }else if(curr.left != null) {
                curr = curr.left;
            }
        }
        return rightBoundary;
    }
    
    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        ans.add(root.data);

        ans.addAll(getLeftBoundary(root));
        
        // Why two separate calls ? Think about test case = [1]
        getLeafBoundary(root.left, ans);
        getLeafBoundary(root.right, ans);

        ArrayList<Integer> rightBoundary = getRightBoundary(root);
        Collections.reverse(rightBoundary);
        ans.addAll(rightBoundary);
        return ans;    
    }
}