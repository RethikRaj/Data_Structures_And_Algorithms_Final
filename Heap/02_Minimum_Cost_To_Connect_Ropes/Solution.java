class Solution {
    public static int minCost(int[] arr) {
        if(arr.length == 1) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        
        for(int ele : arr) pq.offer(ele);
        
        int cost = 0;
        while(pq.size() > 1) {
            int top1 = pq.poll();
            int top2 = pq.poll();
            
            cost = cost + top1 + top2;
            
            pq.offer(top1 + top2);
        }
        
        return cost;
    }
}