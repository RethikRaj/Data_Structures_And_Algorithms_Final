public class HouseRobberTwo {
    // Way 1
    public int f(int[] nums, int startIndex, int endIndex) {
        if(startIndex > endIndex) return 0;

        // Include => Rob this house
        int includeAns = nums[startIndex] + f(nums, startIndex + 2, endIndex);
        // Exclude => Don't rob this house
        int excludeAns = 0 + f(nums, startIndex + 1, endIndex);

        return Math.max(includeAns, excludeAns);
    }

    // Way 2
    public int f(int[] nums, int index, boolean isFirstRobbed) {
        if(index >= nums.length) return 0;

        if(index == nums.length - 1) {
            if(isFirstRobbed) {
                return 0;
            } else{
                return nums[index];
            }
        }
        
        // Include => Rob this house
        int includeAns = nums[index] + f(nums, index + 2, isFirstRobbed);
        // Exclude => Don't rob this house
        int excludeAns = 0 + f(nums, index + 1, isFirstRobbed);

        return Math.max(includeAns, excludeAns);
    }

    public int rob(int[] nums) {
        // Way 1 : 
        // First House robbed
        int ans1 = nums[0] + f(nums, 2, nums.length - 2);
        // First House not robbed
        int ans2 = 0 + f(nums, 1, nums.length-1);
        return Math.max(ans1, ans2);

        // Way 2 : 
        // First House robbed
        // int ans1 = nums[0] + f(nums, 2, true);
        // First House not robbed
        // int ans2 = 0 + f(nums, 1, false);
        // return Math.max(ans1, ans2);
    }
}
