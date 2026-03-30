public class Solution {
    public void brute(int[] nums) {
        // Step 1 : Move all non-zero elements 
        int nextNonZeroPos = 0;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                continue;
            } else {
                nums[nextNonZeroPos] = nums[i];
                nextNonZeroPos += 1;
            }
        }

        // Step 2 : Fill remaining indices with zero
        for(int i = nextNonZeroPos; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // Better
    public void better(int[] nums) {
        int nextNonZeroPos = 0;
        for(int i = 0; i < nums.length; i++) {
            if ( nums[i] != 0 ) {
                // swap nums[i] and nums[nextNonZeroPos]
                int temp = nums[i];
                nums[i] = nums[nextNonZeroPos];
                nums[nextNonZeroPos] = temp;
                // Increment nextNonZeroPos
                nextNonZeroPos += 1;
            }
        }
    }

    // Best=> Same as better but only do swapping when nextNonZeroPos != i => Example Case : [1,2,3,0,0,0]=> Avoids swapping for 1, 2, 3
    public void best(int[] nums) {
        int nextNonZeroPos = 0;
        for(int i = 0; i < nums.length; i++) {
            if ( nums[i] != 0 ) {
                if(i != nextNonZeroPos) {
                    nums[nextNonZeroPos] = nums[i];
                    nums[i] = 0;
                }
                // Increment nextNonZeroPos
                nextNonZeroPos += 1;
            }
        }
    }

}
