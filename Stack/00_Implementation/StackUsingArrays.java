
class ArrayStack {
    int[] stack;
    int top;
    int capacity;

    ArrayStack(int size) {
        stack = new int[size];
        top = -1;
        capacity = size;
    }

    void push(int value) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        stack[++top] = value;
    }

    int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return stack[top--];
    }

    int peek() {
        if(top == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return stack[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}


public class StackUsingArrays {
    public static void main(String[] args) {
        ArrayStack s = new ArrayStack(5);
        s.peek();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        System.out.println(s.peek());
        s.push(6);
    }
}
