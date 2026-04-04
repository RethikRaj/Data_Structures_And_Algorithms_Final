import java.util.Arrays;

class Solution {
    // Sorting approach
    public String sortingApproach(String[] strs) {
        if(strs.length == 0) return "";

        // Step 1 : Sort the array
        Arrays.sort(strs);
        // Now observe that first and last string will have most different characters
        // So if we find the index till where the first and last string matches we can easily find the answer
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }
        return first.substring(0, i);
    }

    // Recursive solution : Divide and conquer (IMP)
    public String f(String[] strs, int start, int end) {
        // Base case
        if(start == end) return strs[start];

        // Divide
        int mid = start + (end-start)/2;
        String s1 = f(strs, start, mid);
        String s2 = f(strs, mid + 1, end);
        // Conquer : Find the common prefix for the two strings s1 , s2 (My work)
        int i = 0;
        while(i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)) {
            i += 1;
        }
        return s1.substring(0, i);
    }

    // Vertical scan approach  (Optimal) (IMP)
    // TC : O(S) -> where S is the sum of all characters in all strings, SC : O(1).
    public String verticalScanApproach(String[] strs) {
        // Edge case
       if(strs.length == 0) return "";

        // If we imagine String[] as 2D matrix. Then we need to check for one col each row has same value or not.
        // Therefore we need to do col traversal
        // But each col has a different length what should be looping condition ? It should be min length of all strings.
        // Let's assume min length to be strs[0].length() and check that index is valid or not inside loop
        for(int col = 0 ; col < strs[0].length(); col++) {
            // row acts as index of String[]
            // For each row ,check if col index is valid and Check if all rows have same col value

            char c = strs[0].charAt(col);

            for(int row = 1; row < strs.length ; row++) {
                if(col >= strs[row].length() || strs[row].charAt(col) != c ){
                    return strs[0].substring(0, col);
                }
            }   
        }
        return strs[0];
    }

    public String longestCommonPrefix(String[] strs) {

        // Divide and conquer
        return f(strs, 0, strs.length-1);

        // return verticalScanApproach(strs);
    }
}