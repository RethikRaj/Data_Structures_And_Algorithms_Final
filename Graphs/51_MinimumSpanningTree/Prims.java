class Info {
    int vertex;
    int wt;
    int parent;
    
    Info(int vertex, int wt, int parent) {
        this.vertex = vertex;
        this.wt = wt;
        this.parent = parent;
    }
}

class Result {
    private static int primsAlgo(int V, List<Map<Integer, Integer>> graphAdjMap, int start) {
        int[] parent = new int[V + 1];
        boolean[] isMST = new boolean[V + 1];

        // Min-heap on edge weight
        PriorityQueue<Info> pq = new PriorityQueue<>((i1, i2) -> i1.wt - i2.wt);
        pq.offer(new Info(start, 0, -1)); // seed: start vertex, 0 cost, no parent
        
        int edgeCountInSpanningTree = 0;
        int minCost = 0;
        
        // MST needs exactly V-1 edges; stop early if graph is disconnected
        while (edgeCountInSpanningTree < V - 1 && !pq.isEmpty()) {
            Info front = pq.poll();
            
            if (isMST[front.vertex]) continue; // skip stale PQ entries
            
            isMST[front.vertex] = true;
            parent[front.vertex] = front.parent;
            minCost += front.wt;
            if (front.parent != -1) edgeCountInSpanningTree++; // start vertex adds no edge
            
            for (Map.Entry<Integer, Integer> entry : graphAdjMap.get(front.vertex).entrySet()) {
                int adjacentVertex = entry.getKey();
                int edgeWt = entry.getValue();
                
                if (isMST[adjacentVertex]) continue; // already in MST
                
                // Lazy insertion: may add duplicates, handled by stale check above
                pq.offer(new Info(adjacentVertex, edgeWt, front.vertex));
            }
        }
        
        // V-1 edges = connected MST found; else disconnected
        return edgeCountInSpanningTree == V - 1 ? minCost : -1;
    }
    
    public static int prims(int n, List<List<Integer>> edges, int start) {
        // Index 0 unused; vertices are 1-based [1, n]
        List<Map<Integer, Integer>> graphAdjMap = new ArrayList<>();
        for (int i = 0; i <= n; i++) graphAdjMap.add(new HashMap<>());
        
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1), wt = edge.get(2);
            graphAdjMap.get(u).put(v, wt);
            graphAdjMap.get(v).put(u, wt); // undirected
        }
        
        return primsAlgo(n, graphAdjMap, start);
    }
}