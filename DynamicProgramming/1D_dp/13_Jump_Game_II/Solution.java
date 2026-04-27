class Solution {
    // brute
    public long f(int[] nums, int idx) {
        if(idx == nums.length - 1) return 0;

        int num = nums[idx];
        long minSteps = Integer.MAX_VALUE;
        for(int jump = 1 ; jump <= num ; jump++) {
            if(idx + jump >= nums.length) break;

            minSteps = Math.min(minSteps, f(nums, idx + jump));
        }

        return 1 + minSteps;
    }

    // better
    public long f_bu(int[] nums) {
        long[] dp = new long[10005]; 

        // step 2.1
        if(nums.length == 1) return 0;
        // step 2.2
        dp[nums.length - 1] = 0;

        // Step 3
        for(int idx = nums.length - 2; idx >= 0; idx--) {
            int num = nums[idx];
            long minSteps = Integer.MAX_VALUE;
            for(int jump = 1 ; jump <= num ; jump++) {
                if(idx + jump >= nums.length) break;

                minSteps = Math.min(minSteps, dp[idx + jump]);
            }

            dp[idx] = 1 + minSteps;
        }

        return dp[0];
    }

    // greedy
    // TC : O(n), SC : O(n)
    public int greedy(int[] nums) {
        
        int jumps = 0;
        int start = 0;
        int end = 0;

        while(end < nums.length - 1) {
            // Find the farthest i can go with jumps available in the range [start, end]
            int farthest = Integer.MIN_VALUE;
            for(int i = start ; i <= end ; i++) {
                farthest = Math.max(farthest , i + nums[i]);
            }

            jumps += 1;
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }



    public int jump(int[] nums) {
        // return (int)f(nums, 0);

        // return (int)f_bu(nums);

        return greedy(nums);
    }
}
