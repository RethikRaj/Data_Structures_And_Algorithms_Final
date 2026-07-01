class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid = start + (end - start)/2;
            if(nums[mid] == target) return mid;

            // Case 1 : mid lies in Line A
            if(nums[mid] >= nums[start]) {
                // Now left part [start, mid] is sorted
                if(nums[start] <= target && target < nums[mid]) {
                    // discard right and move left
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }
            else { // case 2 : mid lies in Line B
                // Now right part [mid, end] is sorted
                if(nums[mid] < target && target <= nums[end]) {
                    // discard left and move right
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }
}