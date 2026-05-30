class Solution {
    // Method 1 : 
    private void dfs(List<List<Integer>> rooms, int room, Set<Integer> visitedRooms) {
        visitedRooms.add(room);

        for(int key : rooms.get(room)) {
            if(!visitedRooms.contains(key)) {
                dfs(rooms, key, visitedRooms);
            }
        }
    }

    // Method 2 : 
    private boolean bfs(List<List<Integer>> rooms) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visitedRooms = new HashSet<>();

        q.offer(0);
        visitedRooms.add(0);

        while(!q.isEmpty()) {
            int front = q.poll();

            for(int key : rooms.get(front)) {
                if(!visitedRooms.contains(key)) {
                    q.offer(key);
                    visitedRooms.add(key);
                }
            }
        }

        return visitedRooms.size() == rooms.size();
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        // MEthod 1 : DFS
        // Set<Integer> visitedRooms = new HashSet<>();
        // dfs(rooms, 0, visitedRooms);
        // return visitedRooms.size() == n;

        // Method 2 : BFS

        return bfs(rooms);
    }
}