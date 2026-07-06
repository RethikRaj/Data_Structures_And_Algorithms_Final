class Solution {
    private int[] par;
    private int[] size;

    private int find(int x) {
        if(x == par[x]) return x;
        return par[x] = find(par[x]);
    }

    private void union(int a, int b) {
        int leader_a = find(a);
        int leader_b = find(b);

        if(leader_a == leader_b) return;

        if(size[leader_a] >= size[leader_b]) {
            size[leader_a] += size[leader_b];
            par[leader_b] = leader_a;
        }else {
            size[leader_b] += size[leader_a];
            par[leader_a] = leader_b;
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // DSU
        par = new int[n];
        size = new int[n];

        for(int i = 0; i < n;i++) {
            par[i] = i;
            size[i] = 0;
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            union(u, v);
        }

        return find(source) == find(destination);
    }
}