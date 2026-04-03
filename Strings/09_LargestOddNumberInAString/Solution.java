class Solution {
    public String largestOddNumber(String num) {
        int index = num.length() - 1;

        // Traverse from last and find the first occurence of odd digit , and then return the substring from [0, index]
        while(index >= 0) {
            char ch = num.charAt(index);
            int j = ch - '0';

            if(j % 2 != 0) {
                // odd digit
                return num.substring(0, index + 1);
            }
            index -= 1;
        }

        return "";
    }
}
