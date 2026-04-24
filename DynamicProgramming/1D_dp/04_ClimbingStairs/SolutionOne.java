
// Forward recursion
public class SolutionOne {
    // f(i) : returns the number of ways to go from i'th step to n'th step
    public int f(int i, int n) {
        if(i == n) return 1; // Only 1 way 
        if(i == n - 1) return 1; // Only 1 way 

        return f(i + 1, n) + f(i + 2, n);
    }

    // Memoization 
    // The parameter `i` is enough to uniquely identify each subproblem => 1D-dp
    // Only parameter `i` is changing => 1D-dp
    public int f_td(int i, int n, int[] dp) {
        if(i == n) return 1; // Only 1 way 
        if(i == n - 1) return 1; // Only 1 way 

        if(dp[i] != -1) return dp[i];

        return dp[i] = f_td(i + 1, n, dp) + f_td(i + 2, n, dp);
    }

    // Tabulation
    public int f_bu(int i, int n) {
        int[] dp = new int[1000];

        // Step 2 : 
        if(i == n) return 1;
        if(i == n - 1) return 1;

        // Below loop is not needed but you can write if you want
        // for(int j = n + 1; j < 1000; j++) {
        //     dp[j] = 0;
        // }
        dp[n] = 1;
        dp[n-1] = 1;

        // Step 3 :
        // In bottom up : dp[j] depends upon dp[j+1], dp[j+2] => Build solution from R to L.
        // In memoization : `i` goes from 0 to n => n to 0 in tabulation
        for(int j = n - 2; j >= 0 ; j--) {
            dp[j] = dp[j + 1] + dp[j + 2];
        }

        return dp[0];
    }

    // Space optimised :
    // dp[j] -> depends only on dp[j+1], dp[j+2] => 2 variables is enough
    public int f_bu_optimised(int i, int n) {
        if(i == n) return 1;
        if(i == n - 1) return 1;

        int next2 = 1;
        int next1 = 1;

        for(int j = n - 2; j >= 0 ; j--) {
            int ans = next1 + next2;
            next2 = next1;
            next1 = ans;
        }

        return next1;
    }

    public int climbStairs(int n) {
        // return f(0, n);
        
        // int[] dp = new int[1000];
        // Arrays.fill(dp , -1);
        // return f_td(0, n , dp);

        // return f_bu(0, n);

        return f_bu_optimised(0, n);
    }
}
