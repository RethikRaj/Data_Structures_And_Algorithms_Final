class Solution {
    public int removeDuplicates(int[] nums) {
        // Pointer to the position where the next unique element should be placed
        int nextUniqueIndex = 1;
        
        // Start from index 1 since the first element is always unique
        for (int i = 1; i < nums.length; ) {
            
            // If current element is a duplicate, skip it
            if (nums[i] == nums[nextUniqueIndex - 1]) {
                i += 1;
            } else {
                // Found a new unique element — place it at nextUniqueIndex
                nums[nextUniqueIndex] = nums[i];
                i += 1;
                nextUniqueIndex += 1;
            }
        }
        
        // nextUniqueIndex represents the count of unique elements
        return nextUniqueIndex;
    }
}