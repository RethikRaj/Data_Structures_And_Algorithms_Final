
public class Solution {
    public static int f(int num) {
        if(num == 1) return 0;
        if(num == 2 | num == 3) return 1;

        int optionOne = f(num - 1);
        int optionTwo = num % 2 == 0 ? f(num/2) : Integer.MAX_VALUE;
        int optionThree = num % 3 == 0 ? f(num/3) : Integer.MAX_VALUE;

        return 1 + Math.min( Math.min(optionOne, optionTwo), optionThree);
    }
    
    public static int f_td(int num, int[] dp) {
        if(num == 1) return 0;
        if(num == 2 | num == 3) return 1;

        if(dp[num] != -1) return dp[num];

        int optionOne = f_td(num - 1, dp);
        int optionTwo = num % 2 == 0 ? f_td(num/2, dp) : Integer.MAX_VALUE;
        int optionThree = num % 3 == 0 ? f_td(num/3, dp) : Integer.MAX_VALUE;

        return dp[num] = 1 + Math.min( Math.min(optionOne, optionTwo), optionThree);
    }

    public static int f_bu(int num) {
        int[] dp = new int[100005];

        if(num == 1) return 0;
        if(num == 2 | num == 3) return 1;

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for(int i = 4 ; i <= num ; i++) {
            int optionOne = dp[i-1];
            int optionTwo = i % 2 == 0 ? dp[i/2] : Integer.MAX_VALUE;
            int optionThree = i % 3 == 0 ? dp[i/3] : Integer.MAX_VALUE;

            dp[i] = 1 + Math.min( Math.min(optionOne, optionTwo), optionThree);
        }

        return dp[num];
    }

    public static int countStepsToOne(int n) {

        // return f(n);

        // int[] dp = new int[100005];
        // Arrays.fill(dp, -1);
        // return f_td(n, dp);

        return f_bu(n);
    }
}