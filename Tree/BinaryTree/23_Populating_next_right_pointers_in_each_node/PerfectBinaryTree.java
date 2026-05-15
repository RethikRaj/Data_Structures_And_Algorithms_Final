

class Solution {
    private Node BFSbrute(Node root) {
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

    private Node BFSoptimal(Node root) {
        if(root == null) return null;

        root.next = null;
        Node head = root;

        while(head.left != null) {
            Node curr = head;
            while(curr.next != null) {
                curr.left.next = curr.right;
                curr.right.next = curr.next.left;
                curr = curr.next;
            }
            // last node
            curr.left.next = curr.right;
            curr.right.next = null;
            // update head
            head = head.left;
        }
        return root;
    }

    public Node connect(Node root) {
        // return BFSbrute(root);

        return BFSoptimal(root);
    }
}