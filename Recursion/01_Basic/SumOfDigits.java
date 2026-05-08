public class SumOfDigits {
    // f(n) -> calculates the sum of digits in n;
    public static int f(int n) {
        // Base case
        if(n == 0 ) return 0;
        // Recursion calculate the remaining digits sum
        int remDigitsSum = f(n/10);
        // My work
        int sum = n%10 + remDigitsSum;
        return sum;
    }

    // Approach 2 :
    static class MyInteger {
        int val;
        MyInteger(int val) {
            this.val = val;
        }
    }
    public static void f(int n, MyInteger sum) {
        if(n == 0) return;

        // Add last digit to sum
        sum.val = sum.val + n % 10;
        f(n/10, sum);
    }

    // Approach 3;

    public static void f(int n, int[] sum) {
        if(n == 0) return;

        sum[0] += n % 10;
        f(n/10, sum);
    }
    public static void main(String[] args) {
        int n = 1234;

        // Approach 1
        System.out.println(f(n));

        // Approach 2
        MyInteger sum = new MyInteger(0);
        f(n, sum);
        System.out.println(sum.val);

        // Approach 3
        int[] ans = new int[1];
        f(n, ans);
        System.out.println(ans[0]);

    }
}
