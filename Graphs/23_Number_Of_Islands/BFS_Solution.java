class Vertex {
    int row;
    int col;

    Vertex(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};

    // BFS + implicit visited
    private void bfs(char[][] grid, Vertex src) {
        Queue<Vertex> q = new ArrayDeque<>();

        q.offer(src);
        grid[src.row][src.col] = '2'; // Mark as visited

        while (!q.isEmpty()) {
            Vertex front = q.poll();

            // Explore adjacent cells
            for (int i = 0; i < DELTA_ROW.length; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                if (adjRow >= 0 && adjRow < grid.length &&
                    adjCol >= 0 && adjCol < grid[0].length &&
                    grid[adjRow][adjCol] == '1') {

                    q.offer(new Vertex(adjRow, adjCol));
                    grid[adjRow][adjCol] = '2'; // Mark as visited
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, new Vertex(i, j));
                }
            }
        }

        return count;
    }
}