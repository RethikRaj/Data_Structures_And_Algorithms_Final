class Solution {
    // Brute Force : Explore all possible solutions 

    // Optimal : Pick the minimum element always and negate it
    // TC : O(n logn + k log n + n logn)
    public int optimal(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min-heap

        // Step 1 
        for(int num : nums) {
            pq.offer(num);
        }

        // Step 2
        for(int i = 0 ; i < k ; i++) {
            int minEle = pq.poll();
            pq.offer(-1*minEle);
        }

        // Step 3
        int sum = 0;
        while(!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        return optimal(nums, k);
    }
}
