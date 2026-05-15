/*
    1. Note that non-(+ve) integers meaning 0 is allowed => can't early exit if (target == 0)
    2. repitition is not allowed
 */
class Solution {
    
    private int f(int[] nums, int idx, int target) {
        if(idx == nums.length) {
            return target == 0 ? 1 : 0;
        }
        
        int ways = 0;
        if(target - nums[idx] >= 0) {
            ways += f(nums, idx + 1, target - nums[idx]);
        }
        ways += f(nums, idx + 1, target);
        
        return ways;
    }
    
    private int f_bu(int[] nums, int t) {
        int[][] dp = new int[1005][1005];
        
        // Step 2.1 
        // Step 2.2 
        dp[nums.length][0] = 1;
        for(int col = 1; col < dp[0].length;col++) {
            dp[nums.length][col] = 0;
        }
        
        for(int idx = nums.length - 1; idx >= 0; idx--) {
            for(int target = 0; target <= t ; target++) {
                int ways = 0;
                if(target - nums[idx] >= 0) {
                    ways += dp[idx + 1][target - nums[idx]];
                }
                ways += dp[idx + 1][target];
                
                dp[idx][target] = ways;
            }
        }
        
        return dp[0][t];
    }
    
    public int perfectSum(int[] nums, int target) {
        // return f(nums, 0, target);
        
        return f_bu(nums, target);
    }
}