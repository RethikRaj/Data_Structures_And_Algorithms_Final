// In solution one : ( Checks validity before making the recursive call) (Guard at the call site (before calling):)
// I didn't even do a recursive call if the next cell is out of bounds or blocked.

// In solution Two : (Check validity at base case)(Guard at the function entry (after calling):)

class SolutionTwo {

    // 1. Pure Recursion
    public int f(int[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length) return 0;
        if (row == grid.length - 1 && col == grid[0].length - 1) return (grid[row][col] != 1) ? 1 : 0;
        if (grid[row][col] == 1) return 0;

        int optionOne = f(grid, row + 1, col);
        int optionTwo = f(grid, row, col + 1);

        return optionOne + optionTwo;
    }

    // 2. Top-Down (Memoization)
    public int f_td(int[][] grid, int row, int col, int[][] dp) {
        if (row >= grid.length || col >= grid[0].length) return 0;
        if (row == grid.length - 1 && col == grid[0].length - 1) return (grid[row][col] != 1) ? 1 : 0;
        if (grid[row][col] == 1) return 0;

        if (dp[row][col] != -1) return dp[row][col];

        int optionOne = f_td(grid, row + 1, col, dp);
        int optionTwo = f_td(grid, row, col + 1, dp);

        return dp[row][col] = optionOne + optionTwo;
    }

    // 3. Bottom-Up (Tabulation)
    public int f_bu(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        if (m == 1 && n == 1 && grid[0][0] != 1) return 1;
        dp[m - 1][n - 1] = (grid[m-1][n-1] != 1) ? 1 : 0;

        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (row == m - 1 && col == n - 1) continue;

                if (grid[row][col] == 1) {
                    dp[row][col] = 0;
                    continue;
                }

                int optionOne = (row + 1 < m) ? dp[row + 1][col] : 0;
                int optionTwo = (col + 1 < n) ? dp[row][col + 1] : 0;

                dp[row][col] = optionOne + optionTwo;
            }
        }

        return dp[0][0];
    }

    // 4. Bottom-Up Space Optimised
    public int f_bu_optimised(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (m == 1 && n == 1 && grid[0][0] != 1) return 1;

        int[] nextRow = new int[n];
        nextRow[n - 1] = (grid[m-1][n-1] != 1) ? 1 : 0;

        for (int col = n - 2; col >= 0; col--) {
            nextRow[col] = (grid[m - 1][col] != 1) ? nextRow[col + 1] : 0;
        }

        for (int row = m - 2; row >= 0; row--) {
            int[] currRow = new int[n];
            for (int col = n - 1; col >= 0; col--) {
                if (grid[row][col] == 1) {
                    currRow[col] = 0;
                    continue;
                }

                int optionOne = (row + 1 < m) ? nextRow[col] : 0;
                int optionTwo = (col + 1 < n) ? currRow[col + 1] : 0;

                currRow[col] = optionOne + optionTwo;
            }
            nextRow = currRow;
        }

        return nextRow[0];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;

        // return f(obstacleGrid, 0, 0);

        // int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        // for (int[] row : dp) Arrays.fill(row, -1);
        // return f_td(obstacleGrid, 0, 0, dp);

        // return f_bu(obstacleGrid);

        return f_bu_optimised(obstacleGrid);
    }
}