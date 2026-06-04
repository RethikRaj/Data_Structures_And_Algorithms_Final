class Pair {
    int row;
    int col;

    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};
    
    // Method 1 : BFS + explicit visited.

    // BFS + implicit visited
    private void bfs(char[][] grid, Pair src) {
        Queue<Pair> q = new ArrayDeque<>();
        
        q.offer(src);
        grid[src.row][src.col] = '2'; // Mark as visited

        while(!q.isEmpty()) {
            Pair front = q.poll();

            // Explore adjacents
            for (int i = 0; i < DELTA_ROW.length; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                if (adjRow >= 0 && adjRow < grid.length && adjCol >= 0 && adjCol < grid[0].length && grid[adjRow][adjCol] == '1') {
                    q.offer(new Pair(adjRow, adjCol));
                    grid[adjRow][adjCol] = '2';
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;

        for(int i = 0; i < grid.length;i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1' && grid[i][j] != '2') {
                    count += 1;
                    bfs(grid, new Pair(i, j));
                }
            }
        }

        return count;
    }
}