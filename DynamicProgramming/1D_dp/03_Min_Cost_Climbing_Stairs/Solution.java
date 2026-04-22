class Solution {

    public int f(int i, int[] costs) {
        if(i == costs.length) return 0;
        
        int path1Cost = (i + 1 < costs.length) ? f(i + 1, costs) : 0;

        int path2Cost = (i + 2 < costs.length) ? f(i + 2, costs) : 0;

        return Math.min(path1Cost, path2Cost) + costs[i];
    }

    public int f_td(int i, int[] costs, int[] dp) {
        if(i == costs.length) return 0;

        // step 3
        if(dp[i] != -1) return dp[i];

        int path1Cost = (i + 1 < costs.length) ? f_td(i + 1, costs, dp) : 0;
        int path2Cost = (i + 2 < costs.length) ? f_td(i + 2, costs, dp) : 0;
        return dp[i] = Math.min(path1Cost, path2Cost) + costs[i]; // step 2
    }

    // dp[i] = minimum cost to reach the top starting from step i.
    public int f_bu(int[] costs) {
        int[] dp = new int[1500];

        dp[costs.length] = 0;

        for(int j = costs.length - 1; j >= 0; j--) {
            int path1Cost = (j + 1 < costs.length) ? dp[j + 1] : 0;
            int path2Cost = (j + 2 < costs.length) ? dp[j + 2] : 0;
            dp[j] = Math.min(path1Cost, path2Cost) + costs[j];
        }

        return Math.min(dp[0], dp[1]);
    }

    public int f_bu_optimised(int[] costs) {
        int next2 = 0;
        int next1 = 0;

        for(int j = costs.length - 1; j >= 0; j--) {
            int ans = Math.min(next1, next2) + costs[j];
            next2 = next1;
            next1 = ans;
        }

        return Math.min(next1, next2);
    }

    public int minCostClimbingStairs(int[] cost) {
        // recursive
        // return Math.min(f(0, cost) , f(1, cost));

        // top-down
        // int[] dp = new int[1500]; // step1 
        // Arrays.fill(dp, -1);
        // return Math.min(f_td(0, cost, dp) , f_td(1, cost, dp));

        // bottom-up
        // return f_bu(cost);

        // space-optimised
        return f_bu_optimised(cost);
    } 
}