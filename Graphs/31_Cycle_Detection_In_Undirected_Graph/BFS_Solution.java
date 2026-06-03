class Pair {
    int value;
    int parent;
    
    Pair(int value, int parent) {
        this.value = value;
        this.parent = parent;
    }
}

class Solution {
    private boolean hasCycleBFS(int src, List<List<Integer>> adjList, Set<Integer> visited) {
        Queue<Pair> q = new ArrayDeque<>();
        
        q.offer(new Pair(src, -1));
        visited.add(src);
        
        while(!q.isEmpty()) {
            Pair front = q.poll();
            
            for(int nbr : adjList.get(front.value)) {
                if(!visited.contains(nbr)) {
                    q.offer(new Pair(nbr, front.value));
                    visited.add(nbr);
                }else {
                    // visited -> check if parent or not;
                    if(front.parent == nbr) {
                        continue;
                    }else {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean isCycle(int V, List<List<Integer>> adjList) {
        // We would have disconnected components
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < V; i++) {
            if(!visited.contains(i)) {
                boolean hasCycle = hasCycleBFS(i, adjList, visited);
                if(hasCycle == true) return true;
            }
        }
        return false;
    }
}