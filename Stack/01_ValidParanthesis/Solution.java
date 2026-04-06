class Solution {
    // Brute Force
    public boolean brute(String s) {
        // Keep removing adjacent matching pairs until none remain
        String prev = "";
        while (!s.equals(prev)) { // Stop when there are no matching pairs
            prev = s;
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
        }
        return s.isEmpty();
    }

    // Optimal (Best)

    public boolean isMatch(char top, char c) {
        return (top == '(' && c == ')') || (top == '{' && c == '}') || (top == '[' && c == ']');
    }

    public boolean best(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') { // If opening bracket , then push
                stack.push(c);
            } else { // Closing bracket
                if (stack.isEmpty()) {
                    return false;
                } else {
                    // Check whether top(open bracket) matches the incoming close bracket, if
                    // mismatch return false
                    char top = stack.peek();
                    if (isMatch(top, c)) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        // return brute(s);
        return best(s);
    }
}