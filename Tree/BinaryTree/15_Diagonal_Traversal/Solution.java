
class Solution {
    public ArrayList<Integer> diagonal(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        // Queue holds the "starting node" of each diagonal
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        
        while(!q.isEmpty()) {
            Node front = q.poll();
            
            // Traverse the entire diagonal by following right pointers
            while(front != null) {
                ans.add(front.data);
                // Left child starts a NEW diagonal — schedule it
                if(front.left != null) q.offer(front.left);
                front = front.right; // stay on the same diagonal
            }
        }
        
        return ans;
    }
}