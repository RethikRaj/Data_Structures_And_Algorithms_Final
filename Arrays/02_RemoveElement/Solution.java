class Solution {
    public int removeElement(int[] nums, int val) {
        int nextCorrectPosition = 0;
        
        for(int i = 0; i < nums.length; ) {
            if(nums[i] == val) {
                i += 1;
            } else {
                nums[nextCorrectPosition] = nums[i];
                i += 1;
                nextCorrectPosition += 1;
            }
        }
        return nextCorrectPosition;
    }
}
