class Solution {
    // MethodOne : Center node <-> degree == n - 1
    private int methodOne(int[][] edges) {
        int maxVertex = Integer.MIN_VALUE; // Represents n
        Map<Integer, Integer> mp = new HashMap<>(); // Vertex -> Degree
        for(int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            mp.put(src, mp.getOrDefault(src, 0) + 1);
            mp.put(dest, mp.getOrDefault(dest, 0) + 1);

            maxVertex = Math.max(maxVertex, Math.max(src, dest));
        }

        for(Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if(entry.getValue() == maxVertex - 1) return entry.getKey();
        }

        return -1;
    }

    // MethodTwo : Center node <-> common node between any two edges.
    private int methodTwo(int[][] edges) {
        int a = edges[0][0];
        int b = edges[0][1];

        int c = edges[1][0];
        int d = edges[1][1];

        if(a == c || a == d) return a;

        return b;
    }

    public int findCenter(int[][] edges) {
        // return methodOne(edges);

        return methodTwo(edges);
    }
}