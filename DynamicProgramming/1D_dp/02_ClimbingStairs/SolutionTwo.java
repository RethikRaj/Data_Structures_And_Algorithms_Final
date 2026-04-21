
// Backward : 
/*
    To reach step n , we can either reach from n-1 or n - 2. So if we find the number of ways to reach n-1 from step 0 and n-2 from step 0 , then we can find the answer.
 */

public class SolutionTwo {
    // g(n) : returns the number of ways to reach n'th step from step 0;
    public int g(int n) {
        if(n < 0) return 0;
        if(n == 0) return 1;

        return g(n - 1) + g(n - 2);
    }

    public int g_td(int n, int[] dp) {
        if(n < 0) return 0;
        if(n == 0) return 1;

        if(dp[n] != -1) return dp[n];

        return dp[n] = g_td(n-1, dp) + g_td(n-2, dp);
    }

    public int g_bu(int n) {
        int[] dp = new int[1000];

        if(n < 0) return 0;
        if(n == 0) return 1;

        dp[0] = 1;

        // Memoization : n to 0 
        // In bottom up we can observe that the subproblem is builded from 0 to n.
        for(int i = 1; i <= n; i++) {
            if(i - 2 < 0) {
                dp[i] = dp[i-1] + 0; // infered from : n < 0 ,then return 0;
            }else {
                dp[i] = dp[i-1] + dp[i-2];
            }
        }

        return dp[n];
    }

    public int g_bu_optimised(int n) {
        if(n < 0) return 0;
        if(n == 0) return 1;

        int prev2 = 0;
        int prev1 = 1;

        for(int i = 1; i <= n; i++) {
            int ans = prev2 + prev1;
            prev2 = prev1;
            prev1 = ans;
        }

        return prev1;

    }

    public int climbStairs(int n) {
        // return g(n);

        // int[] dp = new int[1000];
        // Arrays.fill(dp , -1);
        // return g_td(n , dp);

        // return g_bu(n);

        return g_bu_optimised(n);
    }
}
