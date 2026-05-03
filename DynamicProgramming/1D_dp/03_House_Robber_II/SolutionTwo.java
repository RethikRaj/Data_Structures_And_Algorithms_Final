// Only main(driver) is changed nothing else

public class SolutionTwo {
    public int f(int[] nums, int start, int end) {
        if(start > end) return 0;
        if(start == end) return nums[start];
        if(start == end - 1) return Math.max(nums[start], nums[start + 1]);

        int includeAns = f(nums, start + 2 , end) + nums[start];
        int excludeAns = f(nums, start + 1, end) + 0;
        return Math.max(includeAns, excludeAns);
    }

    public int f_td(int[] nums, int start, int end, int[] dp) {
        if(start > end) return 0;
        if(start == end) return nums[start];
        if(start == end - 1) return Math.max(nums[start], nums[start + 1]);

        if(dp[start] != -1) return dp[start];

        int includeAns = f_td(nums, start + 2 , end, dp) + nums[start];
        int excludeAns = f_td(nums, start + 1, end, dp) + 0;
        return dp[start] = Math.max(includeAns, excludeAns);
    }

    public int f_bu(int[] nums, int start, int end) {
        int[] dp = new int[110];

        // Step 2.1 : Copy paste/convert base case (if needed)
        if(start > end) return 0;
        if(start == end) return nums[start];
        if(start == end - 1) return Math.max(nums[start], nums[start + 1]);
        // Step 2.2 : Analyse base case and Update dp array 
        dp[end] = nums[end];
        dp[end-1] = Math.max(nums[end-1], nums[end]);

        // Step 3
        for(int i = end - 2 ; i >= start ; i--) {
            int includeAns = dp[i + 2] + nums[i];
            int excludeAns = dp[i + 1] + 0;
            dp[i] = Math.max(includeAns, excludeAns);
        }
        return dp[start];
    }

    public int f_bu_optimised(int[] nums, int start, int end) {
        if(start > end) return 0;
        if(start == end) return nums[start];
        if(start == end - 1) return Math.max(nums[start], nums[start + 1]);

        int next2 = nums[end];
        int next1 = Math.max(nums[end-1], nums[end]);

        for(int i = end - 2  ; i >= start ; i--) {
            int includeAns = next2 + nums[i];
            int excludeAns = next1 + 0;
            int ans = Math.max(includeAns, excludeAns);

            next2 = next1;
            next1 = ans;
        }
        return next1;
    }

    // Main
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        // Recursion
        // return Math.max(f(nums, 0, n - 2), f(nums, 1,  n - 1));

        // Top - down
        // int[] dp = new int[110];
        // Arrays.fill(dp, -1);
        // int excludeLastHouse = f_td(nums, 0, n - 2, dp);
        // Arrays.fill(dp, - 1);
        // int excludeFirstHouse = f_td(nums, 1, n - 1, dp);
        // return Math.max(excludeFirstHouse, excludeLastHouse);

        // Bottom-up
        // return Math.max(f_bu(nums, 0, n - 2), f_bu(nums, 1, n-1));

        // Bottom-up-optimised
        return Math.max(f_bu_optimised(nums, 0, n - 2), f_bu_optimised(nums, 1, n - 1));
    }
}
