import java.util.*;
import java.io.*;

class Node {
    int val;   // stores an index into arr[], not the actual value
    Node next;

    Node(int val) {
        this.val = val;
    }
}

public class NStacksInArray {
    private int[] arr;        // shared storage for all N stacks
    private int capacity;

    private Node[] top;       // top[k] = head of linked list tracking k'th stack elements (by index)

    private ArrayDeque<Integer> emptyIndices;  // free indexes in arr[] available for new pushes

    /*
     * N stacks sharing a single array of size S.
     * Each stack is tracked via a linked list of indices (not values),
     * so any stack can use any free slot — no fixed partitioning.
     */
    public NStack(int N, int S) {
        arr = new int[S];
        capacity = S;
        top = new Node[N];

        emptyIndices = new ArrayDeque<>();
        for (int i = 0; i < S; i++) {
            emptyIndices.push(i);  // initially, every slot is free
        }
    }

    // Pushes X into the Mth stack (1-indexed). Returns false if array is full.
    public boolean push(int x, int m) {
        if (emptyIndices.isEmpty()) return false;

        int emptyIndex = emptyIndices.pop();  // claim a free slot
        arr[emptyIndex] = x;

        // prepend a node pointing to this slot onto stack m's linked list
        Node temp = new Node(emptyIndex);
        temp.next = top[m - 1];
        top[m - 1] = temp;

        return true;
    }

    // Pops from Mth stack. Returns -1 if empty.
    public int pop(int m) {
        if (top[m - 1] == null) return -1;

        int topEleIndex = top[m - 1].val;  // index in arr[] where top value lives
        top[m - 1] = top[m - 1].next;      // move stack's head pointer down

        int poppedEle = arr[topEleIndex];
        emptyIndices.push(topEleIndex);     // slot is free again

        return poppedEle;
    }
}
