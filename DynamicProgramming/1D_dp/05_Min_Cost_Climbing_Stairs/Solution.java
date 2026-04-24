class Solution {

    public int f(int i, int[] costs) {
        if(i == costs.length) return 0;
        if(i == costs.length - 1) return costs[costs.length - 1];
        
        int path1Cost = f(i + 1, costs);
        int path2Cost = f(i + 2, costs);
        return Math.min(path1Cost, path2Cost) + costs[i];
    }

    public int f_td(int i, int[] costs, int[] dp) {
        if(i == costs.length) return 0;
        if(i == costs.length - 1) return costs[costs.length - 1];

        // step 3
        if(dp[i] != -1) return dp[i];

        int path1Cost = f_td(i + 1, costs, dp);
        int path2Cost = f_td(i + 2, costs, dp);
        return dp[i] = Math.min(path1Cost, path2Cost) + costs[i]; // step 2
    }

    // dp[i] = minimum cost to reach the top starting from step i.
    public int f_bu(int[] costs) {
        int[] dp = new int[1500];

        // Step 2.1 : Copy paste/convert base case (if needed). Note that we don't have parameter i in method signature . Convert base cases smartly.
        if(costs.length == 0) return 0;
        if(costs.length == 1) return costs[0];
        // Step 2.2 : Analyse base case and Update dp array
        dp[costs.length] = 0;
        dp[costs.length - 1] = costs[costs.length - 1];

        // step 3 : 
        // dp[j] depends upon dp[j+1], dp[j+2] => build solution from R to L
        for(int j = costs.length - 2; j >= 0; j--) {
            int path1Cost = dp[j + 1];
            int path2Cost = dp[j + 2];
            dp[j] = Math.min(path1Cost, path2Cost) + costs[j];
        }

        return Math.min(dp[0], dp[1]);
    }

    public int f_bu_optimised(int[] costs) {
        if(costs.length == 0) return 0;
        if(costs.length == 1) return costs[0];

        int next2 = 0;
        int next1 = costs[costs.length - 1];

        for(int j = costs.length - 2; j >= 0; j--) {
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