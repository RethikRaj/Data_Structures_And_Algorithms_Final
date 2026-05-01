import java.util.Arrays;
import java.util.Scanner;

class Info{
    int smoke;
    int color; // color of resultant mixture
    Info(int smoke, int color) {
        this.smoke = smoke;
        this.color = color;
    }
}

public class Solution {

    public static Info f(int[] arr, int start, int end) {
        if(start == end) return new Info(0, arr[start]);
        if(start + 1 == end) return new Info(arr[start]*arr[end], (arr[start] + arr[end]) % 100);

        int minSmoke = Integer.MAX_VALUE;
        int color = -1;
        for(int k = start; k < end ; k++) {
            Info temp1 = f(arr, start, k);
            Info temp2 = f(arr, k + 1, end);
            int currSmoke = temp1.smoke + temp2.smoke + (temp1.color * temp2.color);
            int currColor = (temp1.color + temp2.color) % 100;

            if(minSmoke > currSmoke) {
                minSmoke = currSmoke;
                color = currColor;
            }
        }

        return new Info(minSmoke, color);
    }

    public static Info f_td(int[] arr, int start, int end, Info[][] dp) {
        if(start == end) return new Info(0, arr[start]);
        // if(start + 1 == end) return new Info(arr[start]*arr[end], (arr[start] + arr[end]) % 100); // this base case is extra if want can use

        if(dp[start][end].color != -1) return dp[start][end];

        int minSmoke = Integer.MAX_VALUE;
        int color = -1;
        for(int k = start; k < end ; k++) {
            Info temp1 = f_td(arr, start, k, dp);
            Info temp2 = f_td(arr, k + 1, end, dp);
            int currSmoke = temp1.smoke + temp2.smoke + (temp1.color * temp2.color);
            int currColor = (temp1.color + temp2.color) % 100;

            if(minSmoke > currSmoke) {
                minSmoke = currSmoke;
                color = currColor;
            }
        }

        return dp[start][end] = new Info(minSmoke, color);
    }

    public static Info f_bu(int[] arr) {
        Info[][] dp = new Info[105][105]; // i can make it as arr.length - 1 * arr.length - 1 also.

        // Step 2.1
        if(arr.length == 1) return new Info(0, arr[0]);
        if(arr.length == 2) return new Info(arr[0]*arr[1], (arr[0] + arr[1]) % 100);
        // Step 2.2
        for(int row = 0; row < arr.length; row++){
            dp[row][row] = new Info(0, arr[row]);
        }

        // Step 3:
        for(int start = arr.length - 2; start >= 0; start--) {
            for(int end = start + 1 ; end < arr.length ; end++) {
                int minSmoke = Integer.MAX_VALUE;
                int color = -1;
                for(int k = start; k < end ; k++) {
                    Info temp1 = dp[start][k];
                    Info temp2 = dp[k + 1][end];
                    int currSmoke = temp1.smoke + temp2.smoke + (temp1.color * temp2.color);
                    int currColor = (temp1.color + temp2.color) % 100;

                    if(minSmoke > currSmoke) {
                        minSmoke = currSmoke;
                        color = currColor;
                    }
                }

                dp[start][end] = new Info(minSmoke, color);
            }
        }

        return dp[0][arr.length - 1];
    }

    // f_bu_2 see matrix chain multiplication.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNextInt()){
            int n = sc.nextInt();

            int[] arr = new int[n];

            for(int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            // Recursion
            // int ans = f(arr, 0, n -1).smoke;

            // Top-down
            // Info[][] dp = new Info[105][105];
            // for(Info[] row : dp) {
            //     Arrays.fill(row, new Info(0, -1));
            // }
            // int ans = f_td(arr, 0, arr.length-1, dp).smoke;

            // bottom-up
            int ans = f_bu(arr).smoke;
            
            System.out.println(ans);
        }
        sc.close();
    }
}
