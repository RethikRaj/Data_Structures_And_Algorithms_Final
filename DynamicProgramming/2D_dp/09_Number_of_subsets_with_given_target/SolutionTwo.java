/*
    N-ary tree but unable to convert to DP - so tough.
 */

class SolutionTwo {
    private int count;
    
    private void g(int[] nums, int target, int start) {
        // Every subset is a valid subset
        if(target == 0) count += 1;
        
        for(int i = start ;  i < nums.length ; i++) {
            if(target - nums[i] >= 0) {
                g(nums, target - nums[i], i + 1);
            }
        }
    }
    
    
    public int perfectSum(int[] nums, int target) {
        count = 0;
        g(nums, target, 0);
        return count;
    }
}