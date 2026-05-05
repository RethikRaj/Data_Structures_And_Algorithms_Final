class Solution {
    private boolean f(int[] nums, int idx, int remSum) {
        // base case
        if(remSum < 0) return false;
        if(remSum == 0) return true; // early exit
        if(idx == nums.length) return false;

        return f(nums, idx + 1, remSum - nums[idx]) || f(nums, idx + 1, remSum);
    }

    private boolean f_td(int[] nums, int idx, int remSum, Boolean[][] dp) {
        // base case
        if(remSum < 0) return false;
        if(remSum == 0) return true; // early exit
        if(idx == nums.length) return false;

        if(dp[idx][remSum] != null) return dp[idx][remSum];

        return dp[idx][remSum] = f_td(nums, idx + 1, remSum - nums[idx], dp) || f_td(nums, idx + 1, remSum, dp);
    }

    private boolean f_bu(int[] nums, int target) {
        boolean[][] dp = new boolean[205][target + 1];

        // - idx == nums.length , then false 
        // for(int col = 0; col < target + 1; col++) dp[nums.length][col] = false; by default boolean array values is false
        // - remSum == 0 => then true
        for(int row = 0; row < dp.length ; row++) {
            dp[row][0] = true;
        }

        for(int idx = nums.length - 1; idx >= 0; idx--) {
            for(int remSum = 1 ; remSum < target + 1 ; remSum++) {
                dp[idx][remSum] = dp[idx+1][remSum];  // excldude path always valid
                if(remSum - nums[idx] >= 0) {         // include path is valid only if remSum-nums[idx] >= 0
                    dp[idx][remSum] = dp[idx][remSum] || dp[idx+1][remSum - nums[idx]];
                }
            }
        }

        return dp[0][target];
    }

    private boolean f_bu_optimised(int[] nums, int target) {
        boolean[] nextRow = new boolean[target + 1];

        nextRow[0] = true;
        
        for(int idx = nums.length - 1; idx >= 0; idx--) {
            boolean[] currRow = new boolean[target + 1];
            for(int remSum = 1 ; remSum < target + 1; remSum++) {
                currRow[remSum] = nextRow[remSum];  // excldude path always valid
                if(remSum - nums[idx] >= 0) {         // include path is valid only if remSum-nums[idx] >= 0
                    currRow[remSum] = currRow[remSum] || nextRow[remSum - nums[idx]];
                }
            }
            nextRow = currRow;
        }

        return nextRow[target];
    }

    private boolean f_bu_optimised_2(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];

        dp[0] = true;
        
        for(int idx = nums.length - 1; idx >= 0; idx--) {
            boolean[] currRow = new boolean[target + 1];
            for(int remSum = target ; remSum >= 0 ; remSum--) {
                dp[remSum] = dp[remSum];  // excldude path always valid
                if(remSum - nums[idx] >= 0) {         // include path is valid only if remSum-nums[idx] >= 0
                    dp[remSum] = dp[remSum] || dp[remSum - nums[idx]];
                }
            }
        }

        return dp[target];
    
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) sum += num;

        if(sum % 2 != 0) return false;

        int remSum = sum / 2;

        // recursion
        // return f(nums, 0, remSum);

        // top-down
        // Boolean[][] dp = new Boolean[205][remSum + 1]; // i could have done (100 * 205 / 2) also but just i didnt do, you can.
        // return f_td(nums, 0, remSum, dp);

        // bottom-up
        // return f_bu(nums, remSum);

        // return f_bu_optimised(nums, remSum);

        return f_bu_optimised_2(nums, remSum);
    }
}