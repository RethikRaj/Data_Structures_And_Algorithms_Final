class Solution {
    // returns all paths from curr to end.
    private void dfs(int[][] graph, int curr, int end, Set<Integer> visited, List<Integer> currPath, List<List<Integer>> allPaths) {
        if(curr == end) {
            currPath.add(curr);
            allPaths.add(new ArrayList<>(currPath));
            currPath.removeLast();
            return;
        }

        visited.add(curr);
        currPath.add(curr); 

        for(int nbr : graph[curr]) {
            if(!visited.contains(nbr)) {
                dfs(graph, nbr, end, visited, currPath, allPaths);
            }
        }

        currPath.removeLast();
        visited.remove(curr);
    }

    // bfs can be used but so tough and has higher TC.
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> allPaths = new ArrayList<>();
        int src = 0;
        int end = graph.length - 1;
        // Example : Find all paths from vertex 0 to vertex n - 1.
        dfs(graph, 0, end, new HashSet<>(), new ArrayList<>(), allPaths);
        return allPaths;
    }
}