class Solution {
    public boolean Brute(int x) {
        // Step 1 : Convert integer to string/arr.
        String s = String.valueOf(x);
        // Step 2 : Check palindrome.
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left += 1;
            right -= 1;
        }

        return true;
    }

    public boolean better(int x) {
        // Step 1 : reverse the integer
        int original_x = x;
        int reversed_x = 0; 
        while ( x > 0) {
            int last_digit = x % 10;
            x = x / 10;

            reversed_x = reversed_x*10 + last_digit;
        }

        // Step 2 : Check whether the reversed intger is equal to original integer.
        return original_x == reversed_x;
    }

    public boolean isPalindrome(int x) {
        // Early return
        if( x < 0) {
            return false;
        }

        // return brute(x);

        return better(x);        
    }
}
