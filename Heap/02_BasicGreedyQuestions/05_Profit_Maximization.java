// profit maximization : interviewBit
class Solution {
    public int solve(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        for(int ele : A) pq.offer(ele);
        
        int result = 0; // result denotes maxProfit
        
        while(B > 0 && !pq.isEmpty()) {
            int top = pq.poll();
            
            result += top;
            
            B--;
            if(top > 1) pq.offer(top - 1);
        }
        
        return result;
    }
}
