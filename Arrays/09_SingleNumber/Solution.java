class Solution {
    // Brute Force : TC : O(n^2), SC : O(1)
    // Step 1 : Loop through each element
    // Step 2 : For each element, count its occurrences in the array
    // Step 3 : If count == 1, return that element

    // Better Approach : TC : O(n), SC : O(n)
    // Step 1 : Use map to store (element -> count)
    // Step 2 : Iterate through map and find the element with single occurence.

    // Best Approach : TC : O(n), SC:O(1)
    public int xorApproach(int[] nums) {
        int xor = 0;
        for(int i = 0; i < nums.length; i++) {
            xor = xor ^ nums[i];
        }
        return xor;
    }

    public int singleNumber(int[] nums) {
        return xorApproach(nums);
    }
}
