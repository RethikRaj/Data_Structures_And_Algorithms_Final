class Solution {
    public static long sumBetweenTwoKth(long nums[], long N, long K1, long K2) {
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> Long.compare(b, a)); // max-heap
        
        // maxheap of size k2 -> stores k2 smallest elements seen so far
        for(int i = 0; i < K2; i++) pq.offer(nums[i]);
        
        for(int i = (int)K2; i < nums.length;i++) {
            if(nums[i] < pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        
        long sum = 0; // sum of elements in range (k1, k2).
        pq.poll(); // pop the k2th smallest
        while(pq.size() > K1) {
            sum += pq.poll();
        }        
        
        return sum;
    }
}