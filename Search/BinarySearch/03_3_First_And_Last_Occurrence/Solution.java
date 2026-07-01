class Solution {
    public int firstPosition(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int ansIndex = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(arr[mid] == target) {
                ansIndex = mid;
                end = mid - 1;
            }else if(arr[mid] < target) {
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return ansIndex;
    }

    public int lastPosition(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int ansIndex = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(arr[mid] == target) {
                ansIndex = mid;
                start = mid + 1;
            }else if(arr[mid] < target) {
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return ansIndex;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] ans = {firstPosition(nums, target), lastPosition(nums, target)};
        return ans;
    }
}

// Method 2 : Lower Bound(finds the first index where nums[i] >= target) and Upper Boundfinds the first index where nums[i] > target