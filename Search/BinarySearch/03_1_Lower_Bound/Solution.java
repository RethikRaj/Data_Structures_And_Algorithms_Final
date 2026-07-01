class Solution {
    int lowerBound(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int ansIndex = arr.length;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(arr[mid] >= target) {
                ansIndex = mid;
                end = mid - 1; // discard right and move left
            }else {
                start = mid + 1; // discard left and move right
            }
        }
        return ansIndex;
    }
}
