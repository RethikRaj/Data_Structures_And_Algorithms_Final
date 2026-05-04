import java.util.Scanner;

public class SolutionWayTwo {
    public static final int MAX_COINS = 1000000;

    // return the whole dp
    public static Boolean[] f_bu(int c, int K, int L) {
        Boolean[] dp = new Boolean[MAX_COINS + 5];

        dp[1] = true;
        dp[K] = true;
        dp[L] = true;

        for (int coin = 2; coin <= c; coin++) {
            // Can take all coins in one move → W-position

            // Important : Do this check otherwise our set solution in base case will be overrided and then causes us problems.
            if (coin == K || coin == L) {
                continue;
            }

            int[] choices = {1, K, L};
            boolean allMovesLeadToWin = true;
            for (int choice : choices) {
                if (coin - choice < 0) continue;
                allMovesLeadToWin = allMovesLeadToWin && dp[coin - choice];
            }

            dp[coin] = !allMovesLeadToWin;
        }

        return dp;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int L = sc.nextInt();
        int M = sc.nextInt(); // number of queries

        int[] coins = new int[M];
        for (int i = 0; i < M; i++) {
            coins[i] = sc.nextInt();
        }

        // Way two : Instead of calling f or f_td or f_bu (m times). We can just call it once for the maximum value of input coins.
        // Now here also we have two choices , either we can find the maximum value of coin in coins i/p or we can just use the maximum value of coin in constraints
        // This we can do because for each coin value , K and L doesn't change.

        Boolean[] dp = f_bu(MAX_COINS, K, L);

        StringBuilder sb = new StringBuilder();
        for(int coin : coins) {
            boolean ans = dp[coin];
            sb.append(ans ? 'A' : 'B');
        }

        System.out.println(sb.toString());
    }
}
