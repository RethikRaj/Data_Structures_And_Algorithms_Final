class Solution {
    public Node connect(Node root) {
        if(root == null) return null;

        Queue<Node> q = new ArrayDeque<>();

        q.offer(root);

        while(!q.isEmpty()) {
            int levelSize = q.size();

            Node prev = q.poll();
            if(prev.left != null) q.offer(prev.left);
            if(prev.right != null) q.offer(prev.right);

            for(int i = 1; i < levelSize; i++) {
                Node curr = q.poll();

                prev.next = curr;
                prev = curr;

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            prev.next = null;
        }

        return root;
    }
}