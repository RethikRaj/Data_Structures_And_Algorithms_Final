import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {

    // Method 1 : DFS
    // returns the number of nodes in the graph which is connected to `curr` vertex
    private int dfs(List<List<Integer>> graph, int curr, Set<Integer> visited) {
        visited.add(curr);

        int count = 1;

        for(int nbr : graph.get(curr)) {
            if(!visited.contains(nbr)) {
                count += dfs(graph, nbr, visited);
            }
        }

        // No backtracking needed : unlike "find all paths", this is a counting problem.
        // Once a node is visited and counted, revisiting it would increase the count.
        // Goal : every node must be counted exactly once.

        return count;
    }

    // Method 2 :
    private boolean bfs(List<List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();

        int src = 0;

        q.add(src);
        visited.add(src);

        int count = 0;

        while(!q.isEmpty()) {
            int front = q.poll();

            count += 1;

            for(int nbr : graph.get(front)) {
                if(!visited.contains(nbr)) {
                    q.add(nbr);
                    visited.add(nbr);
                }
            }
        }

        return count == graph.size();
    }

    // Assume graph is given as adjList
    public boolean isConnected(List<List<Integer>> graph) {
        // Method one :
        // int count = dfs(graph, 0, new HashSet<>());
        // return count == graph.size();

        // Method Two : 
        return bfs(graph);


        // *Note : Instead of count variable , we could also just check `visited.size() == graph.size()`;
    }
}
