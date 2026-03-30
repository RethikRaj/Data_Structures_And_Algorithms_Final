class Solution {
    // Worst Brute Force  => TC:O(n^2), SC:O(1)
    // Loop through 0 to n and check whether tat number is present in array or not.

    // Brute Force : TC : O(nlogn), SC:O(1)
    // Step 1 : Sort the array.
    // Step 2 : Loop through the array and check at which index : arr[index] != index => missing num = index.
    public int bruteSort(int[] nums) {
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i) {
                return i;
            }
        }

        return nums.length;
    }

    // Better Approach  => TC: O(n), SC:O(n)
    // Step 1 : Use map or set or another array(this array must be implemented to be used like set) to store the elements
    // Step 2 : Loop through 0 to n and check whether tat number is present in map/set/anotherarray.

    // Best Approach : Sum approach => TC: O(n), SC:O(1)
    public int sumApproach(int[] nums) {
        int n = nums.length;
        // To handle overflow use long
        long actualSum = ((long)n * (n+1) ) / 2;

        long totalSum = 0;
        for(int i = 0; i < n;i++) {
            totalSum += nums[i];
        }
        return (int) (actualSum-totalSum);
    }

    // Best Approach : XOR approach => TC : O(n), SC:O(1)
    public int xorApproach(int[] nums) {
        int n = nums.length;
        int xor = 0;
        // Step 1 : Calculate xor of all elements from [0 to n]
        for(int i = 0; i <= n; i++) {
            xor = xor ^ i;
        }

        // Step 2 : xor the result with all elements of the array
        for(int i = 0; i < n;i++) {
            xor = xor ^ nums[i];
        }

        return xor;
    }


    public int missingNumber(int[] nums) {
        // return bruteSort(nums);
        return sumApproach(nums);
        // return xorApproach(nums);
    }
}
