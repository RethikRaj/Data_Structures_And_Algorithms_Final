public class Factorial {
    public static int factorial(int n) {
        // base case
        if(n == 1){
            return 1;
        }
        // 1 case me solve, remaining recursion will handle
        return n * factorial(n-1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}