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

    public void islandsAndTreasure(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Multi source BFS
        Queue<Pair> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m;i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    q.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()) {
            Pair front = q.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                if(adjRow < 0 || adjCol < 0 || adjRow >= m || adjCol >= n) continue;

                if(visited[adjRow][adjCol] == true) continue;

                if(grid[adjRow][adjCol] == -1 || grid[adjRow][adjCol] == 0) continue;

                q.offer(new Pair(adjRow, adjCol));
                visited[adjRow][adjCol] = true;
                grid[adjRow][adjCol] = grid[front.row][front.col] + 1;
                
            }
        }
    }
}
