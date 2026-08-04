class Solution {
    // Better :
    // TC : O(n), SC : O(n)
    public int[] better(int[] nums) {
        int n = nums.length;
        int[] prefixMultiplication = new int[n];
        int[] suffixMultiplication = new int[n];

        prefixMultiplication[0] = 1;
        for(int i = 1; i < n; i++) prefixMultiplication[i] = nums[i-1] * prefixMultiplication[i-1];

        suffixMultiplication[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--) suffixMultiplication[i] = nums[i+1] * suffixMultiplication[i+1];

        int[] ans = new int[n];

        for(int i = 0 ; i < n; i++) {
            ans[i] = prefixMultiplication[i] * suffixMultiplication[i];
        }

        return ans;
    }

    // TC : O(n), SC:O(1)
    public int[] best(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // First pass: store prefix products in result
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Second pass: multiply suffix products in-place
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
    }
}