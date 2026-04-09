
/*
    To find the stock span of i'th Price(Pi) , we need to find the PGE's index for Pi. Let's say it is 'j' , then stock span = i - j;
 */


class Node {
    int index;   // Position of this price in the sequence which it is inserted
    int value;   // The stock price at this position

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

class StockSpanner {
    int index;              // Tracks current day (0-based)
    ArrayDeque<Node> stack; // Monotonic decreasing stack (stores the elements which can be PGE of upcoming elements)

    public StockSpanner() {
        index = -1;         
        stack = new ArrayDeque<>();
    }
    
    public int next(int price) {
        index += 1; 

        // Pop all prices that are <= current price (they can't be PGE of upcoming elements)
        // This keeps the stack strictly DECREASING from bottom to top
        while (!stack.isEmpty() && stack.peek().value <= price) {
            stack.pop();
        }

        int span;
        if (!stack.isEmpty()) {
            // The PGE of today's Price is at stack.peek().value
            span = index - stack.peek().index;
        } else {
            // No PGE exists → all days from day 0 to today are <= price
            span = index + 1; // +1 because index is 0-based
        }

        // Push current day onto stack as a potential future PGE
        stack.push(new Node(index, price));

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */