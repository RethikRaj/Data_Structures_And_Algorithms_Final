class KthLargest {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);  // always contains k largest elements seen so far
    private int copyk ;

    public KthLargest(int k, int[] nums) {
        for(int i = 0 ; i < k && i < nums.length;i++) minHeap.offer(nums[i]);

        for(int i = k; i < nums.length;i++) {
            if(minHeap.peek() < nums[i]) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }

        copyk = k;
    }
    
    public int add(int val) {
        if(minHeap.size() < copyk) { // just insert because we haven;t got k elements yet, then how to find kth largest
            minHeap.offer(val);
            return minHeap.peek();
        }

        // minHeap.size() == k -> need to compare and then insert
        if(minHeap.peek() < val) {
            minHeap.poll();
            minHeap.offer(val);
        }

        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */