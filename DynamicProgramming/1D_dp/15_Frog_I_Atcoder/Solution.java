import java.util.Scanner;

public class Solution {
    private static int f_bu(int[] heights) {
        int n = heights.length;
        int[] dp = new int[100005];

        dp[n - 1] = 0;
        dp[n - 2] = Math.abs(heights[n - 2] - heights[n - 1]);

        for(int idx = n - 3; idx >= 0; idx--) {
            dp[idx] = Math.min(
                        Math.abs(heights[idx] - heights[idx + 1]) + dp[idx + 1],
                        Math.abs(heights[idx] - heights[idx + 2]) + dp[idx + 2]
                    );
        }

        return dp[0];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] heights = new int[n];
        for(int i = 0;i < n; i++) {
            heights[i] = sc.nextInt();
        }
        System.out.println(f_bu(heights));
        sc.close();
    }
}
