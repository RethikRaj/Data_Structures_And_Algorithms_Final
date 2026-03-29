class Solution {

    public int brute(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;

        // Try every possible buying day (can't buy on the last day) => buyDay : [0, n-2]
        for (int buyDay = 0; buyDay < n - 1; buyDay++) {
            int buyPrice = prices[buyDay];

            // Find the day with the highest price after buyDay => Find the max in range [i + 1, n-1]
            int maxPrice = Integer.MIN_VALUE;
            for (int j = buyDay + 1; j < n; j++) {
                if (prices[j] > maxPrice) {
                    maxPrice = prices[j];
                }
            }

            // Calculate profit for this buy/sell pair and update max
            int currProfit = maxPrice - buyPrice;
            maxProfit = Math.max(maxProfit, currProfit);
        }

        return maxProfit;
    }

    public int better(int[] prices) {
        int n = prices.length;

        // Precompute: for each day, store the max price available in the future (to the right)
        int[] maxFuturePrices = new int[n];
        int maximum = Integer.MIN_VALUE;
        for (int day = n - 1; day >= 0; day--) {
            maxFuturePrices[day] = maximum;   // Best sell price if bought on this day
            if (prices[day] > maximum) {
                maximum = prices[day];
            }
        }

        // Try buying on each day and compute profit using the best future sell price
        int maxProfit = 0;
        for (int buyDay = 0; buyDay < n - 1; buyDay++) {
            int bestSellPrice = maxFuturePrices[buyDay];

            // No future day exists with a higher price or no future day is there - skip
            if (bestSellPrice == Integer.MIN_VALUE) {
                continue;
            }

            int currProfit = bestSellPrice - prices[buyDay];
            maxProfit = Math.max(maxProfit, currProfit);
        }

        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        // return brute(prices);
        return better(prices);
    }
}