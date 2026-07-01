public class Solution {
    // Brute force
    public int bruteForce(int[] arr) {
        for(int i = 0; i < arr.length ; i++) {
            if(arr[i] == i) return i;
        }
        return arr.length;
    }

    public int binarySearchMethod(int[] arr) {
        if(arr[0] != 0) return 0;

        int ans = arr.length; // Why ? To handle `no elements are missing case`
        int start = 0;
        int end = arr.length - 1;
        while(start <= end) {
            int mid = start + (end - start) /2;

            if(arr[mid] == mid) start = mid + 1;
            else {
                ans = mid;
                end = mid - 1; // To search for smallest.
            }
        }
        return ans;
    }
}
