import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.swing.tree.TreeNode;


class Solution {
    private List<Integer> ans;

    // way 1 : Keep ans as global
    public void f(TreeNode root) {
        if(root == null) return;

        ans.add(root.val);
        f(root.left);
        f(root.right);
    }

    // way 2 : Pass result(ans) as parameter
    public void f(TreeNode root, List<Integer> result) {
        if(root == null) return;

        result.add(root.val);
        f(root.left, result);
        f(root.right, result);
    }

    // way 3 : Iterative 
    public List<Integer> iterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);

            // Push right first then left => Because in preorder left needs to be processed first
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }

        return res;
    }



    public List<Integer> preorderTraversal(TreeNode root) {
        // Way 1 : Global
        // ans = new ArrayList<>();
        // f(root);
        // return ans;

        // Way 2 
        // List<Integer> result = new ArrayList<>();
        // f(root, result);
        // return result;

        // Way 3 :
        return iterative(root);
    }
}
