
// Forward recursion
public class SolutionOne {
    // f(i) : returns the number of ways to go from i'th step to n'th step
    public int f(int i, int n) {
        if(i > n) return 0;
        if(i == n) return 1; // 1 way found 

        return f(i + 1, n) + f(i + 2, n);
    }

    // Memoization 
    // The parameter `i` is enough to uniquely identify each subproblem => 1D-dp
    // Only parameter `i` is changing => 1D-dp
    public int f_td(int i, int n, int[] dp) {
        if(i > n) return 0;
        if(i == n) return 1;

        if(dp[i] != -1) return dp[i];

        return dp[i] = f_td(i + 1, n, dp) + f_td(i + 2, n, dp);
    }

    // Tabulation
    public int f_bu(int i, int n) {
        int[] dp = new int[1000];

        if(i > n) return 0;
        if(i == n) return 1;

        // Step 2 : Initializa state of dp array
        for(int j = n + 1; j < 1000; j++) {
            dp[j] = 0;
        }
        dp[n] = 1;

        // Step 3 :
        // In memoization : `i` goes from 0 to n => n to 0 in tabulation
        // In bottom up we can observe that the subproblem is builded from n to 0.
        for(int j = n - 1; j >= 0 ; j--) {
            dp[j] = dp[j + 1] + dp[j + 2];
        }

        return dp[0];
    }

    // Space optimised :
    // dp[j] -> depends only on dp[j+1], dp[j+2] => 2 variables is enough
    public int f_bu_optimised(int i, int n) {
        if(i > n) return 0;
        if(i == n) return 1;

        int next2 = 0;
        int next1 = 1;

        for(int j = n - 1; j >= 0 ; j--) {
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
