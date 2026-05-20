import java.util.*;

class Solution {
    public static int kthSmallest(int n,int k, int[]nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // max-heap
        
        // maxheap of size k -> stores k smallest elements seen so far
        for(int i = 0; i < k; i++) pq.offer(nums[i]);

        for(int i = k; i < nums.length;i++) {
            if(nums[i] < pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pq.peek();
    }
}
