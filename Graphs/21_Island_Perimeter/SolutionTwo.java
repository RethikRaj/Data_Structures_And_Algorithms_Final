// IDEA : Perimeter Contributed by one land cell = 4 - (Number of adjacent land cells)

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = { 0, 0, -1, 1};

    private int dfs(int[][] grid, int row, int col) {
        grid[row][col] = -1; // Marking as visited;

        int countOfAdjacentLandCells = 0;

        int perimeter = 0;

        for(int i = 0; i < 4; i++) {
            int adjRow = row + DELTA_ROW[i];
            int adjCol = col + DELTA_COL[i];

            if (adjRow < 0 || adjCol < 0 || adjRow >= grid.length || adjCol >= grid[0].length) continue;
            
            if(grid[adjRow][adjCol] == 0) continue;

            countOfAdjacentLandCells += 1;

            if(grid[adjRow][adjCol] == -1) continue;

            perimeter += dfs(grid, adjRow, adjCol);

        }

        perimeter += (4-countOfAdjacentLandCells);

        return perimeter;
    }

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m;i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }

        return -1;
    }
}
