class Solution {
    private int sum;

    private boolean f(int[] nums, int idx, int sumOfSubsetOne) {
        if(idx == nums.length) return sumOfSubsetOne == sum - sumOfSubsetOne;

        return f(nums, idx + 1, sumOfSubsetOne + nums[idx]) || f(nums, idx + 1, sumOfSubsetOne);
    }

    private boolean f_td(int[] nums, int idx, int sumOfSubsetOne, Boolean[][] dp) {
        if(idx == nums.length) return sumOfSubsetOne == sum - sumOfSubsetOne;

        if(dp[idx][sumOfSubsetOne] != null) return dp[idx][sumOfSubsetOne];

        return dp[idx][sumOfSubsetOne] = f_td(nums, idx + 1, sumOfSubsetOne + nums[idx], dp) || f_td(nums, idx + 1, sumOfSubsetOne, dp);
    }

    private boolean f_bu(int[] nums) {
        boolean[][] dp = new boolean[205][100*205];
        for(boolean[] arr : dp) {
            Arrays.fill(arr, false);
        }

        // sumOfSubsetOne(col) can range from 0 to totalSum;
        for(int col = 0; col <= sum ; col++) {
            dp[nums.length][col] = (col == sum - col);
        }

        for(int idx = nums.length - 1; idx >= 0; idx--) {
            for(int sumOfSubsetOne = sum ; sumOfSubsetOne >= 0; sumOfSubsetOne--) {
                dp[idx][sumOfSubsetOne] = dp[idx + 1][sumOfSubsetOne + nums[idx]] || dp[idx + 1][sumOfSubsetOne];
            }
        }

        return dp[0][0];
    }

    private boolean f_bu_optimised(int[] nums) {
        boolean[] nextRow = new boolean[100*205];
        Arrays.fill(nextRow, false);

        // sumOfSubsetOne(col) can range from 0 to totalSum;
        for(int col = 0; col <= sum ; col++) {
            nextRow[col] = (col == sum - col);
        }

        for(int idx = nums.length - 1; idx >= 0; idx--) {
            // boolean[] currRow = new boolean[100*205];
            for(int sumOfSubsetOne = sum ; sumOfSubsetOne >= 0; sumOfSubsetOne--) {
                nextRow[sumOfSubsetOne] = nextRow[sumOfSubsetOne + nums[idx]] || nextRow[sumOfSubsetOne];
            }
            // nextRow = currRow;
        }

        return nextRow[0];
    }

    private boolean f_bu_optimised_2(int[] nums) {
        boolean[] dp = new boolean[100*205];
        Arrays.fill(dp, false);

        for(int col = 0; col <= sum; col++) {
            dp[col] = (col == sum - col);
        }

        for(int idx = nums.length - 1; idx >= 0; idx--) {
            // iterate LEFT to RIGHT so that dp[sumOfSubsetOne + nums[idx]]
            // is still from previous iteration when we read it
            for(int sumOfSubsetOne = 0; sumOfSubsetOne <= sum; sumOfSubsetOne++) {
                dp[sumOfSubsetOne] = dp[sumOfSubsetOne + nums[idx]] || dp[sumOfSubsetOne];
            }
        }

        return dp[0];
    }

    public boolean canPartition(int[] nums) {
        this.sum = 0;
        for(int i = 0; i < nums.length; i++) {
            this.sum += nums[i];
        }

        if(this.sum % 2 != 0) return false;

        // return f(nums, 0, 0);

        // top-down
        // Boolean[][] dp = new Boolean[205][100*205];
        // return f_td(nums, 0, 0, dp);

        // bottom-up
        // return f_bu(nums);

        // return f_bu_optimised(nums);

        return f_bu_optimised_2(nums);
    }
}