class Solution {
    // The below solution i just did whatever came to mind so it is a little length but has same logic as best solution
    public int better(int[] nums) {
        int startIndex = -1;
        int endIndex = -1; // Actually not needed just for clarity
        int maxConsecutiveOnes = Integer.MIN_VALUE;

        for(int i = 0 ; i < nums.length ; i++) {
            if(nums[i] == 1) {
                if(startIndex == -1) {
                    startIndex = i;
                }
            } else { // nums[i] == 0
                endIndex = i - 1;
                // Update maxConsecutiveOnes
                if(endIndex >= 0 && startIndex >= 0) {
                    int currConsecutiveOnes = endIndex - startIndex + 1;
                    maxConsecutiveOnes = Math.max(maxConsecutiveOnes, currConsecutiveOnes);
                }

                // Update start and end
                startIndex = -1;
                endIndex = -1;
            }
        }

        // Since there can be continuous 1 till last index
        if(startIndex != -1) {
            endIndex = nums.length - 1;
        }
        
        // Update maxConsecutiveOnes
        if(endIndex >= 0 && startIndex >= 0) {
            int currConsecutiveOnes = endIndex - startIndex + 1;
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, currConsecutiveOnes);
        }

        return maxConsecutiveOnes == Integer.MIN_VALUE ? 0 : maxConsecutiveOnes;
    }

    public int best(int[] nums) {
        int currCount = 0;
        int maxCount = 0;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                currCount += 1;
            } else {
                // Update max
                maxCount = Math.max(maxCount, currCount);
                // Reset currCount
                currCount = 0;
            }
        }

        //! Don't forget to update max again because we might have a case where the max consecutive ones occur at last. Example : [1,0,1,1,1,1]
        maxCount = Math.max(maxCount, currCount);
        return maxCount;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        // return brute(nums);
        return best(nums);
    }
}