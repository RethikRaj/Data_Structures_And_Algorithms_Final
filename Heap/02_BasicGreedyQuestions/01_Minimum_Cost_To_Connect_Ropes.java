class Solution {
    // TC : O(nlogn + n * (3logn)) , SC : O(n)
    public static int minCost(int[] arr) {
        if(arr.length == 1) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        
        // In below step we are inserting elements one by one and building heap from scratch => TC : O(n logn)
        // If we use build heap using heapify , then we can reduce it to O(n)
        for(int ele : arr) pq.offer(ele);
        
        int cost = 0;
        while(pq.size() > 1) {
            int top1 = pq.poll(); // O(logn)
            int top2 = pq.poll(); // O(logn)
            
            cost = cost + top1 + top2;
            
            pq.offer(top1 + top2); // O(logn)
        }
        
        return cost;
    }
}