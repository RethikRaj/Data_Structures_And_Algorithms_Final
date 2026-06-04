class Solution {
    private int[] par;
    private int[] size; // No specific reason to use size , just if we use rank we would need to implement 3 cases in union 
    
    private int find(int x) {
        if(x == par[x]) return x;
        return par[x] = find(par[x]);
    }
    
    // union returns true if Cycle is detected
    private boolean union(int a, int b) {
        int leader_a = find(a);
        int leader_b = find(b);
        
        if(leader_a == leader_b) return true; // belongs to same group
        
        if(size[leader_a] >= size[leader_b]) {
            size[leader_a] += size[leader_b];
            par[leader_b] = leader_a;
        }else {
            size[leader_b] += size[leader_a];
            par[leader_a] = leader_b;
        }
        return false; // No cycle
    }
    
    public boolean isCycle(int V, int[][] edges) {
        par = new int[V];
        size = new int[V];
        // !Don't forget to initialize
        for(int i = 0; i < V; i++) {
            par[i] = i;
            size[i] = 1;
        }
        
        for(int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            
            boolean hasCycle = union(src, dest);
            
            if(hasCycle == true) return true;
        }
        return false;
    }
}