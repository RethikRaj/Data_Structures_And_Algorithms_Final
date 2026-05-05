class Solution {
    public int f(int i) {
        if(i == 1 || i == 2) return i;
        
        return f(i - 1) + f(i - 2);
    }
    
    public int f_td(int i, int[] dp) {
        if(i == 1 || i == 2) return i;
        
        if(dp[i] != -1) return dp[i];
        
        return dp[i] = f_td(i - 1, dp) + f_td(i - 2, dp);
    }
    
    public int f_bu(int n) {
        int[] dp = new int[50];
        
        if(n == 1 || n == 2) return n;
        dp[1] = 1;
        dp[2] = 2;
        
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
    
    public int f_bu_optimised(int n) {
        if(n == 1 || n == 2) return n;
        
        int prev2 = 1;
        int prev1 = 2;
        
        for(int i = 3; i <= n; i++) {
            int ans = prev1 + prev2;
            prev2 = prev1;
            prev1 = ans;
        }
        return prev1;
    }
    
    public int numberOfWays(int n) {
        // return f(n);
        
        // int[] dp = new int[50];
        // Arrays.fill(dp, -1);
        
        // return f_td(n, dp);
        
        // return f_bu(n);
        
        return f_bu_optimised(n);
    }
};