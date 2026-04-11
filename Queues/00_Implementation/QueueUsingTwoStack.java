import java.util.ArrayDeque;

// We always push at the bottom of the stack.
/*
    Push - O(n)
    Pop  - O(1)
    Peek - O(1)
    isEmpty - O(1)
 */
class MyQueueBrute {
    private ArrayDeque<Integer> main;
    private ArrayDeque<Integer> helper;

    public MyQueue() {
        main = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }
    
    public void push(int x) {
        // Push all elements from stack1(main) to stack2(helper)
        while(!main.isEmpty()) {
            helper.push(main.pop());
        }
        // Insert x into stack1(main)
        main.push(x);
        // Push all elements back from stack2(helper) to stack1(main)
        while(!helper.isEmpty()) {
            main.push(helper.pop());
        }

    }
    
    public int pop() {
        return main.pop();
    }
    
    public int peek() {
        return main.peek();
    }
    
    public boolean empty() {
        return main.isEmpty();
    }
}

// We do rearranging only when someone tries to do dequeue operation
/*
    Push - O(1)
    Pop & peek - Amortized O(1) - Only 3 operations per element (push , pop from input to output, pop from output).So even though a single pop/peel call might trigger an O(n) transfer, that transfer handles all n elements at once. Those n elements will then be served by future pop/peek calls in O(1) each, without needing another transfer.
    isEmpty() - O(1)
 */
class MyQueueOptimal {
    ArrayDeque<Integer> input;  // For pushing
    ArrayDeque<Integer> output; // For popping

    public MyQueue() {
        input = new ArrayDeque<>();
        output = new ArrayDeque<>();
    }
    
    public void push(int x) {
        input.push(x);
    }
    
    public int pop() {
        // First do reversal if output stack is empty
        if(output.isEmpty()) {
            while(!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        return output.pop();
    }
    
    public int peek() {
        // First do reversal if output stack is empty
        if(output.isEmpty()) {
            while(!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        return output.peek();
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */