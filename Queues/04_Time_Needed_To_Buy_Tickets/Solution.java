import java.util.Queue;

class Node {
    int person;       // person's index in original array
    int ticketCount;  // remaining tickets to buy

    Node(int person, int ticketCount) {
        this.person = person;
        this.ticketCount = ticketCount;
    }
}

class Solution {

    /**
     * Brute Force - Queue with Node objects
     * Strategy: Simulate the queue — each round, decrement front person's ticket count.
     * If done and it's person k, return time. Otherwise re-enqueue.
     * TC: O(n * max(tickets[i])) => Worst case : TC : O(n^2), SC: O(n)
     */
    public int bruteWayOne(int[] tickets, int k) {
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < tickets.length; i++) {
            queue.offer(new Node(i, tickets[i]));
        }

        int time = 0;
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            front.ticketCount--;
            time++;

            if (front.ticketCount == 0) {
                if (front.person == k) return time; // k is done
            } else {
                queue.offer(new Node(front.person, front.ticketCount));
            }
        }
        return time;
    }

    /**
     * Brute Force - Queue with indices (cleaner, avoids Node wrapper)
     * Strategy: Same simulation, but store only the index and mutate tickets[] in-place.
     * Stop as soon as tickets[k] hits 0.
     * TC: O(n * max(tickets[i])) => Worst case : TC : O(n^2), SC: O(n)
     */
    public int bruteWayTwo(int[] tickets, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < tickets.length; i++) {
            queue.offer(i);
        }

        int time = 0;
        while (tickets[k] != 0) {
            int frontIndex = queue.poll();
            tickets[frontIndex]--;
            time++;

            if (tickets[frontIndex] != 0) {
                queue.offer(frontIndex);
            }
        }
        return time;
    }

    /**
     * Optimal - Math without simulation
     * Strategy: Person k needs exactly tickets[k] full rounds.
     *   - For people BEFORE k (i <= k): they buy min(tickets[i], tickets[k]) — they either
     *     finish early or last exactly as long as k.
     *   - For people AFTER k (i > k): they get one fewer round since k exits before them
     *     in the final round → min(tickets[i], tickets[k] - 1).
     * TC: O(n), SC: O(1)
     */
    public int best(int[] tickets, int k) {
        int ticketsNeededByKthPerson = tickets[k]; // rounds k will participate in
        int time = 0;

        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                time += Math.min(tickets[i], ticketsNeededByKthPerson);
            } else {
                time += Math.min(tickets[i], ticketsNeededByKthPerson - 1); // k exits before them on last round
            }
        }
        return time;
    }

    public int timeRequiredToBuy(int[] tickets, int k) {
        return best(tickets, k);
    }
}