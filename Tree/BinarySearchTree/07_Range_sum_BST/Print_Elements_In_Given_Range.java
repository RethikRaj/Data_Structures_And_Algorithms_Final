class Solution {
    
    private static void f(Node root, int low, int high, ArrayList<Integer> ans) {
        if(root == null) return;
        
        if(root.data < low) {
            // If root.data < low , then the elements in range[low, high] will be present only in the right sub tree.
            f(root.right, low, high, ans);
        }else if(root.data > high) {
            // If root.data > high , then the elements in range[low, high] will be present only in the left sub tree.
            f(root.left, low, high, ans);
        }else {
            f(root.left, low, high, ans);
            ans.add(root.data);  // since needed in sorted order insert after exploring left sub tree.
            f(root.right, low, high, ans);
        }
    }
    
    public static ArrayList<Integer> printNearNodes(Node root, int low, int high) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        f(root, low, high, ans);
        return ans;
    }
}