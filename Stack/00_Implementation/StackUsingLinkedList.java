

// ============================================================
// APPROACH 1: Stack using DOUBLY Linked List (DLL)
// ------------------------------------------------------------
// Stack top = TAIL end of the list
// push() → insertAtTail()
// pop()  → deleteAtTail()
//
// WHY DLL? Because we're working at the TAIL.
// After deleting the tail node, we need to move back to the previous node => We need a prev pointer.
// ============================================================

class Node {
    int val;
    Node next;
    Node prev;

    Node(int val) {
        this.val = val;
    }
}

class LinkedListStack {
    private Node sentinelHead;
    private Node sentinelTail;

    public LinkedListStack() {
        sentinelHead = new Node(-1);
        sentinelTail = new Node(-1);
        sentinelHead.next = sentinelTail;
        sentinelTail.prev = sentinelHead;
    }

    public void push(int val) {
        Node newNode = new Node(val);
        newNode.next = sentinelTail;
        newNode.prev = sentinelTail.prev;
        sentinelTail.prev.next = newNode;
        sentinelTail.prev = newNode;
    }

    public int pop() {
        if (sentinelHead.next == sentinelTail) {
            System.out.println("No elements in stack : Stack underflow");
            return -1;
        }
        Node toDelete = sentinelTail.prev;
        toDelete.prev.next = toDelete.next;
        sentinelTail.prev = toDelete.prev;
        toDelete.next = null;
        toDelete.prev = null;
        return toDelete.val;
    }

    public int peek() {
        if (sentinelTail.prev == sentinelHead) {
            System.out.println("No elements in stack : Stack Underflow");
            return -1;
        }
        return sentinelTail.prev.val;
    }

    public boolean isEmpty() {
        return sentinelHead.next == sentinelTail;
    }
}


// ============================================================
// APPROACH 2: Stack using SINGLY Linked List (SLL)
// ------------------------------------------------------------
// Stack top = HEAD of the list
// push() → insertAtHead()
// pop()  → deleteAtHead()
//
// WHY SLL is enough here? Because we're working at the HEAD.
// Deleting head only needs: head = head.next
// No need to go backward → no prev pointer needed.
// 
// SIMPLER and uses less memory per node (no .prev pointer).
// ============================================================
class SLLNode {
    int val;
    SLLNode next;

    SLLNode(int val) {
        this.val = val; 
    }
}

class LinkedListStackTwo {
    SLLNode head; // head IS the top of the stack

    void push(int val) {
        SLLNode node = new SLLNode(val);
        node.next = head; // new node points to old top
        head = node;      // new node becomes the new top
    }

    int pop() {
        if (head == null) {
            System.out.println("Stack Underflow");
            return -1;
        }
        int val = head.val;
        head = head.next; // just move head forward — O(1), no .prev needed
        return val;
    }

    int peek() {
        if (head == null) return -1;
        return head.val; // top is always head
    }

    boolean isEmpty() {
        return head == null;
    }
}

// ============================================================
// COMPARISON SUMMARY
// ============================================================
//
//  Feature              DLL (Approach 1)          SLL (Approach 2)
//  -----------------    ----------------------    ----------------
//  Top of stack         Tail                      Head
//  Core operations      insertAtTail/deleteAtTail  insertAtHead/deleteAtHead
//  Needs .prev?         YES (to retreat from tail) NO (head.next is enough)
//  Sentinel nodes?      YES (cleaner tail ops)     NO (null check is fine)
//  Memory per node      More (val+next+prev)       Less (val+next)
//  Code complexity      Higher                     Simpler 
//  Time complexity      O(1) all ops               O(1) all ops
//
//  KEY INSIGHT:
//  DLL was only needed because Approach 1 chose the TAIL as the top.
//  If you choose the HEAD as the top (Approach 2), a SLL is sufficient.
//  Both are O(1) — Approach 2 is just simpler and more memory-efficient.
// ============================================================

public class StackUsingLinkedList {
    public static void main(String[] args) {
        LinkedListStack s = new LinkedListStack();
        System.out.println(s.pop());
        System.out.println(s.peek());
        System.out.println(s.isEmpty());
    }
}