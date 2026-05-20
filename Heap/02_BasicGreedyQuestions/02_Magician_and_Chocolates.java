import java.util.* ;
import java.io.*; 
import java.util.ArrayList;

class Solution {
    private static int mod = 1000000007;


    // TC : O(k*logn + n*logn)
    public static int maximumChocolates(ArrayList<Integer> arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);

        for(int ele : arr) pq.offer(ele);  // O(n logn)

        int result = 0;

        while(k > 0 && !pq.isEmpty()) {
            int top = pq.poll(); // O(logn)

            result = (result % mod + top % mod) % mod;
            
            k--;
            pq.offer(top / 2); // O(logn)
        }

        return result;
    }
}