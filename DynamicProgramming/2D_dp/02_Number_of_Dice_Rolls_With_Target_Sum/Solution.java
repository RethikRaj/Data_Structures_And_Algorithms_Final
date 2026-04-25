class Solution {
    public static final int MOD = 1000000007;

    public static final int MAX_N = 35;
    public static final int MAX_TARGET = 1005;

    // TC : O( k^n ) , SC : O(n)
    public long f(int dice, int k, int target) {
        if(target == 0 && dice == 0) return 1;
        if(dice == 0) return 0;

        long totalWays = 0;

        for(int face = 1 ; face <= k ; face++) {
            if(target - face < 0) break;
            totalWays = (totalWays % MOD + f(dice - 1, k , target - face) % MOD) % MOD;
        }

        return totalWays % MOD;
    }

    // TC : O(n * t * k) , SC : O(n*t)
    public long f_td(int dice, int k, int target, long[][] dp) {
        if(target == 0 && dice == 0) return 1;
        if(dice == 0) return 0;

        if(dp[dice][target] != -1) return dp[dice][target];

        long totalWays = 0;

        for(int face = 1 ; face <= k ; face++) {
            if(target - face < 0) break;
            totalWays = (totalWays % MOD + f_td(dice - 1, k , target - face, dp) % MOD) % MOD;
        }

        return dp[dice][target] = totalWays % MOD;
    }

    // TC : O(n * t * k) , SC : O(n*t)
    public long f_bu(int d, int k, int t) {
        long[][] dp = new long[MAX_N][MAX_TARGET];

        // Step 2.1 :
        if(t == 0 && d == 0) return 1;
        if(d == 0) return 0;
        // Step 2.2 :
        dp[0][0] = 1;
        // columns of row 0 are already initialized with 0 by default;

        // Step 3 : 
        for(int dice = 1 ; dice <= d; dice++) {
            for(int target = 0 ; target <= t; target++) {
                long totalWays = 0;

                for(int face = 1 ; face <= k ; face++) {
                    if(target - face < 0) break;
                    totalWays = (totalWays % MOD + dp[dice-1][target - face] % MOD) % MOD;
                }

                dp[dice][target] = totalWays % MOD;
            }
        }

        return dp[d][t];
    }

    // TC : O(n * t * k) , SC : O(t)
    public long f_bu_optimised(int d, int k, int t) {
        long[] prevRow = new long[MAX_TARGET];

        if(t == 0 && d == 0) return 1;
        if(d == 0) return 0;
        prevRow[0] = 1;

        for(int dice = 1 ; dice <= d; dice++) {
            long[] currRow = new long[MAX_TARGET];
            for(int target = 0 ; target <= t; target++) {
                long totalWays = 0;

                for(int face = 1 ; face <= k ; face++) {
                    if(target - face < 0) break;
                    totalWays = (totalWays % MOD + prevRow[target - face] % MOD) % MOD;
                }

                currRow[target] = totalWays % MOD;
            }
            prevRow = currRow; //!Update
        }

        return prevRow[t];
    }


    public int numRollsToTarget(int n, int k, int target) {
        // return (int)f(n, k, target);

        // long[][] dp = new long[MAX_N][MAX_TARGET];
        // for(long[] arr : dp) {
        //     Arrays.fill(arr, -1L);
        // }    
        // return (int)f_td(n, k, target, dp);

        // return (int)f_bu(n, k, target);

        return (int)f_bu_optimised(n, k, target);
    }
}