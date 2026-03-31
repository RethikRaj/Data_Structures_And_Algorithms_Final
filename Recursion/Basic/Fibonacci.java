public class Fibonacci {
    // f(n) -> return nth fibonnacci number
    public static int f(int n) {
        if(n == 0 || n == 1) return n;

        return f(n-1) + f(n-2);
    }

    public static void main(String[] args) {
        // 0, 1, 1, 2, 3, 5
        int n = 5;
        System.out.println(f(5));
    }
}