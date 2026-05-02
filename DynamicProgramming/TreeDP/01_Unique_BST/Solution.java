class Solution {
    public int f(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int ans = 0;
        for(int k = 1 ; k <= n ; k++) {
            ans = ans + (f(k - 1) * f(n - k));
        }

        return ans;
    }

    public int f_td(int n, int[] dp) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(n == 2) return 2;

        if(dp[n] != -1) return dp[n];

        int ans = 0;
        for(int k = 1 ; k <= n ; k++) {
            ans = ans + (f_td(k - 1, dp) * f_td(n - k, dp));
        }

        return dp[n] = ans;
    }

    public int f_bu(int n) {
        int[] dp = new int[25];

        if(n == 0) return 1;
        if(n == 1) return 1;
        if(n == 2) return 2;

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n ; i++) {
            int ans = 0;
            for(int k = 1 ; k <= i ; k++) {
                ans = ans + (dp[k - 1] * dp[i - k]);
            }

            dp[i] = ans;
        }

        return dp[n];
    }

    public int numTrees(int n) {
        // return f(n);

        // int[] dp = new int[25];
        // Arrays.fill(dp, -1);

        // return f_td(n, dp);

        return f_bu(n);
    }
}