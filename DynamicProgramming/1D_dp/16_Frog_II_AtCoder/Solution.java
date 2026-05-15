import java.util.Scanner;

public class Solution {
    private static int f_bu(int[] heights, int k) {
        int n = heights.length;
        int[] dp = new int[100005];

        dp[n - 1] = 0;

        for(int idx = n - 2; idx >= 0; idx--) {
            int minCost = Integer.MAX_VALUE;
            for(int jump = 1; jump <= k; jump++) {
                if(idx + jump >= n) break;
                
                int currCost = Math.abs(heights[idx] - heights[idx + jump]) + dp[idx + jump];
                minCost = Math.min(minCost, currCost);
            }
            dp[idx] = minCost;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] heights = new int[n];
        for(int i = 0;i < n; i++) {
            heights[i] = sc.nextInt();
        }
        System.out.println(f_bu(heights, k));
        sc.close();
    }
}
