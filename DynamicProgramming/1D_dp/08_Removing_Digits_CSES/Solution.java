import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    private static ArrayList<Integer> getDigitsExceptZero(int num) {
        ArrayList<Integer> digits = new ArrayList<>();

        while(num > 0) {
            int digit = num % 10;
            num = num / 10;

            if(digit == 0) continue;
            else digits.add(digit);
        }

        return digits;
    }

    public static int f(int n) {
        if(n == 0) return 0;
        if(n >= 1 && n <= 9) return 1;

        ArrayList<Integer> digits = getDigitsExceptZero(n);

        int minSteps = Integer.MAX_VALUE;
        for(int digit : digits) {
            minSteps = Math.min(minSteps, f(n - digit));
        }
        return 1 + minSteps; 
        //! minSteps will never remain Integer.MAX_VALUE because for any n > 0,
        // there is always at least one non-zero digit in n. That digit will be
        // included in 'digits', ensuring at least one recursive call f(n - digit).
        // Since (n - digit) < n, the recursion always progresses toward the base case (n == 0),
        // guaranteeing that minSteps gets updated with a valid value.
    }

    public static int f_td(int n, int[] dp) {
        if(n == 0) return 0;
        if(n >= 1 && n <= 9) return 1;

        if(dp[n] != -1) return dp[n];

        ArrayList<Integer> digits = getDigitsExceptZero(n);

        int minSteps = Integer.MAX_VALUE;
        for(int digit : digits) {
            minSteps = Math.min(minSteps, f_td(n - digit, dp));
        }
        return 1 + minSteps;
    }

    public static int f_bu(int n) {
        int[] dp = new int[1000005];

        // Step 2 :
        if(n == 0) return 0;
        if(n >= 1 && n <= 9) return 1;

        dp[0] = 0;
        for(int i = 0 ; i <= 9 ; i++) dp[i] = 1;

        // Step 3 : dp[i] depends on dp[i-digit] => Build solution from L to R
        for(int i = 10 ; i <= n ; i++) {
            ArrayList<Integer> digits = getDigitsExceptZero(i);
            int minSteps = Integer.MAX_VALUE;
            for(int digit : digits) {
                minSteps = Math.min(minSteps, dp[i - digit]);
            }
            dp[i] = 1 + minSteps;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Recursive
        // int ans = f(n);

        // Top-down
        // int[] dp = new int[1000005];
        // Arrays.fill(dp, -1);
        // int ans = f_td(n, dp);

        int ans = f_bu(n);

        System.out.println(ans);
    }
}