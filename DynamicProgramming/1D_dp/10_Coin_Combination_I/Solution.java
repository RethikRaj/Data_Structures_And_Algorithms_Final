/*
Consider a money system consisting of n coins. Each coin has a positive integer value. Your task is to calculate the number of distinct ways you can produce a money sum x using the available coins.
For example, if the coins are \{2,3,5\} and the desired sum is 9, there are 8 ways:
    2+2+5
    2+5+2
    5+2+2
    3+3+3
    2+2+2+3
    2+2+3+2
    2+3+2+2
    3+2+2+2
 */

import java.util.Arrays;
import java.util.Scanner;


// Note that 2 + 2 + 5 , 2 + 5 + 2 is counted separately.
public class Solution {
    static int mod = 1000000007;

    // returns the number of combinations(ways) to make up amount=`amt`
    public static long f(int[] coins, int amt) {
        if(amt == 0) return 1;

        long totalWays = 0;
        for(int i = 0 ; i < coins.length ; i++) {
            if(amt - coins[i] < 0) continue;

            totalWays = (totalWays % mod +  f(coins, amt - coins[i]) % mod ) % mod;
        }

        return totalWays % mod;
    }

    public static long f_td(int[] coins, int amt, long[] dp) {
        if(amt == 0) return 1;

        if(dp[amt] != -1) return dp[amt];

        long totalWays = 0;
        for(int i = 0 ; i < coins.length ; i++) {
            if(amt - coins[i] < 0) continue;

            totalWays = (totalWays % mod +  f_td(coins, amt - coins[i], dp) % mod  ) % mod;
        }

        return dp[amt] = totalWays % mod;
    }

    public static long f_bu(int[] coins, int amt) {
        long[] dp = new long[amt + 1];

        if(amt == 0) return 1;
        dp[0] = 1;

        for(int money = 1; money <= amt; money++) {
            long totalWays = 0;
            for(int i = 0 ; i < coins.length ; i++) {
                if(money - coins[i] < 0) continue;

                // totalWays = (totalWays % mod +  dp[money - coins[i]] % mod  ) % mod;

                totalWays = (totalWays +  dp[money - coins[i]]);
                if (totalWays >= mod) {
                    totalWays -= mod;
                }
            }

            dp[money] = totalWays % mod;
        }

        return dp[amt];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();

        int coins[] = new int[n];
        for(int i = 0; i < n ; i++) {
            coins[i] = sc.nextInt();
        }

        // long ans = f(coins, x);

        // long[] dp = new long[1000005];
        // Arrays.fill(dp, -1);
        // long ans = f_td(coins, x, dp);

        long ans = f_bu(coins, x);

        System.out.println(ans);

        // sc.close();
    }

}
