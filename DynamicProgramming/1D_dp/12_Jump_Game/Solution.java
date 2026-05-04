class Solution {
    public static final int MAX_LENGTH = 10000;

    // Brute
    public boolean f(int[] nums, int idx) {
        if(idx >= nums.length) return false; // Instead of writing this base case i could have just skipped this recursive call inside loop by doing (idx + jump >= nums.length) break; -> see jump - game 2
        if(idx == nums.length - 1) return true;

        int num = nums[idx];
        boolean ans = false; // identity value of 'or' operation is false
        for(int jump = 1; jump <= num ; jump++) {
            ans = ans || f(nums, idx + jump);
        }

        return ans;
    }

    // Better
    public boolean f_bu(int[] nums) {
        Boolean[] dp = new Boolean[MAX_LENGTH + 5];

        // Step 2 
        dp[nums.length - 1] = true;

        // Step 3
        for(int idx = nums.length - 2; idx >= 0 ; idx--) {
            int num = nums[idx];
            boolean ans = false; // identity value of 'or' operation is false
            for(int jump = 1; jump <= num ; jump++) {
                if(idx + jump >= nums.length) break; // Instead of the base case (index >= nums.length),  i am handling it here.

                ans = ans || dp[idx + jump];
            }
            dp[idx] = ans;
        }

        return dp[0];
    }

    // Optimal
    public boolean greedy(int[] nums) {
        int maxReachIndexSoFar = 0;

        for(int i = 0 ; i < nums.length ; i++) {
            if(i > maxReachIndexSoFar) return false;

            maxReachIndexSoFar = Math.max(maxReachIndexSoFar, i + nums[i]);
        }

        return true;
    }

    public boolean canJump(int[] nums) {
        return f(nums, 0);

        // return f_bu(nums);

        // return greedy(nums);
    }
}