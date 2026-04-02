class Solution {
    // Approach 1 : Using inbuilt methods
    public int approachOne(String s) {
        // 1. Trim spaces before the first character and after the last character
        s = s.trim();
        // 2. Split by " "
        String[] arr = s.split(" ");

        int index = arr.length - 1;
        return index >= 0 ? arr[index].length() : 0;
    }

    // Approach 2 : Dry run 
    public int approachTwo(String s) {
        int n = s.length();

        int i = 0;
        while (i < n) {
            int count = 0;

            // Count characters in the current word
            while (i < n && s.charAt(i) != ' ') {
                count += 1;
                i += 1;
            }

            // Skip trailing spaces after the current word
            while (i < n && s.charAt(i) == ' ') {
                i += 1;
            }

            // If we've consumed the entire string after skipping spaces,
            // 'count' holds the length of the last word
            if (i >= n) return count;
        }

        // s was all spaces (or empty) — no word was found
        return 0;
    }

    // Best approach : Traverse from end
    public int best(String s) {
        int last = s.length() - 1;

        // Skip white spaces
        while(last >= 0 && s.charAt(last) == ' ') {
            last -= 1;
        }

        // count the length of last word
        int count = 0;
        while(last >= 0 && s.charAt(last) != ' ') {
            count += 1;
            last -= 1;
        }
        return count;
    }


    public int lengthOfLastWord(String s) {
        return approachOne(s);
        // return approachTwo(s);
    }
}