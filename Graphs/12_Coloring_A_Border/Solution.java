class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};

    private void dfs(int[][] grid, int row, int col, int originalColor, int newColor, boolean[][] visited) {
        // Step 1 
        visited[row][col] = true;

        // Step 2
        if(row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1) {
            grid[row][col] = newColor;
        }

        // Step 3 
        boolean toColor = false;
        for(int i = 0; i < DELTA_ROW.length; i++) {
            int adjRow = row + DELTA_ROW[i];
            int adjCol = col + DELTA_COL[i];

            if(adjRow >= 0 && adjRow < grid.length && adjCol >= 0 && adjCol < grid[0].length && !visited[adjRow][adjCol]) {

                if(grid[adjRow][adjCol] == originalColor) {
                    dfs(grid, adjRow, adjCol, originalColor, newColor, visited);
                }else {
                    toColor = true;
                }
            }
        }

        if(toColor == true) {
            grid[row][col] = newColor;
        }
    }

    // dfs without using visited
    // So confusing please wait 

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int originalColor = grid[row][col];
        if(originalColor == color) return grid;

        dfs(grid, row, col, originalColor, color, new boolean[grid.length][grid[0].length]);

        return grid;

    }
}