import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Item {
    int val;
    int weight;

    Item(int val, int weight) {
        this.val = val;
        this.weight = weight;
    }
}

class CustomComparator implements Comparator<Item> {
    @Override
    public int compare(Item a, Item b) {
        double ratioA = (double) a.val / a.weight;
        double ratioB = (double) b.val / b.weight;
        return Double.compare(ratioB, ratioA); // max-heap behavior
        
        
        // return (b.val * a.weight) - (a.val * b.weight); // Method 2 
    }
}

class Solution {
    
    // method 1 : Using Priority Queues : TC : O(n logn + n logn) , SC : O(n)
    public double methodOne(int[] val, int[] wt, int capacity) {
        // Step 1 : 
        CustomComparator cc = new CustomComparator();
        PriorityQueue<Item> pq = new PriorityQueue<>(cc);

        for(int i = 0 ; i < val.length ; i++) {
            pq.offer(new Item(val[i], wt[i]));
        }
        
        // Step 2 :
        double profit = 0.0;
        while(!pq.isEmpty() && capacity >= pq.peek().weight) {
            Item temp = pq.poll();
            profit += temp.val;
            capacity -= temp.weight;
        }

        if(!pq.isEmpty()) {
            profit = profit + ((double)capacity/pq.peek().weight)*pq.peek().val;
        }
        
        return profit;
    }

    // method 2 : Using arrays + sorting :  TC : O(n logn + n) , SC : O(n)
    public double methodTwo(int[] val, int[] wt, int capacity) {
        int n = val.length;
        
        // Step 1: Create items array
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(val[i], wt[i]);
        }

        // Step 2: Sort by value/weight ratio (descending)
        CustomComparator cc = new CustomComparator();
        Arrays.sort(items, cc);

        // Step 3: Greedy selection
        double profit = 0.0;

        for (int i = 0; i < n; i++) {
            if (capacity >= items[i].weight) {
                profit += items[i].val;
                capacity -= items[i].weight;
            } else {
                profit += ((double) capacity / items[i].weight) * items[i].val;
                break;
            }
        }

        return profit;

    }

    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        return methodOne(val, wt, capacity);
        // return methodTwo(val, wt, capacity);
    }
}