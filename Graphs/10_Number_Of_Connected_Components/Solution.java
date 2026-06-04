class Solution {
    // Method 1 :
    private void dfs(List<List<Integer>> adjList, int curr, Set<Integer> visited) {
        visited.add(curr);

        for(int nbr : adjList.get(curr)) {
            if(!visited.contains(nbr)) {
                dfs(adjList, nbr, visited);
            }
        }
    }

    // Method 2 :
    private void bfs(List<List<Integer>> adjList, int src, Set<Integer> visited) {
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(src);
        visited.add(src);

        while(!q.isEmpty()) {
            int front = q.poll();

            for(int nbr : adjList.get(front)) {
                if(!visited.contains(nbr)) {
                    q.offer(nbr);
                    visited.add(nbr);
                }
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        // Step 1 : Create adjacency list from edgesList
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < n ; i++) adjList.add(new ArrayList<>());

        for(int[] edge : edges) {
            int src = edge[0]; 
            int dest = edge[1];

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

        // Step 2 : Apply bfs or dfs
        int count = 0;

        Set<Integer> visited = new HashSet<>();

        for(int i = 0; i < n ; i++) {
            if(!visited.contains(i)) {
                // dfs(adjList, i, visited);

                bfs(adjList, i, visited);
                count += 1;
            }
        }

        return count;
    }
}
