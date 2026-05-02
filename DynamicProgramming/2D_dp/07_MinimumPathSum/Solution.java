class Solution {
    private int m;
    private int n;

    public int f(int[][] grid, int row, int col) {
        // System.out.println("row : " + row + "col : " + col);
        if(row == m - 1 && col == n - 1) return grid[row][col];

        int sumRight = (col + 1 < n) ? f(grid, row, col + 1) : Integer.MAX_VALUE;
        int sumDown =  (row + 1 < m) ? f(grid, row + 1, col) : Integer.MAX_VALUE;
        
        return grid[row][col] + Math.min(sumRight, sumDown); // Why didn't i check for Integer.MAX_VALUE ? Because except the cell row == n-1 and col == n-1, in all other cells we can move either right or down or both always.
    }

    public int f_td(int[][] grid, int row, int col, int[][] dp) {
        if(row == m - 1 && col == n - 1) return grid[row][col];

        if(dp[row][col] != -1) return dp[row][col];

        int sumRight = (col + 1 < n) ? f_td(grid, row, col + 1, dp) : Integer.MAX_VALUE;
        int sumDown =  (row + 1 < m) ? f_td(grid, row + 1, col, dp) : Integer.MAX_VALUE;
        
        return dp[row][col] = grid[row][col] + Math.min(sumRight, sumDown);
    }

    public int f_bu(int[][] grid) {
        int[][] dp = new int[205][205];

        if(m == 1 && n == 1) return grid[0][0]; // Step 2.1
        dp[m - 1][n - 1] = grid[m - 1][n - 1]; // Step 2.2

        // Fill last row as base case so that inside loop we need not check if(row == m - 1 && col == n - 1) continue;
        for(int col = n - 2; col >= 0; col--) {
            dp[m - 1][col] = grid[m - 1][col] + dp[m - 1][col + 1]; // only can move right
        }
        // We can also fill last col ( but i am just leaving it here and handling it inside loop)

        for(int row = m - 2; row >= 0; row --) {
            for(int col = n - 1 ; col >= 0; col--) {
                int sumRight = (col + 1 < n) ? dp[row][col + 1] : Integer.MAX_VALUE;
                int sumDown =  (row + 1 < m) ? dp[row + 1][col] : Integer.MAX_VALUE;
                
                dp[row][col] = grid[row][col] + Math.min(sumRight, sumDown);
            }
        }

        return dp[0][0];
    }

    public int f_bu_optimised(int[][] grid) {
        int[] nextRow = new int[205];

        if(m == 1 && n == 1) return grid[0][0]; // Step 2.1
        nextRow[n - 1] = grid[m - 1][n - 1]; // Step 2.2

        // Fill last row as base case so that inside loop we need not check if(row == m - 1 && col == n - 1) continue;
        for(int col = n - 2; col >= 0; col--) {
            nextRow[col] = grid[m - 1][col] + nextRow[col + 1]; // only can move right
        }
        // We can also fill last col ( but i am just leaving it here and handling it inside loop)

        for(int row = m - 2; row >= 0; row --) {
            int[] currRow = new int[205];
            for(int col = n - 1 ; col >= 0; col--) {
                int sumRight = (col + 1 < n) ? currRow[col + 1] : Integer.MAX_VALUE;
                int sumDown =  (row + 1 < m) ? nextRow[col] : Integer.MAX_VALUE;
                
                currRow[col] = grid[row][col] + Math.min(sumRight, sumDown);
            }
            nextRow = currRow;
        }

        return nextRow[0];
    }

    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // return f(grid, 0, 0);

        // int[][] dp = new int[205][205];
        // for(int[] row : dp) Arrays.fill(row, -1);

        // return f_td(grid, 0, 0, dp);

        // return f_bu(grid);

        return f_bu_optimised(grid);
    }
}