class Edge {
    int src;
    int dest;
    int wt;
    
    Edge(int src, int dest, int wt) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }
}

class Result {
    private static int[] par;
    private static int[] size;
    
    private static int find(int x) {
        if(x == par[x]) return x;
        return par[x] = find(par[x]);
    }
    
    // returns true if cycle, else false
    private static boolean union(int a, int b) {
        int leader_a = find(a);
        int leader_b = find(b);
        
        if(leader_a == leader_b) return true;
        
        if(size[leader_a] >= size[leader_b]) {
            size[leader_a] += size[leader_b];
            par[leader_b] = leader_a;
        }else {
            size[leader_b] += size[leader_a];
            par[leader_a] = leader_b;
        }
        return false;
    }

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.wt - e2.wt);
        // O(E logE)
        for(int i = 0 ; i < gFrom.size(); i++) {
            pq.add(new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i)));
        }
        
        // Initialize DSU : O(V)
        par = new int[gNodes + 1];
        size = new int[gNodes + 1];
        // Vertices are 1 based -> V lies in range [1, gNodes]            
        for(int i = 1; i <= gNodes; i++) {
            par[i] = i;
            size[i] = 1;
        }
        
        // Number of edges in MST = V - 1
        int edgeCountInSpanningTree = 0;
        int minCost = 0;
    
        while(edgeCountInSpanningTree < gNodes - 1 && !pq.isEmpty()) { // while loop in worst case runs for O(E) times , not O(V).
            Edge front = pq.poll();
            
            // Way 1 to check cycle 
            boolean causesCycle = union(front.src, front.dest); // O(log*n)
            
            if(causesCycle) continue;
            else {
                minCost += front.wt;
                edgeCountInSpanningTree += 1;
            }
            
            // Way 2 to check cycle . Notice in union() i pass leaders so even if 4 find calls happen but the last 2 find calls will be O(1) since we pass leaders.
            // int leader_src = find(front.src);
            // int leader_dest = find(front.dest);
            
            // if(leader_src == leader_dest) continue; // including it will cause cycle
            // else {
            //     minCost += front.wt;
            //     edgeCountInSpanningTree += 1;
            //     union(leader_src, leader_dest);
            // }
        }
        return edgeCountInSpanningTree == gNodes - 1 ? minCost : -1; // -1 --> NO spanning Tree possible
    }
}