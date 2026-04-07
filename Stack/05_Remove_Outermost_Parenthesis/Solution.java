class Solution {
    // Approach One Way one:
    // Observation : When we reach a primitive decomposition stack becomes empty
    public String approachOneWayOne(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        StringBuilder finalAns = new StringBuilder("");
        StringBuilder currPrimitive = new StringBuilder("");
        for(char c : s.toCharArray()) {
            currPrimitive.append(c);
            if(c == '(') {
                stack.push(c);
            }else {
                stack.pop();

                if(stack.isEmpty()) {
                    // We got one primitive decomposition => Remove outermost from currPrimitive
                    String removedCurrPrimitive = currPrimitive.substring(1, currPrimitive.length()-1);
                    finalAns.append(removedCurrPrimitive);
                    currPrimitive = new StringBuilder("");
                }
            }
        }

        return new String(finalAns);
    }
    
    // But the above solution has one problem due to my way of coding , i needed two stringbuilders and substring method which itself takes O(n) time 
    // Same approach but different code
    // TC : O(n), SC :O(n)
    public String approachOneWayTwo(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        StringBuilder finalAns = new StringBuilder("");
        for(char c : s.toCharArray()) {
            if(c == '(') {
                // Append only if it is not the outermost opening bracket
                // If stack empty => then the incoming opening bracket is outermost
                if(!stack.isEmpty()) {
                    finalAns.append(c);
                }

                stack.push(c);
            }else {
                stack.pop();

                if(!stack.isEmpty()) {
                    finalAns.append(c);
                }
            }
        }

        return new String(finalAns);
    }


    // Approach Two (Optimised) (Best)
    // We don’t actually need a stack. We only care about depth of parentheses, so an integer counter is enough.
    // depth == 0 → outermost level
    // TC : O(n), SC : O(1)
    public String Best(String s) {
        StringBuilder finalAns = new StringBuilder();
        int depth = 0;

        for(char c : s.toCharArray()) {
            if(c == '(') {
                if(depth > 0) finalAns.append(c);
                depth++;
            } else {
                depth--;
                if(depth > 0) finalAns.append(c);
            }
        }

        return finalAns.toString();
    }

    public String removeOuterParentheses(String s) {
        // return approachOneWayOne(s);
        return approachOneWayTwo(s);
    }
}