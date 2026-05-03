// Backward recursion

class SolutionTwo {

    public int g(int i, int[] costs) {
        if(i == 0 || i == 1) return 0;

        return Math.min(g(i - 1, costs) + costs[i - 1] , g(i - 2, costs) + costs[i - 2]);
    }

    public int g_td(int i, int[] costs, int[] dp) {
        if(i == 0 || i == 1) return 0;

        if(dp[i] != -1) return dp[i];

        return dp[i] = Math.min(g_td(i - 1, costs, dp) + costs[i - 1] , g_td(i - 2, costs, dp) + costs[i - 2]);
    }

    public int g_bu(int[] costs) {
        int[] dp = new int[1005];

        // Step 2.1 
        if(costs.length == 1) return costs[0];
        if(costs.length == 2) return Math.min(costs[0], costs[1]);
        // Step 2.2
        dp[0] = dp[1] = 0;

        for(int i = 2; i <= costs.length; i++) {
            dp[i] = Math.min(dp[i-1] + costs[i - 1] , dp[i-2] + costs[i - 2]);
        }

        return dp[costs.length];
    }

    public int g_bu_optimised(int[] costs) {
        if(costs.length == 1) return costs[0];
        if(costs.length == 2) return Math.min(costs[0], costs[1]);

        int prev2 = 0, prev1 = 0;

        for(int i = 2; i <= costs.length; i++) {
            int curr = Math.min(prev1 + costs[i - 1] , prev2 + costs[i - 2]);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // recursive
        // return g(n, cost);

        // top-down
        // int[] dp = new int[1005];
        // Arrays.fill(dp, -1);
        // return g_td(n, cost, dp);

        // Bottom-up
        // return g_bu(cost);

        return g_bu_optimised(cost);
    }
}
