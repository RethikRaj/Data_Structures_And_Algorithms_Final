class HouseRobberOne {
    public int f(int[] nums, int index) {
        if(index >= nums.length) return 0;
        
        // Include => Rob this house
        int includeAns = nums[index] + f(nums, index + 2);
        // Exclude => Don't rob this house
        int excludeAns = 0 + f(nums, index + 1);

        return Math.max(includeAns, excludeAns);
    }

    public int rob(int[] nums) {
        return f(nums, 0);
    }
}
