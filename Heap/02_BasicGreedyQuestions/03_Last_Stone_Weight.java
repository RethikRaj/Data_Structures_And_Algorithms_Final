class Solution {

    // TC : O(n logn + (n-1)*2logn)
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);

        for(int ele : stones) pq.offer(ele);

        while(pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();

            if(first != second) {
                pq.offer(first - second);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}