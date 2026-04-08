import java.util.ArrayDeque;

class Solution {
    // Approach One : Using only stack :  (In stack we push both '(' , ')'  based on some condition and pop on match)
    public int approachOne(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            }else if(c == ')') {
                if(stack.isEmpty()) {
                    stack.push(')');
                } else {
                    char top = stack.peek();

                    if(top == '(') {
                        stack.pop();
                    }else if(top == ')') {
                        stack.push(')');
                    }

                }
            }
        }

        return stack.size();
    }

    // Approach Two : Using stack(we only push '(') + count 
    public int approachTwo(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        int countOfOpeningBracketsRequired = 0;

        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            }else if(c == ')') {
                if(stack.isEmpty()) {
                    countOfOpeningBracketsRequired += 1;
                } else {
                    stack.pop();
                }
            }
        }

        int countOfClosingBracketsRequired = stack.size();
        return countOfClosingBracketsRequired + countOfOpeningBracketsRequired;
    }

    // Approach Three : Using depth + count (Optimal) SC : O(1)
    public int approachThree(String s) {
        int depth = 0;
        int countOfOpeningBracketsRequired = 0;

        for(char c : s.toCharArray()) {
            if(c == '(') {
                depth += 1;
            }else if(c == ')') {
                if(depth == 0) {
                    countOfOpeningBracketsRequired += 1;
                } else {
                    depth -= 1;
                }
            }
        }

        int countOfClosingBracketsRequired = depth;
        return countOfClosingBracketsRequired + countOfOpeningBracketsRequired;

    }

    public int minAddToMakeValid(String s) {
        return approachThree(s);
    }
}