
// What is different from solution one .
// 1. Instead of returning both color and ,minSmoke from the function now we return just minimum smoke.
// 2. Color can be calculated internally bu summing the subarry from start to end and doing modulus of 100.

import java.util.Arrays;
import java.util.Scanner;

public class SolutionTwo {

    // Color of any subarray is always (sum of elements) % 100
    public static int g(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum = (sum % 100 + arr[i] % 100) % 100;
        }
        return sum % 100;
    }

    public static int f(int[] arr, int start, int end) {
        if (start == end) return 0;

        int minSmoke = Integer.MAX_VALUE;
        for (int k = start; k < end; k++) {
            int smoke1 = f(arr, start, k);
            int smoke2 = f(arr, k + 1, end);
            int color1 = g(arr, start, k);
            int color2 = g(arr, k + 1, end);
            int currSmoke = smoke1 + smoke2 + (color1 * color2);

            minSmoke = Math.min(minSmoke, currSmoke);
        }

        return minSmoke;
    }

    public static int f_td(int[] arr, int start, int end, int[][] dp) {
        if (start == end) return 0;

        if (dp[start][end] != -1) return dp[start][end];

        int minSmoke = Integer.MAX_VALUE;
        for (int k = start; k < end; k++) {
            int smoke1 = f_td(arr, start, k, dp);
            int smoke2 = f_td(arr, k + 1, end, dp);
            int color1 = g(arr, start, k);
            int color2 = g(arr, k + 1, end);
            int currSmoke = smoke1 + smoke2 + (color1 * color2);

            minSmoke = Math.min(minSmoke, currSmoke);
        }

        return dp[start][end] = minSmoke;
    }

    public static int f_bu(int[] arr) {
        int[][] dp = new int[105][105];

        if (arr.length == 1) return 0;
        if (arr.length == 2) return arr[0] * arr[1];

        for (int row = 0; row < arr.length; row++) {
            dp[row][row] = 0;
        }

        for (int start = arr.length - 2; start >= 0; start--) {
            for (int end = start + 1; end < arr.length; end++) {
                int minSmoke = Integer.MAX_VALUE;
                for (int k = start; k < end; k++) {
                    int color1 = g(arr, start, k);
                    int color2 = g(arr, k + 1, end);
                    int currSmoke = dp[start][k] + dp[k + 1][end] + (color1 * color2);

                    minSmoke = Math.min(minSmoke, currSmoke);
                }
                dp[start][end] = minSmoke;
            }
        }

        return dp[0][arr.length - 1];
    }

    // f_bu_2 refer matrix chain multiplication -> len based for loop
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            // Recursion
            // int ans = f(arr, 0, n - 1);

            // Top-down
            // int[][] dp = new int[105][105];
            // for (int[] row : dp) Arrays.fill(row, -1);
            // int ans = f_td(arr, 0, n - 1, dp);

            // Bottom-up
            int ans = f_bu(arr);

            System.out.println(ans);
        }
        sc.close();
    }
}