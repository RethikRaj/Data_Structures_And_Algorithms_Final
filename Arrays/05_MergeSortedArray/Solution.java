import java.util.Arrays;

class Solution {
    // TC : O( (n) + (m+n)log(m+n)) , SC: O(1)
    public void brute(int[] nums1, int m, int[] nums2, int n) {
        // Copy the n elements of nums2 into nums1 starting from index :[m, m+n-1]
        for(int i = m; i < m+n;i++) {
            nums1[i] = nums2[i-m];
        }

        // Sort the nums1 array
        Arrays.sort(nums1);
    }

    // TC : O(m+n) , SC: O(m+n)
    public void betterOne(int[] nums1, int m, int[] nums2, int n) {
        int[] mergedArray = new int[m+n];
        // Step 1 : merge sorted array
        int p1 = 0;
        int p2 = 0;
        int z = 0;

        while (p1 < m && p2 < n) {
            if (nums1[p1] < nums2[p2]) {
                mergedArray[z] = nums1[p1];
                p1 += 1;
            } else {
                mergedArray[z] = nums2[p2];
                p2 += 1;
            }
            z += 1;
        }

        while (p1 < m) {
            mergedArray[z] = nums1[p1];
            p1 += 1;
            z += 1;
        }

        while (p2 < n) {
            mergedArray[z] = nums2[p2];
            p2 += 1;
            z += 1;
        }

        // Step 2: Copy mergedArray to nums1
        for(int k = 0; k < mergedArray.length ; k++) {
            nums1[k] = mergedArray[k];
        }
    }

    // betterTwo : Instead of creating an array of size m + n , we can just create an copy of m elements of nums1 array and then store the mergedArray directly in nums1
    // TC: O(m+n) . SC : O(m)

    // Optimal Solution : TC : O(m+n), SC : O(1)
    // Do merging but do it in nums1 array from reverse direction
    public void optimal(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;

        int p1 = m-1;
        int p2 = n-1;

        // Find largest element and place at last
        while(p1 >= 0 && p2 >= 0) {
            if(nums1[p1] < nums2[p2]) {
                nums1[i] = nums2[p2];
                p2 -= 1;
            }else{
                nums1[i] = nums1[p1];
                p1 -= 1;
            }
            i -= 1;
        }

        while(p1 >= 0) {
            nums1[i] = nums1[p1];
            p1 -= 1;
            i -= 1;
        }

        while(p2 >= 0) {
            nums1[i] = nums2[p2];
            p2 -= 1;
            i -= 1;
        }
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // brute(nums1, m, nums2, n);
        // betterOne(nums1, m, nums2, n);
        optimal(nums1, m, nums2, n);
    }
}
