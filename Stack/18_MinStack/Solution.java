
// BetterOne : Using Two stacks : One as mainStack and another stores the minElement at each level.
class MinStackBetter {
    ArrayDeque<Integer> mainStack;
    ArrayDeque<Integer> helperStack; // Stores minElement at each level.

    public MinStack() {
        mainStack = new ArrayDeque<>();
        helperStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        if(mainStack.isEmpty()) {
            mainStack.push(val);
            helperStack.push(val);
        }else {
            mainStack.push(val);
            int currMin = helperStack.peek();
            int minAfterPush = Math.min(currMin, val);
            helperStack.push(minAfterPush);
        }
    }
    
    public void pop() {
        mainStack.pop();
        helperStack.pop();
    }
    
    public int top() {
        return mainStack.peek();
    }
    
    public int getMin() {
        return helperStack.peek();
    }
}

// Better Two : Using One stack but this stack stores a pair of values 
class Pair {
    int first; // actual value
    int second; // minimum element at each level
    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class MinStackBetterTwo {
    ArrayDeque<Pair> stack;

    public MinStack() {
        stack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        if(stack.isEmpty()) {
            stack.push(new Pair(val, val));
        }else {
            stack.push(new Pair(val, Math.min(stack.peek().second, val)));
        }
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek().first;
    }
    
    public int getMin() {
        return stack.peek().second;
    }
}


// optimal ( best ) : Encoding & Decoding Approach

// Way One : Works only when range of numbers is limited and non-negative
// We need to store (val , minEle @ each level) by using an single integer 
// Let's say the range is [a, b] => Take a number `modulus` = b + 1
// Encode(x) : encodedVal = x * modulus + minEle
// Decode(x) : originalVal = x / modulus , minEle = x % modulus

// Way Two : 




/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
