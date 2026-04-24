class Solution {
    public int f(int[] nums, int index) {
        if(index == nums.length - 1) return nums[nums.length - 1];
        if(index == nums.length - 2) return Math.max(nums[nums.length - 1], nums[nums.length - 2]);

        int includeAns = nums[index] + f(nums, index + 2);
        int excludeAns = 0 + f(nums, index + 1);
        return Math.max(includeAns, excludeAns);
    }

    public int f_td(int[] nums, int index, int[] dp) {
        if(index == nums.length - 1) return nums[nums.length - 1];
        if(index == nums.length - 2) return Math.max(nums[nums.length - 1], nums[nums.length - 2]);

        // Step 3 :
        if(dp[index] != -1) return dp[index];

        // Step 2 :
        int includeAns = nums[index] + f_td(nums, index + 2, dp);
        int excludeAns = 0 + f_td(nums, index + 1, dp);
        return dp[index] = Math.max(includeAns, excludeAns); 
    }

    public int f_bu(int[] nums) {
        int[] dp = new int[110];

        // Step 2.1 : Copy paste/convert base case (if needed) : Not that we don't have index parameter in method signature , therefore think smartly . (think exact meaning)
        // if(index == nums.length - 1) return nums[nums.length - 1]; => means length == 1 
        // if(index == nums.length - 2) return Math.max(nums[nums.length - 1], nums[nums.length - 2]);
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        // Step 2.2 : Analyse base case and Update dp array 
        dp[nums.length - 1] = nums[nums.length - 1];
        dp[nums.length - 2] = Math.max(nums[nums.length - 1], nums[nums.length - 2]);


        for(int index = nums.length - 3; index >= 0; index--) {
            int includeAns = nums[index] + dp[index + 2];
            int excludeAns = 0 + dp[index + 1];
            dp[index] = Math.max(includeAns, excludeAns);
        }
        return dp[0];
    }

    public int f_bu_optimised(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);

        int next2 = nums[nums.length - 1];
        int next1 = Math.max(nums[nums.length - 1], nums[nums.length - 2]);

        for(int index = nums.length - 3; index >= 0; index--) {
            int includeAns = next2 + nums[index];
            int excludeAns = next1 + 0;
            int ans = Math.max(includeAns, excludeAns);

            next2 = next1;
            next1 = ans;
        }
        return next1;

    }

    public int rob(int[] nums) {
        // return f(nums, 0);

        // int[] dp = new int[110];
        // Arrays.fill(dp, -1);
        // return f_td(nums, 0, dp);

        // return f_bu(nums);

        return f_bu_optimised(nums);    
    }
}