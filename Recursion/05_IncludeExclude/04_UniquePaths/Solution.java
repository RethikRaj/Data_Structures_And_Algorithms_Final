class Solution {
    public int f(int row, int col, int m, int n) {
        // Base Case :
        if(row == m - 1 && col == n-1) return 1;

        // Move right if cell exists
        int numberOfPathsWhileMovingRight = (col + 1) < n ? f(row, col + 1, m, n) : 0;

        // Move left if cell exists
        int numberOfPathsWhileMovingLeft = (row + 1) < m ? f(row + 1, col, m, n) : 0;

        return numberOfPathsWhileMovingRight + numberOfPathsWhileMovingLeft;
    }

    public int uniquePaths(int m, int n) {
        return f(0, 0, m, n);
    }
}