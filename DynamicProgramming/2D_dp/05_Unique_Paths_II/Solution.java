class Solution {
    

    public int f(int[][] grid,int row, int col) {
        if(row == grid.length - 1 && col == grid[0].length - 1 ) return 1;
        // Move down
        int optionOne = (row + 1 < grid.length && grid[row+1][col] != 1) ? f(grid, row + 1, col) : 0;
        // Move right
        int optionTwo = (col + 1 < grid[0].length && grid[row][col+1] != 1) ? f(grid, row, col + 1) : 0;

        return optionOne + optionTwo;
    }

    public int f_td(int[][] grid,int row, int col,int[][] dp) {
        if(row == grid.length - 1 && col == grid[0].length - 1 ) return 1;

        if(dp[row][col] != -1) return dp[row][col];

        // Move down
        int optionOne = (row + 1 < grid.length && grid[row+1][col] != 1) ? f_td(grid, row + 1, col, dp) : 0;
        // Move right
        int optionTwo = (col + 1 < grid[0].length && grid[row][col+1] != 1) ? f_td(grid, row, col + 1, dp) : 0;

        return dp[row][col] = optionOne + optionTwo;
    }

    public int f_bu(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[105][105];

        // Step 2.1
        if(m == 1 && n == 1 && grid[0][0] != 1) return 1; // grid[0][0] check is not needed since we check in driver func.
        // Step 2.2
        dp[m - 1][n - 1] = 1;

        // Step 3 : row : bottom to top , col : right to left
        for(int row = m - 1; row >= 0 ; row--) {
            for(int col = n - 1;  col >= 0 ; col--) {
                // !VVIMPPP ( either do this or fill last row as base case in step 2.2)
                if(row == m - 1 && col == n - 1) continue; 

                // Move down
                int optionOne = (row + 1 < m && grid[row+1][col] != 1) ? dp[row + 1][col] : 0;
                // Move right
                int optionTwo = (col + 1 < n && grid[row][col+1] != 1) ? dp[row][col + 1] : 0;
                
                dp[row][col] = optionOne + optionTwo;
            }
        }

        return dp[0][0];
    }

    public int f_bu_optimised(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] nextRow = new int[105];

        // Step 2.1
        if(m == 1 && n == 1 && grid[0][0] != 1) return 1; // grid[0][0] check is not needed since we check in driver func.
        // Step 2.2
        nextRow[n - 1] = 1;
        // Fill last row elements and change loop to start from m - 2
        for(int col = n - 2; col >= 0; col--) {
            nextRow[col] = (grid[m - 1][col + 1] != 1) ? nextRow[col + 1] : 0;
        }

        // Step 3 : row : bottom to top , col : right to left
        for(int row = m - 2; row >= 0 ; row--) {
            int[] currRow = new int[105];
            for(int col = n - 1;  col >= 0 ; col--) {
                // Move down
                int optionOne = (row + 1 < m && grid[row+1][col] != 1) ? nextRow[col] : 0;
                // Move right
                int optionTwo = (col + 1 < n && grid[row][col+1] != 1) ? currRow[col + 1] : 0;
                
                currRow[col] = optionOne + optionTwo;
                // System.out.println("Row : " + row + " col : " + col + "ans : " + currRow[col]);
            }
            nextRow = currRow;
        }

        return nextRow[0];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // If starting (0, 0) is itself obstacle then return 0.
        if(obstacleGrid[0][0] == 1) return 0;

        // return f(obstacleGrid, 0, 0);

        // int[][] dp = new int[105][105];
        // for(int[] row : dp) Arrays.fill(row, -1);
        // return f_td(obstacleGrid, 0, 0, dp);

        // return f_bu(obstacleGrid);

        return f_bu_optimised(obstacleGrid);
    }
}
