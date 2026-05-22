

class Pair {
    Node node;
    int levelIndex;
    
    Pair(Node node, int levelIndex) {
        this.node = node;
        this.levelIndex = levelIndex;
    }
}

class Solution {
    public boolean isHeap(Node tree) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(tree, 0));
        
        int expectedIndex = 0;
        
        while(!q.isEmpty()) {
            Pair front = q.poll();
            
            // Checking complete binary tree property.
            if(front.levelIndex != expectedIndex) return false;
            
            expectedIndex += 1;
            
            if(front.node.left != null) {
                if(front.node.left.data > front.node.data) return false; // checking max-heap property
                else q.offer(new Pair(front.node.left, 2*front.levelIndex + 1));
            }
            
            if(front.node.right != null) {
                if(front.node.right.data > front.node.data) return false; // checking max-heap property
                else q.offer(new Pair(front.node.right, 2*front.levelIndex + 2));
            }
        }
        
        return true;
    }
}