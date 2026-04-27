import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static final int MAX_N = 100000;

    public static int f(int i, int act, int[][] points) {
        if(i == points.length - 1) return points[points.length - 1][act];
        
        // Perform ith activity on current day 
        int pointsGained = points[i][act];

        // For the next day , we have two options 
        int bestOption = Integer.MIN_VALUE;
        for(int activity = 0 ; activity < 3 ; activity++) {
            if(activity == act) continue;

            bestOption = Math.max(bestOption , f(i + 1, activity, points));
        }

        return pointsGained+ bestOption;
    }

    public static int f_td(int i, int act, int[][] points, int[][] dp) {
        // Base case
        if(i == points.length - 1) return points[points.length - 1][act];

        if(dp[i][act] != -1) return dp[i][act]; // step 3

        // Perform ith activity on current day 
        int pointsGained = points[i][act];

        // For the next day , we have two options 
        int bestOption = Integer.MIN_VALUE;
        for(int activity = 0 ; activity < 3 ; activity++) {
            if(activity == act) continue;

            bestOption = Math.max(bestOption , f_td(i + 1, activity, points, dp));
        }

        return dp[i][act] = pointsGained+ bestOption; // step 2
    }

    public static int f_bu(int[][] points) {
        int[][] dp = new int[MAX_N + 5][3];

        // Step 2.1
        if(points.length == 1) return Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        // Step 2.2
        for(int col = 0 ; col < 3; col++) {
            dp[points.length - 1][col] = points[points.length - 1][col];
        } 

        // Step 3
        for(int i = points.length - 2; i >= 0 ; i--) {
            for(int act = 0 ; act < 3 ; act++) {
                int pointsGained = points[i][act];

                // For the next day , we have two options 
                int bestOption = Integer.MIN_VALUE;
                for(int activity = 0 ; activity < 3 ; activity++) {
                    if(activity == act) continue;

                    bestOption = Math.max(bestOption , dp[i + 1][activity]);
                }

                dp[i][act] = pointsGained+ bestOption;
            }
        }

        return Math.max(dp[0][0], Math.max(dp[0][1], dp[0][2]));
    }

    // Sc : O(1) -> even though 1D array but only 3 size => 3 variables
    public static int f_bu_optimised(int[][] points) {
        int[] prevRow = new int[3];

        // Step 2.1
        if(points.length == 1) return Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        // Step 2.2
        for(int col = 0 ; col < 3; col++) {
            prevRow[col] = points[points.length - 1][col];
        }

        // Step 3
        for(int i = points.length - 2; i >= 0 ; i--) {
            int[] currRow = new int[3];
            for(int act = 0 ; act < 3 ; act++) {
                int pointsGained = points[i][act];

                // For the next day , we have two options 
                int bestOption = Integer.MIN_VALUE;
                for(int activity = 0 ; activity < 3 ; activity++) {
                    if(activity == act) continue;

                    bestOption = Math.max(bestOption , prevRow[activity]);
                }

                currRow[act] = pointsGained+ bestOption;
            }

            prevRow = currRow;
        }

        return Math.max(prevRow[0], Math.max(prevRow[1], prevRow[2]));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] points = new int[N][3];

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0; j < 3; j++) {
                points[i][j] = sc.nextInt();
            }
        }

        // int ans = Math.max(f(0, 0, points) , Math.max( f(0, 1, points) , f(0, 2, points)));

        // int[][] dp = new int[MAX_N + 5][3];
        // for(int[] arr : dp) {
        //     Arrays.fill(arr, -1);
        // }

        // int ans = Math.max(f_td(0, 0, points, dp), Math.max( f_td(0, 1, points, dp) , f_td(0, 2, points, dp)));

        // int ans = f_bu(points);

        int ans = f_bu_optimised(points);

        System.out.println(ans);
    }
}
