class Solution {
    // Using sort approach : TC : O(n logn)
    private int methodOne(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k]; // kth element from the last
    }

    // Using min heap : TC : O( (n*logn / n)  + klogn)
    private int methodTwo(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // max-heap

        for(int i = 0; i < nums.length;i++) pq.offer(nums[i]);

        // perform k-1 deletions
        for(int i = 0; i < k - 1; i++) pq.poll();

        return pq.peek();
    }

    // Using max heap : TC : O( (k*logk / k ) + (n-k)*logk)
    private int methodThree(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b); // min-heap
        
        // minheap of size k -> stores k largest elements seen so far
        for(int i = 0; i < k; i++) pq.offer(nums[i]);

        for(int i = k; i < nums.length;i++) {
            if(nums[i] > pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pq.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        // return methodOne(nums, k);

        // return methodTwo(nums, k);

        return methodThree(nums, k);
    }
}