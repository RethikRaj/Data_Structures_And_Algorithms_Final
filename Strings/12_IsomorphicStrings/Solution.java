import java.util.HashMap;

class Solution {

    // Approach Two : TC:O(N), SC:O(1)
    // Approach Two Way One : Using one map and one set
    public boolean approachTwoWayOne(String s, String t) {
        // If lengths are different, strings cannot be isomorphic
        if (s.length() != t.length()) return false;

        // Map to store character mapping from string s -> string t
        // Using Character[] instead of char[] so we can check for null (unmapped)
        // This prevents a single character in 's' mapping to  multiple characters in 't'
        Character[] mp = new Character[128];

        // Set to track which characters in 't' are already mapped
        // This prevents multiple characters in 's' mapping to same character in 't'
        boolean[] st = new boolean[128];

        // Traverse both strings simultaneously
        for (int i = 0; i < s.length(); i++) {

            char key = s.charAt(i);     // character from string s
            char value = t.charAt(i);   // corresponding character from string t

            // Case 1: key already mapped before
            if (mp[key] != null) {

                // If existing mapping doesn't match current value → not isomorphic
                if (mp[key] != value) return false;

            } else {

                // Case 2: key not mapped yet
                // Check if this value is already mapped to some other key
                // (ensures one-to-one mapping)
                if (st[value] == true) return false;

                // Create new mapping
                mp[key] = value;

                // Mark this value as already mapped
                st[value] = true;
            }
        }

        // If all mappings are valid, strings are isomorphic
        return true;
    }

    // Approach Two Way Two : Same as better one but uses two maps
    public boolean approachTwoWayTwo(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> mapST = new HashMap<>();
        HashMap<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check s -> t mapping
            if (mapST.containsKey(charS) && mapST.get(charS) != charT) {
                return false;
            }
            // Check t -> s mapping
            if (mapTS.containsKey(charT) && mapTS.get(charT) != charS) {
                return false;
            }

            mapST.put(charS, charT);
            mapTS.put(charT, charS);
        }
        return true;
    }

    // Approach Three : Different from above two . Uses last seen index
    // The last time s[i] appeared must be the same as the last time t[i] appeared. 

    public boolean approachThree(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] lastSeenS = new int[128];
        int[] lastSeenT = new int[128];
        Arrays.fill(lastSeenS, -1);
        Arrays.fill(lastSeenT, -1);

        for(int i = 0 ; i < s.length();i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if(lastSeenS[charS] != lastSeenT[charT]) {
                return false;
            }

            // Update 
            lastSeenS[charS] = i;
            lastSeenT[charT] = i;
        }

        return true;
    }


    public boolean isIsomorphic(String s, String t) {
        // return approachTwoWayTwo(s, t);
        // return approachTwoWayOne(s, t);
        return approachThree(s, t);
    }
}