import java.util.Arrays;

class Solution {
    
    // Recursion - Brute
    // TC: O(3^n) - exponential due to overlapping subproblems recomputed
    // SC: O(n) - recursion stack depth
    static int f(int[] arr, int i, int j) {
        if(i == j) return 0;
        if(i + 1 == j) return 0;
        
        int minMultiplications = Integer.MAX_VALUE;
        // Try every possible split point(partitions) k between i and j
        for(int k = i + 1 ; k <= j-1 ; k++) {
            int currMultiplications = f(arr, i, k) + f(arr, k, j) + arr[i] * arr[j] * arr[k];
            minMultiplications = Math.min(minMultiplications, currMultiplications);
        }
        
        return minMultiplications; // Not needed to check minMultiplications = Integer.Max_VALUE because for loop runs atleast once for sure.
    }
    
    // Top-down
    // TC: O(n^3)
    // SC: O(n^2) - dp table + O(n) recursion stack
    static int f_td(int[] arr, int i, int j, int[][] dp) {
        if(i == j) return 0;
        if(i + 1 == j) return 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        
        int minMultiplications = Integer.MAX_VALUE;
        // Try every possible split point k between i and j
        for(int k = i + 1 ; k <= j-1 ; k++) {
            int currMultiplications = f_td(arr, i, k, dp) + f_td(arr, k, j, dp) + arr[i] * arr[j] * arr[k];
            minMultiplications = Math.min(minMultiplications, currMultiplications);
        }
        
        return dp[i][j] = minMultiplications;
    }
    
    // Bottom-up (Tabulation)
    // TC: O(n^3) - three nested loops over the dp table
    // SC: O(n^2) - dp table only, no recursion stack
    static int f_bu(int[] arr) {
        int[][] dp = new int[105][105];
        
        // Step 2.1
        if(arr.length == 1) return 0;
        if(arr.length == 2) return 0;
        // Step 2.2
        for(int row = 0; row < dp.length; row++) {
            dp[row][row] = 0;
        }
        for(int row = 0; row < dp.length - 1; row++) {
            dp[row][row + 1] = 0;
        }
        
        // Step 3:
        // Fill in increasing gap sizes (j - i), bottom-up
        for(int i = arr.length - 3 ; i >= 0 ; i--) {
            for(int j = i + 2; j < arr.length; j++) {
                
                int minMultiplications = Integer.MAX_VALUE;
                // Try every split point k; cost = left partition + right partition + merge cost
                for(int k = i + 1 ; k <= j-1 ; k++) {
                    int currMultiplications = dp[i][k] + dp[k][j] + arr[i] * arr[j] * arr[k];
                    minMultiplications = Math.min(minMultiplications, currMultiplications);
                }
                
                dp[i][j] = minMultiplications;
            }
        }
        
        return dp[0][arr.length - 1];
    }
    
    // bottom up one more way 
    static int f_bu_2(int[] arr) {
        int[][] dp = new int[105][105];

        // Step 2.1
        if(arr.length == 1) return 0;
        if(arr.length == 2) return 0;
        // Step 2.2
        // for(int row = 0; row < dp.length; row++) {
        //     dp[row][row] = 0;
        // }
        // for(int row = 0; row < dp.length - 1; row++) {
        //     dp[row][row + 1] = 0;
        // }
        for(int[] row : dp) Arrays.fill(row, 0); // we want 0 so fill zero at all places.

        for(int len = 3; len <= arr.length; len++) {
            for(int i = 0; i + len - 1 < arr.length; i++) {
                int j =  i + len - 1;

                int minMultiplications = Integer.MAX_VALUE;
                // Try every split point k; cost = left partition + right partition + merge cost
                for(int k = i + 1 ; k <= j-1 ; k++) {
                    int currMultiplications = dp[i][k] + dp[k][j] + arr[i] * arr[j] * arr[k];
                    minMultiplications = Math.min(minMultiplications, currMultiplications);
                }
                
                dp[i][j] = minMultiplications;
            }
        }
        return dp[0][arr.length-1];
    }

    static int matrixMultiplication(int arr[]) {
        // return f(arr, 0, arr.length - 1);
        
        // int[][] dp = new int[105][105];
        // for(int[] row : dp) {
        //     Arrays.fill(row, -1);
        // }
        
        // return f_td(arr, 0, arr.length - 1, dp);
        
        // return f_bu(arr);        

        return f_bu_2(arr);
    }
}