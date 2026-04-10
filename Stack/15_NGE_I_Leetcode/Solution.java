class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> mp = new HashMap<>();

        for(int i = 0 ; i < nums2.length ; i++) {
            mp.put(nums2[i], i); 
        }

        // Find NGE for all elements in nums2
        int[] nge = new int[nums2.length];
        Arrays.fill(nge , -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < nums2.length; i++) {
            while(!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                nge[stack.peek()] = nums2[i];
                stack.pop();
            }
            stack.push(i);
        }

        // Traverse nums1
        int[] ans = new int[nums1.length];
        for(int i = 0 ; i < nums1.length ; i++) {
            int j = mp.get(nums1[i]); // j = index of nums1[i] in nums2
            ans[i] = nge[j];
        }

        return ans;
    }
}