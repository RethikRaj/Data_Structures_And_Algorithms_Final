class Solution {
    // Method 1 : Direst stack based solution

    public int stackBased(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(String token : tokens) {
            if(token.equals("+")) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2 + temp1);
            }else if(token.equals("-")) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2 - temp1);
            }else if(token.equals("*")) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2 * temp1);
            }else if(token.equals("/")) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2 / temp1);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    // Method 2 : Implement array using stack and manage top pointer => Marginally faster because it avoid stack class's method call overhead.
    public int arrayBased(String[] tokens) {
        // Why size = (tokens.length + 1) / 2 ? 
        // Because question has told that the expression is valid . Which means for k operands there will be k-1 operators.
        // Therefore tokens.length = k + k - 1 = 2k -1; In arr(stack) we store only operands. 
        int[] stack = new int[(tokens.length + 1) / 2];
        int top = -1;

        for (String token : tokens) {
            if (token.equals("+")) {
                stack[top - 1] = stack[top - 1] + stack[top];
                top--;
            } else if (token.equals("-")) {
                stack[top - 1] = stack[top - 1] - stack[top];
                top--;
            } else if (token.equals("*")) {
                stack[top - 1] = stack[top - 1] * stack[top];
                top--;
            } else if (token.equals("/")) {
                stack[top - 1] = stack[top - 1] / stack[top];
                top--;
            } else {
                top++;
                stack[top] = Integer.parseInt(token);
            }
        }

        return stack[0];
    }

    public int evalRPN(String[] tokens) {
        // return stackBased(tokens);
        return arrayBased(tokens);
    }
}