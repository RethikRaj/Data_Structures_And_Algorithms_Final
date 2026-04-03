class Solution {
    public boolean isAlphaNumeric(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }

    // Method 1 : TC : O(n), SC :O(n)
    public boolean approachOne(String s) {
        StringBuilder temp = new StringBuilder("");

        for(char c : s.toCharArray()) {
            c = Character.toLowerCase(c);
            if(isAlphaNumeric(c)) {
                temp.append(c);
            }    
        }

        // Now check palindrome
        int left = 0;
        int right = temp.length() - 1;

        while(left < right) {
            if(temp.charAt(left) != temp.charAt(right)){
                return false;
            }
            left += 1;
            right -= 1;
        }

        return true;
    }

    // Method 2 : Check palindrome on the fly : TC : O(n) , SC : O(1)
    public boolean approachTwo(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            char c1 = Character.toLowerCase(s.charAt(left));
            char c2 = Character.toLowerCase(s.charAt(right));

            boolean isAlphaNumericC1 = isAlphaNumeric(c1);
            boolean isAlphaNumericC2 = isAlphaNumeric(c2);

            if(isAlphaNumericC1 == true && isAlphaNumericC2 == true) {
                // Check equal or not
                if(c1 != c2) return false;
                left += 1;
                right -= 1;
            }else if (isAlphaNumericC1 == false && isAlphaNumericC2 == true) {
                left += 1;
            } else if (isAlphaNumericC1 == true && isAlphaNumericC2 == false) {
                right -= 1;
            } else {
                left += 1;
                right -= 1;
            }
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        // return approachOne(s);
        return approachTwo(s);
    }
}
