class Solution {
    private int[] multiSourceBFS(ArrayList<ArrayList<Integer>> graph, int[] indegreeMap) {
        int V = indegreeMap.length;

        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        int[] result = new int[V];
        int idx = 0;

        for(int i = 0; i < indegreeMap.length; i++) {
            if(indegreeMap[i] == 0) {
                q.offer(i);
                visited.add(i);
            }
        }

        while(!q.isEmpty()) {
            int front = q.poll();

            result[idx++] = front;

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

        // Check for cycle.
        return visited.size() == V ? result : new int[0];
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int V = numCourses;

        int[] indegreeMap = new int[V];

        // Constructing a directed graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < V; i++) graph.add(new ArrayList<>());
        for(int[] edge : prerequisites) {
            int src = edge[1]; // bi
            int dest = edge[0]; // ai
            indegreeMap[dest] += 1;
            
            graph.get(src).add(dest);
        }

        int[] result = multiSourceBFS(graph, indegreeMap);

        return result;
    }
}