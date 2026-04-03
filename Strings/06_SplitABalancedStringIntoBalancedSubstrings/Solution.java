class Solution {
    public int balancedStringSplit(String s) {
        int countOfR = 0;
        int countOfL = 0;
        int countOfBalancedStrings = 0;

        for(char c : s.toCharArray()) {
            if(c == 'R') countOfR += 1;
            if(c == 'L') countOfL += 1;

            if(countOfR == countOfL) {
                countOfBalancedStrings += 1;
                countOfR = 0;
                countOfL = 0;
            }
        }

        // No need to check if countOfR == 0 && countOfL == 0 because given string is balanced so for sure if we no substring is balanced , the whole string(which is also a substring) atlast will be balanced.
        return countOfBalancedStrings;
    }
}