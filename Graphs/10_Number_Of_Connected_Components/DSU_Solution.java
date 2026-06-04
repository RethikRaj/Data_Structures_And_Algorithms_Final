class Solution {
    private int[] par;
    private int[] size;

    private int find(int x) {
        if(par[x] == x) return x;
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

    public int countComponents(int n, int[][] edges) {
        par = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for(int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        // Number of connected components == Number of leaders
        int result = 0;
        for(int i = 0; i < n ; i++) {
            if(i == par[i]) {
                // `i` is a leader
                result += 1;
            }
        }

        return result;
    }
}
