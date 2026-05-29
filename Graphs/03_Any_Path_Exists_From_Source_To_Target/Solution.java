class Solution {
    // Method 1 : DFS
    private boolean dfs(List<List<Integer>> graph, int curr, int dest, Set<Integer> visited) {
        if(curr == dest) return true;

        visited.add(curr);

        for(int nbr : graph.get(curr)){
            if(!visited.contains(nbr)) {
                boolean result = dfs(graph, nbr, dest, visited);
                if(result == true) return true;
            }
        }

        // visited.remove(curr); // No needed to backtrack . Although even if we backtrack we get correct answer but we are doing extra work which causes exponential TC.

        return false;
    }

    // Method 2 : bfs
    private boolean bfs(List<List<Integer>> graph, int src, int dest) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();

        q.add(src);
        visited.add(src);

        while(!q.isEmpty()) {
            int front = q.poll();

            if(front == dest) return true;

            for(int nbr : graph.get(front)) {
                if(!visited.contains(nbr)) {
                    q.add(nbr);
                    visited.add(nbr);
                }
            }
        }

        return false;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Step 1 : Construct adjacency list from edge list
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            graph.get(src).add(dest);

            graph.get(dest).add(src); // Since given in question it is bi-directional(undirected)
        }


        // Step 2 : Apply bfs or dfs
        // return dfs(graph, source, destination, new HashSet<>());

        return bfs(graph, source, destination);


    }
}