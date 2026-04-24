import java.util.* ;
import java.io.*; 

public class SolutionTwo {
    static int mod = 1000000007;

    public static long f(int i) {
        if(i == 0 || i == 1 || i == 2) return i;
        
        long singleWays = f(i-1) % mod;
        long pairWays = ( (i-1)%mod * f(i-2) % mod )  % mod;
        return ( singleWays % mod + pairWays % mod ) % mod;
    }

    public static long f_td(int i, long[] dp) {
        if(i == 0 || i == 1 || i == 2) return i;

        if(dp[i] != -1) return dp[i];
        
        long singleWays = f_td(i-1, dp) % mod;
        long pairWays = ( (i-1)%mod * f_td(i-2, dp) % mod )  % mod;
        return dp[i] = ( singleWays % mod + pairWays % mod ) % mod;
    }

    public static long f_bu(int i) {
        long[] dp = new long[10005];

        if(i == 0 || i == 1 || i == 2) return i;

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for(int j = 3 ; j <= i; j++) {
            long singleWays = dp[j-1] % mod;
            long pairWays = ( (j-1)%mod * dp[j-2] % mod )  % mod;
            dp[j] = ( singleWays % mod + pairWays % mod ) % mod;
        }

        return dp[i];
    }

    public static long f_bu_optimised(int i) {
        if(i == 0 || i == 1 || i == 2) return i;

        long prev2 = 1;
        long prev1 = 2;

        for(int j = 3 ; j <= i; j++) {
            long singleWays = prev1 % mod;
            long pairWays = ( (j-1)%mod * prev2 % mod )  % mod;
            long ans = ( singleWays % mod + pairWays % mod ) % mod;

            prev2 = prev1;
            prev1 = ans;
        }

        return prev1;
    }

    public static int numberOfWays(int n) {
        // return (int)f(n);

        // long[] dp = new long[10005];
        // Arrays.fill(dp, -1);
        // return (int)f_td(n, dp);

        // return (int)f_bu(n);

        return (int)f_bu_optimised(n);
    }
}

