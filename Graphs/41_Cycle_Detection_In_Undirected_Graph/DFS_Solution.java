class Solution {
    private boolean hasCycleDFS(int curr, int parent, List<List<Integer>> adjList, Set<Integer> visited) {
        visited.add(curr);
        
        for(int nbr : adjList.get(curr)) {
            if(!visited.contains(nbr)) {
                boolean hasCycle = hasCycleDFS(nbr, curr, adjList, visited);
                if(hasCycle == true) return true;
            }else {
                // already visited so check if it is parent or not
                if(nbr == parent) {
                    continue;
                }else {
                    return true; // cycle detected;
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
                boolean hasCycle = hasCycleDFS(i, -1, adjList, visited);
                if(hasCycle == true) return true;
            }
        }
        return false;
    }
}