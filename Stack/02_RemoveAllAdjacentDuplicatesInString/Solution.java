import java.util.Deque;

class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> st = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            if(st.isEmpty() || (st.peek() != c )) {
                st.push(c);
            }
            else { // Not empty and st.peek() == c
                st.pop();
            }
        }

        // Construct output string from stack
        StringBuilder sb = new StringBuilder("");
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }
        sb.reverse();

        return new String(sb);
    }
}