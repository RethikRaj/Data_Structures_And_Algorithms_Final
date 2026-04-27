import java.util.Arrays;
import java.util.Scanner;

public class SolutionToBEModified {
    public static final int MAX_N = 100000;

    public static int f(int i, int prevActivity,int[][] points) {
        if(i == points.length - 1) {
            if(prevActivity == 0) return Math.max(points[i][1], points[i][2]);
            else if(prevActivity == 1) return Math.max(points[i][0], points[i][2]);
            else if(prevActivity == 2) return Math.max(points[i][0], points[i][1]);
            else return Math.max(points[i][0], Math.max(points[i][1], points[i][2]));
        }

        if(prevActivity == 0) {
            return Math.max( 
                points[i][1] + f(i + 1, 1, points),
                points[i][2] + f(i + 1, 2, points)
            );
        } else if(prevActivity == 1) {
            return Math.max( 
                points[i][0] + f(i + 1, 0, points),
                points[i][2] + f(i + 1, 2, points)
            );
        } else if(prevActivity == 2) {
            return Math.max( 
                points[i][0] + f(i + 1, 0, points),
                points[i][1] + f(i + 1, 1, points)
            );
        } else {
            return Math.max( 
                points[i][0] + f(i + 1, 0, points),
                Math.max(
                    points[i][1] + f(i + 1, 1, points),
                    points[i][2] + f(i + 1, 2, points)
                )
            );
        }

    }

    // Technically dp is not 2dp since we would have only 3 cols. ( N 1-D arrays of size 3)
    public static int f_td(int i, int prevActivity, int[][] points, int[][] dp) {
        // Base case
        if(i == points.length - 1) {
            if(prevActivity == 0) return Math.max(points[points.length - 1][1], points[points.length - 1][2]);
            else if(prevActivity == 1) return Math.max(points[points.length - 1][0], points[points.length - 1][2]);
            else if(prevActivity == 2) return Math.max(points[points.length - 1][0], points[points.length - 1][1]);
        }

        // Step 3
        if(dp[i][prevActivity] != -1) return dp[i][prevActivity];

        // Step 2 
        if(prevActivity == 0) {
            dp[i][prevActivity] = Math.max( 
                points[i][1] + f_td(i + 1, 1, points, dp),
                points[i][2] + f_td(i + 1, 2, points, dp)
            );
        } else if(prevActivity == 1) {
            dp[i][prevActivity] = Math.max( 
                points[i][0] + f_td(i + 1, 0, points, dp),
                points[i][2] + f_td(i + 1, 2, points, dp)
            );
        } else if(prevActivity == 2) {
            dp[i][prevActivity] = Math.max( 
                points[i][0] + f_td(i + 1, 0, points, dp),
                points[i][1] + f_td(i + 1, 1, points, dp)
            );
        }

        return dp[i][prevActivity];
    }

    public static int f_bu(int[][] points) {
        int[][] dp = new int[MAX_N + 5][3];

        dp[points.length - 1][0] = Math.max(points[points.length - 1][1], points[points.length - 1][2]);
        dp[points.length - 1][1] = Math.max(points[points.length - 1][0], points[points.length - 1][2]);
        dp[points.length - 1][2] = Math.max(points[points.length - 1][0], points[points.length - 1][1]);

        // dp[i][prevActivity] depends on dp[i+1][!prevActivity]    
        for(int i = points.length - 2; i >= 1 ; i--) {
            dp[i][0] = Math.max( 
                        points[i][1] + dp[i+1][1],
                        points[i][2] + dp[i+1][2]
                    );

            dp[i][1] = Math.max( 
                        points[i][0] + dp[i + 1][0],
                        points[i][2] + dp[i + 1][2]
                    );

            dp[i][2] = Math.max( 
                        points[i][0] + dp[i + 1][0],
                        points[i][1] + dp[i + 1][1]
                    );
        }

        int ans = Math.max(
            points[0][0] + dp[1][0],
            Math.max(
                points[0][1] + dp[1][1],
                points[0][2] + dp[1][2]
            )
        );

        return ans;
    }

    public static int f_bu_optimised(int[][] points) {
        int[] nextRow = new int[3];

        nextRow[0] = Math.max(points[points.length - 1][1], points[points.length - 1][2]);
        nextRow[1] = Math.max(points[points.length - 1][0], points[points.length - 1][2]);
        nextRow[2] = Math.max(points[points.length - 1][0], points[points.length - 1][1]);

        for(int i = points.length - 2; i >= 1 ; i--) {
            int[] currRow = new int[3];
            currRow[0] = Math.max( 
                        points[i][1] + nextRow[1],
                        points[i][2] + nextRow[2]
                    );

            currRow[1] = Math.max( 
                        points[i][0] + nextRow[0],
                        points[i][2] + nextRow[2]
                    );

            currRow[2] = Math.max( 
                        points[i][0] + nextRow[0],
                        points[i][1] + nextRow[1]
                    );
            
            nextRow = currRow;
        }

        int ans = Math.max(
            points[0][0] + nextRow[0],
            Math.max(
                points[0][1] + nextRow[1],
                points[0][2] + nextRow[2]
            )
        );

        return ans;
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

        // Recursion
        // int ans = f(0, -1, points);

        // Top-down : 
        // -1 represent no prev activity is an invalid index , so we can just choose for day 0 ourselves and take maximum
        // int[][] dp = new int[MAX_N + 5][3];
        // for(int[] arr : dp) {
        //     Arrays.fill(arr, -1);
        // }

        // int ans = Math.max(
        //     points[0][0] + f_td(1, 0 , points, dp),
        //     Math.max(
        //         points[0][1] + f_td(1, 1, points, dp),
        //         points[0][2] + f_td(1, 2, points, dp)
        //     )
        // );

        // int ans = f_bu(points);
        int ans = f_bu_optimised(points);

        System.out.println(ans);

    }
}