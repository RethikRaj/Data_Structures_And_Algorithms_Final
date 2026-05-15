class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        int left = 0;
        int right = 0;

        boolean[] window = new boolean[255]; // behaves equivalent to set

        while(right < s.length()) {
            // Check whether `s.charAt(right)` is repeating and if repeating remove the previous occurence
            // Shrinking window until duplicate is removed.
            while(window[s.charAt(right)] == true) {
                window[s.charAt(left)] = false;
                left += 1;
            }

            window[s.charAt(right)] = true;
            maxLength = Math.max(maxLength, right - left + 1);
            right += 1;
        }

        return maxLength;
    }
}