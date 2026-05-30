

class Solution {
    // Method 1 : DFS
    private Node dfs(Node node, Map<Integer, Node> visited) {
        if(node == null) return null;

        Node clone = new Node(node.val);
        visited.put(node.val, clone);

        for(Node nbr : node.neighbors) {
            if(!visited.containsKey(nbr.val)) {
                Node cloneNbr = dfs(nbr, visited);
                clone.neighbors.add(cloneNbr);
            }else {
                clone.neighbors.add(visited.get(nbr.val)); // reuse already-cloned node
            }
        }

        return clone;
    }

    // Method 2 : BFS using two queues
    private Node bfs(Node node) {
        if(node == null) return null;

        Queue<Node> qOld = new ArrayDeque<>();
        Queue<Node> qNew = new ArrayDeque<>();

        Map<Integer, Node> visited = new HashMap<>();

        qOld.add(node);

        Node clone = new Node(node.val);
        qNew.add(clone);

        visited.put(node.val, clone);

        while(!qOld.isEmpty()) {
            Node fOld = qOld.poll();
            Node fNew = qNew.poll();

            for(Node nbr : fOld.neighbors) {
                if(!visited.containsKey(nbr.val)) {
                    qOld.offer(nbr);
                    
                    Node cloneNbr = new Node(nbr.val);
                    qNew.offer(cloneNbr);
                    fNew.neighbors.add(cloneNbr);

                    visited.put(nbr.val, cloneNbr);
                }else {
                    fNew.neighbors.add(visited.get(nbr.val)); // reuse already-cloned node
                }
            }
        }

        return clone;
    }

    // Method 3 : BFS using one queue
    private Node bfs_2(Node node) {
        if (node == null) return null;

        Queue<Node> q = new ArrayDeque<>();
        Map<Integer, Node> visited = new HashMap<>();

        q.offer(node);

        Node clone = new Node(node.val);
        visited.put(node.val, clone);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            Node currClone = visited.get(curr.val); // fetch its clone from map

            for (Node nbr : curr.neighbors) {
                if (!visited.containsKey(nbr.val)) {
                    q.offer(nbr);

                    Node nbrClone = new Node(nbr.val);
                    currClone.neighbors.add(nbrClone);

                    visited.put(nbr.val, nbrClone);
                }else {
                    currClone.neighbors.add(visited.get(nbr.val));
                }
                
            }
        }

        return clone;
    }

    public Node cloneGraph(Node node) {
        // return dfs(node, new HashMap<>());

        // return bfs(node);

        return bfs_2(node);
    }
}