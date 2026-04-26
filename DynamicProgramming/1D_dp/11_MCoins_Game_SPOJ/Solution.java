import java.util.Scanner;

public class Solution {

    public static final int MAX_COINS = 1000000;

    // Returns true if the current player WINS with coins=coins (W-state)
    // Returns false if the current player LOSES with coins=coins (L-state)
    public static boolean f(int coins, int K, int L) {

        // Base case: if we can take all coins in one move, current player wins , winning state
        if (coins == 1 || coins == K || coins == L) return true;

        int[] choices = {1, K, L};

        // A position is L if ALL moves lead to W-state for the opponent
        boolean allMovesLeadToWin = true; // Identity for AND is true
        for (int choice : choices) {
            // coins - choice can never be 0 here because those cases are caught above
            if (coins - choice < 0) continue;
            allMovesLeadToWin = allMovesLeadToWin && f(coins - choice, K, L);
        }

        // If all moves lead to W for opponent → current position is L → return false
        // If at least one move leads to L for opponent → current position is W → return true
        return !allMovesLeadToWin;
    }

    public static boolean f_td(int coins, int K, int L, Boolean[] dp) {
        if (coins == 1 || coins == K || coins == L) return true;

        if (dp[coins] != null) return dp[coins];

        int[] choices = {1, K, L};
        boolean allMovesLeadToWin = true;
        for (int choice : choices) {
            if (coins - choice < 0) continue;
            allMovesLeadToWin = allMovesLeadToWin && f_td(coins - choice, K, L, dp);
        }

        return dp[coins] = !allMovesLeadToWin;
    }

    public static boolean f_bu(int c, int K, int L) {
        Boolean[] dp = new Boolean[1000005];

        if (c == 1 || c == K || c == L) return true;

        dp[1] = true;
        dp[K] = true;
        dp[L] = true;

        for (int coins = 2; coins <= c; coins++) {

            // Can take all coins in one move → W-position
            if (coins == K || coins == L) {
                dp[coins] = true;
                continue;
            }

            int[] choices = {1, K, L};
            boolean allMovesLeadToWin = true;
            for (int choice : choices) {
                if (coins - choice < 0) continue;
                allMovesLeadToWin = allMovesLeadToWin && dp[coins - choice];
            }

            dp[coins] = !allMovesLeadToWin;
        }

        return dp[c];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int L = sc.nextInt();
        int M = sc.nextInt(); // number of queries

        int[] coinsArr = new int[M];
        for (int i = 0; i < M; i++) {
            coinsArr[i] = sc.nextInt();
        }

        // Way 1 : Calling the f or f_td or f_bu `m` times 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            // // Top - down
            // Boolean[] dp = new Boolean[MAX_COINS + 5]; // default value is NULL . Therefore we can distinguish between not computed yet and computed = false / true.
            // boolean ans = f_td(coinsArr[i], K, L, dp);

            // Bottom-up 
            boolean ans = f_bu(coinsArr[i], K, L);

            sb.append(ans ? 'A' : 'B'); // A = Asen wins, B = Boyan wins
        }
        System.out.println(sb.toString());



        sc.close();
    }

}