class Solution {
    private int perimeter;

    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};
    
    private void dfs(int[][] grid, int row, int col) {
        grid[row][col] = -1; // 1. mark as visited

        // 2. Process curr cell

        // 3. Explore adjacents
        for (int i = 0; i < 4; i++) {
            int adjRow = row + DELTA_ROW[i];
            int adjCol = col + DELTA_COL[i];

            // out of bounds or water — this edge contributes to perimeter
            if (adjRow < 0 || adjCol < 0 || adjRow >= grid.length || adjCol >= grid[0].length || grid[adjRow][adjCol] == 0) {
                perimeter++;
                continue;
            }

            if (grid[adjRow][adjCol] == -1) continue; // already visited

            dfs(grid, adjRow, adjCol);
        }
    }

    public int islandPerimeter(int[][] grid) {
        // Find the first land cell as DFS entry point
        int sr = -1, sc = -1;
        outer:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) { sr = i; sc = j; break outer; }
            }
        }

        perimeter = 0;
        dfs(grid, sr, sc);
        return perimeter;
    }
}