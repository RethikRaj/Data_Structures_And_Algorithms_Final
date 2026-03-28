public class Power {
    // TC : O(b), SC: O(b)
    public static int brutePower(int a, int b) {
        if (b == 0) return 1;

        return a * brutePower(a, b-1);
    }

    // TC : O(b), SC : O(log2b)
    public static int betterPower(int a, int b) {
        if (b == 0) return 1;

        int val1 = betterPower(a, b/2);
        int val2 = betterPower(a, b/2);
        if(b % 2 == 0){
            return val1 * val2;
        }else {
            return a * val1 * val2;
        }
    }

    // TC : O(log2b), SC : O(log2b)
    public static int bestPower(int a, int b){
        if (b == 0) return 1;

        int value = betterPower(a, b/2);
        if(b % 2 == 0){
            return value * value;
        }else {
            return a * value * value;
        }
    }

    public static void main(String[] args) {
        System.out.println(bestPower(2, 3));
    }
}
