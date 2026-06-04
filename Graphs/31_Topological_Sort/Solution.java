import java.util.* ;
import java.io.*; 
public class Solution {
    private static ArrayList<Integer> multiSourceBFS(ArrayList<ArrayList<Integer>> graph, int[] indegreeMap) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < indegreeMap.length; i++) {
            if(indegreeMap[i] == 0) {
                q.offer(i);
                visited.add(i);
            }
        }

        while(!q.isEmpty()) {
            int front = q.poll();

            result.add(front);

            // Adjacent
            for(int nbr : graph.get(front)) {
                if(!visited.contains(nbr)) {
                    indegreeMap[nbr] -= 1;

                    if(indegreeMap[nbr] == 0){
                        q.offer(nbr);
                        visited.add(nbr);
                    }
                }
            }
        }

        return result;
    }

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) {
        int[] indegreeMap = new int[v];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < v; i++) graph.add(new ArrayList<>());

        for(ArrayList<Integer> edge : edges) {
            int src = edge.get(0);
            int dest = edge.get(1);
            indegreeMap[dest] += 1;
            
            graph.get(src).add(dest);
        }

        // Apply mutlti souce bfs with nodes having indegree as 0;
        ArrayList<Integer> result = multiSourceBFS(graph, indegreeMap);
        return result;
    }
}
