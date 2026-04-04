class Solution {
    // Approach 1 : Check whether frequencies of each character matches in both
    // TC : O(n) , SC : O(1)
    public boolean approachOne(String s, String t) {
        // !Imp base case
        if (s.length() != t.length())
            return false;

        // Step 1 : Prepare freq map of s
        int[] map = new int[128];
        for (char c : s.toCharArray()) {
            map[c] += 1;
        }

        // Step 2 : Decrement for t, exit early if count goes negative
        for (char c : t.toCharArray()) {
            map[c] -= 1;
            if (map[c] < 0)
                return false;
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        return approachOne(s, t);
    }
}
