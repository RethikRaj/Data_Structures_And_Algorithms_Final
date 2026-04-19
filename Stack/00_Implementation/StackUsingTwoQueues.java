// Optimal 
/*
    Single Queue :
        - Push - O(n)
        - Pop  - O(1)
        - top  - O(1)
        - isEmpty - O(1)
 */
class MyStackOptimalOne {
    Queue<Integer> q;

    public MyStack() {
        q = new ArrayDeque<>();
    }
    
    public void push(int x) {
        // push x into queue
        q.offer(x);
        // Rotate the queue size-1 time , that is , pop and push size-1 times
        int count = q.size() - 1;
        while(count != 0) {
            q.offer(q.poll());
            count -= 1;
        }
        // Now the top element will be the last inserted element => Pop, push - O(1)
    }
    
    public int pop() {
        return q.poll();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}

/*
    Single Queue :
        - Push - O(1)
        - Pop  - O(n)
        - top  - O(1)
        - isEmpty - O(1)
 */
class MyStackOptimalTwo {
    private Queue<Integer> q;
    int topEle;

    public MyStack() {
        q = new ArrayDeque<>();
    }
    
    public void push(int x) {
        q.offer(x);
        topEle = x;
    }
    
    public int pop() {
        int size = q.size();
        // Pop and push size - 1 times
        for(int i = 0 ; i < size - 1 ; i++) {
            topEle = q.poll();
            q.offer(topEle);
        }
        // frontelement will be the topElement of stack
        return q.poll();
    }
    
    public int top() {
        return topEle;
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}

// Brute :
// Two queues 
/*
    Push - O(n), Pop - O(1), top() - O(1) , isEmpty() - O(1)
 */
class MyStackOne {
    Queue<Integer> main;
    Queue<Integer> helper;

    public MyStack() {
        main = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }
    
    public void push(int x) {
        while(!main.isEmpty()) {
            helper.offer(main.poll());
        }
        
        main.offer(x);

        while(!helper.isEmpty()) {
            main.offer(helper.poll());
        }
    }
    
    public int pop() {
        return main.poll();
    }
    
    public int top() {
        return main.peek();
    }
    
    public boolean empty() {
        return main.isEmpty();
    }
}

// Two queues 
/*
    Push - O(1), Pop - O(n), top() - O(n) , isEmpty() - O(1)
 */

class MyStackTwo {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int topElement;

    public MyStack() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }
    
    public void push(int x) {
        q1.offer(x);
        topElement = x;
    }
    
    public int pop() {
        // Dequeue size - 1 elements from q1 to q2
        while(q1.size() > 1) {
            topElement = q1.poll(); // !IMPPP
            q2.offer(topElement);
        }
        // Now q1 contains single element which is the last inserted element
        int ans = q1.poll();

        // Swap q1 with q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return ans;
    }
    
    public int top() {
        return topElement;
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}



/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
