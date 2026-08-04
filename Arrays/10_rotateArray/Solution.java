class Solution {
    // Approach: brute : Rotate on by one . Do it for k times
    // Time: O(n*k) | Space: O(1)
    public void bruteRotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }

    
    // Approach: better
    // Time: O(n) | Space: O(n)
    public void betterRotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] temp = new int[n];

        // Place each element at its rotated position
        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }

        // Copy back to original array
        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }


    // Approach: better_variant
    // Time: O(n) | Space: O(k)
    public void betterRotateTwo(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        // store last k elements in reverse order (temp[0] = last elem, ...)
        int[] temp = new int[k];
        int z = 0;
        for (int i = n - 1; i > n - k - 1; i--) {
            temp[z] = nums[i];
            z++;
        }

        // shift first (n-k) elements right by k positions
        for (int i = n - k - 1; i >= 0; i--) {
            nums[(i + k) % n] = nums[i];
        }

        // place stored elements at the front (in correct order)
        z = k - 1;
        for (int i = 0; i < k; i++) {
            nums[i] = temp[z];
            z--;
        }
    }


    // Approach: optimal_reversal
    // Time: O(n) | Space: O(1)
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            // Swap elements
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            
            // Move pointers inward
            start++;
            end--;
        }
    }

    public void bestRotate(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        if(n == 1) return;
        reverse(arr, 0, n - k - 1);
        reverse(arr, n - k , n - 1);
        reverse(arr, 0, n-1);

    }

}