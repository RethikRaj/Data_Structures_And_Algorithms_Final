class Solution {
    // iterative
    public void iterative(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while(left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left += 1;
            right -= 1;
        }
    }

    // recursive
    public void f(char[] s, int left, int right) {
        // base case
        if(left >= right) return;

        // My work (1 case me solve)
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        // Recursion
        f(s, left + 1, right - 1);
    }

    public void reverseString(char[] s) {
        // Iterative way
        iterative(s);

        // Recursive way
        // f(s, 0 , s.length - 1);
    }
}
