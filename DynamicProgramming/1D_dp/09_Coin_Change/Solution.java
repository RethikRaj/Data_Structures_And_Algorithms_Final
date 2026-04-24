class Solution {
    public int f(int[] coins, int amt) {
        if(amt == 0) return 0;

        int minCoins = Integer.MAX_VALUE;
        for(int coin : coins) {
            if(amt - coin >= 0) {
                minCoins = Math.min(minCoins, f(coins, amt - coin));
            }
        }

        return minCoins != Integer.MAX_VALUE ? 1 + minCoins : Integer.MAX_VALUE ;
    }

    public int f_td(int[] coins, int amt, int[] dp) {
        if(amt == 0) return 0;

        if(dp[amt] != -1) return dp[amt];

        int minCoins = Integer.MAX_VALUE;
        for(int coin : coins) {
            if(amt - coin >= 0) {
                minCoins = Math.min(minCoins, f_td(coins, amt - coin, dp));
            }
        }

        return dp[amt] = ( minCoins != Integer.MAX_VALUE ? 1 + minCoins : Integer.MAX_VALUE );
    }

    public int f_bu(int[] coins , int amt) {
        int[] dp = new int[10005];

        if(amt == 0) return 0;
        dp[0] = 0;

        for(int money = 1; money <= amt ; money++) {
            int minCoins = Integer.MAX_VALUE;
            for(int coin : coins) {
                if(money - coin >= 0) {
                    minCoins = Math.min(minCoins, dp[money-coin]);
                }
            }

            dp[money] = ( minCoins != Integer.MAX_VALUE ? 1 + minCoins : Integer.MAX_VALUE );
        }

        return dp[amt];
    }

    public int coinChange(int[] coins, int amount) {
        // int ans = f(coins, amount);

        // int[] dp = new int[10005];
        // Arrays.fill(dp, -2); // Why initialized with -2 ? Because -1 denotes that no combination possible . Although here we don't store -1 directly inside dp array , we check in main(caller) only , but just to be on safer side. 
        // int ans = f_td(coins, amount, dp); 

        int ans = f_bu(coins, amount);


        return ans != Integer.MAX_VALUE ? ans : -1;
    }
}