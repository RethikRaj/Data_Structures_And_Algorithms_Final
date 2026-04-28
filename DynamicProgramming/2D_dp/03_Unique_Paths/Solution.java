class Solution {
    // TC: O(2^(m+n)) | SC: O(m+n) recursion stack
    public int f(int m, int n, int row, int col) {
        if(row == m - 1 && col == n - 1) return 1;

        int optionOne = (row + 1 < m) ? f(m, n, row + 1, col) : 0;
        int optionTwo = (col + 1 < n) ? f(m, n, row, col + 1) : 0;

        return optionOne + optionTwo;
    }

    // TC: O(m*n) | SC: O(m*n) dp + O(m+n) recursion stack
    public int f_td(int m, int n, int row, int col, int[][] dp) {
        if(row == m - 1 && col == n - 1) return 1;

        if(dp[row][col] != -1) return dp[row][col];

        int optionOne = (row + 1 < m) ? f_td(m, n, row + 1, col, dp) : 0;
        int optionTwo = (col + 1 < n) ? f_td(m, n, row, col + 1, dp) : 0;

        return dp[row][col] = optionOne + optionTwo;
    }

    // TC: O(m*n) | SC: O(m*n)
    public int f_bu(int m, int n) {
        int[][] dp = new int[105][105];

        if(m == 1 && n == 1) return 1; // step 2.1
        dp[m-1][n-1] = 1; // step 2.2

        // dp[row][col] depends upon dp[row + 1][col] , dp[row][col + 1]
        // Therefore we need to : row : (m-1 to 0)(Bottom to top) and col : (n-1 to 0) (R to L)
        for(int row = m - 1 ; row >= 0; row--) {
            for(int col = n - 1; col >= 0 ; col--) {
                if(row == m - 1 && col == n-1) continue;

                int optionOne = (row + 1 < m) ? dp[row + 1][col] : 0;
                int optionTwo = (col + 1 < n) ? dp[row][col + 1] : 0;

                dp[row][col] = optionOne + optionTwo;
            }
        }
        return dp[0][0];
    }

    // TC: O(m*n) | SC: O(n)
    public int f_bu_optimised(int m, int n) {
        int[] nextRow = new int[105];

        if(m == 1 && n == 1) return 1; // step 2.1
        nextRow[n-1] = 1; // step 2.2

        // One more base case we can see is that from all columns of all last row we can reach only in 1 way;
        for(int col = 0; col < n - 1; col++) {
            nextRow[col] = 1;
        }
        // Why did i add a extra base case -> Dry run and see without this base case;
        // Since we did above check row : (m-2 to 0), col : (n-1 to 0)

        for(int row = m - 2 ; row >= 0; row--) {
            int[] currRow = new int[105];

            for(int col = n - 1; col >= 0 ; col--) {
                int optionOne = nextRow[col];
                int optionTwo = (col + 1 < n) ? currRow[col + 1] : 0;

                currRow[col] = optionOne + optionTwo;
            }

            nextRow = currRow;
        }
        return nextRow[0];
    }

    public int uniquePaths(int m, int n) {
        // return f(m, n, 0, 0);

        // top-down
        // int[][] dp = new int[105][105];
        // for(int[] row : dp){
        //     Arrays.fill(row, -1);
        // }
        // return f_td(m, n, 0, 0, dp);

        // return f_bu(m, n);

        return f_bu_optimised(m, n);
    }
}