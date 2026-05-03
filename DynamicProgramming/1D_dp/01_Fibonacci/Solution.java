import java.util.Arrays;

public class Solution {

    // Brute (Recursive solution)
    public static int f(int n) {
        // base case
        if (n == 0 || n == 1) return n;
        
        // 1 case me solve remaining recursion will solve
        int recAns = f(n - 1) + f(n - 2);
        return recAns;
    }

    // Memoization
    public static int f(int n, int[] dp) {
        // base case
        if (n == 0 || n == 1) {
            return n;
        }

        // step3 : if ans already exists then return it (subproblem is already computed => don't compute again)
        if (dp[n] != -1) {
            return dp[n];
        }

        // step2 : store recAns in dp & return (store the answer of the subproblem when encountered first time and return it)
        dp[n] = f(n-1, dp) + f(n-2, dp);
        return dp[n];
    }

    // Tabulation
    public static int f_bu(int n) {
        // step1 : Create dp array
        int[] dp = new int[100005];

        // step 2 : analyse base case and copy paste base case(if needed) and update dp
        dp[0] = 0;
        dp[1] = 1;

        // step 3 : 
        // Understand that dp[i] = dp[i-1] + dp[i-2] => For calculating current subproblem we need to calculate all previous subproblem.
        // check parameter range and reverse it and run a loop
        // recursive approach : n --> 0
        // bottom up approach : 0 ---> n
        for (int i = 2; i <= n; i++) {
            // copy - paste logic -> dp[n] = f(n-1, dp) + f(n-2, dp);
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Space optimised
    public static int f_bu_optimised(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int prev2 = 0;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int ans = prev2 + prev1;
            prev2 = prev1;
            prev1 = ans;
        }
        return prev1;
    }

    public static void main(String[] args) {
        // Normal recursion
        // System.out.println(f(10));

        // Memoization
        // step 1 : 1D dp -> because only n parameter changes during each rec call
        // what is the size of 1D dp array ? -> shld store from 0 to n -> size : n+1
        // But in most of the problems we don't need to care about the size , just take the largest value of n given in constraint.
        int[] dp = new int[100005];
        Arrays.fill(dp, -1);
        System.out.println(f(10, dp));

        // Tabulation
        System.out.println(f_bu(10));

        // Space optimised
        System.out.println(f_bu_optimised(10));
    }
}