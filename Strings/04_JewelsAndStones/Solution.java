class Solution {
    // Brute : TC : O(stones.length * jewels.length), SC:O(1)
    // For each character in stones : loop through the jewels string and find whether that character is present or not.

    // Better : TC : O(max(stones.length, jewels.length)), SC : O(jewels.length)
    // Use a set to store characters in jewels
    // For each character in stones : check whether that character is present in set or not .
    public int better(String jewels, String stones) {
        Set<Character> st = new HashSet<>();

        // 1. Store character of jewels in st
        for(int i = 0 ; i < jewels.length();i++) {
            st.add(jewels.charAt(i));
        }

        // 2. For each character in stones ; check in set
        int count = 0;
        for(int i = 0 ; i < stones.length() ; i++) {
            if(st.contains(stones.charAt(i))) {
                count += 1;
            }
        }

        return count;
    }

    // Little more optimisation : SC : O(1)
    // Use boolean[] arr = new boolean[256] or new boolean[128]; because jewels and stones consist of only English letters.
    public int betterOptimised(String jewels, String stones) {
        boolean[] arr = new boolean[256];

        for(char c : jewels.toCharArray()) {
            arr[c] = true;
        }

        int count = 0;
        for(char c : stones.toCharArray()) {
            if(arr[c] == true) count += 1;
        }

        return count;
    }

    public int numJewelsInStones(String jewels, String stones) {
        // return better(jewels, stones);
        return betterOptimised(jewels, stones);
    }
}