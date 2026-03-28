class Solution {
    public int absoluteValue(int x){
        if ( x < 0) {
            return -x;
        }
        return x;
    }

    public int reverse(int x) {
        int abs_x = absoluteValue(x);
        long rev_x = 0;
        // Why long ? 
        // If x = Integer.MAX_VALUE (2147483647), its reverse 7463847412
        // exceeds int range [-2147483648, 2147483647], causing overflow.
        // Using long safely holds the reversed value before the bounds check.
        while(abs_x > 0) {
            int last_digit = abs_x % 10;
            abs_x = abs_x/10;
            rev_x = rev_x * 10 + last_digit;
        }

        // Check whether reverse went out of integer range.
        if(rev_x < Integer.MIN_VALUE || rev_x > Integer.MAX_VALUE) {
            return 0;
        }

        return x < 0 ? (int)-rev_x : (int)rev_x;
    }
}
