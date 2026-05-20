class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);

        for(int ele : gifts) pq.offer(ele);

        while(k > 0 && !pq.isEmpty()) {
            int top = pq.poll();

            k--;
            pq.offer( (int) Math.floor(Math.sqrt(top)) );
        }

        long result = 0;
        while(!pq.isEmpty()) result += pq.poll();

        return result;
    }
}