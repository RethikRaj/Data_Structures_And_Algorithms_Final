class Solution {
    public boolean brute(int n) {
        // Brute : Iterate through every power of 2 till n.
        long num = 0;
        int exponent = 0;
        while(num <= n){
            num = (long)Math.pow(2, exponent);
            exponent += 1;
            System.out.println(num);
            if(num == n) {
                return true;
            }
        }
        return false;
    }

    public boolean better(int n) {
        // Better : Repeatedly divide n by 2 and check if the remainder is always 0.
        // A power of 2 is only divisible by 2 with no remainder until it reduces to 1.
        if(n <= 0){
            return false;
        }

        if(n == 1){
            return true;
        }

        while(n != 1) {
            int remainder = n % 2;
            n = n / 2;

            if (remainder != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isPowerOfTwo(int n) {
        // return brute(n);
        return better(n);
    }

}
