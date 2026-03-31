public class ReverseLinkedList {
    
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    // Iterative solution

    public ListNode reverseListIterative(ListNode head) {
        // Empty or single node
        if(head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            ListNode succ = current.next; // Save next node
            current.next = prev; // Reverse the pointer
            prev = current; // Move prev forward
            current = succ; // Move curr forward;
        }

        return prev;
    }

    public ListNode reverseListRecursive(ListNode current, ListNode prev) {
        // Base case
        if (current == null) {
            return prev;
        }

        // 1 case me solve => reverse current node
        ListNode succ = current.next;
        current.next = prev;
        prev = current;
        current = succ;
        // Remaining recursion will handle
        return reverseListRecursive(current, prev);
    }

    // IMP : Backtracking
    // f(current) -> Reverses the LL starting from current node to the end.
    public ListNode reverseListRecursiveTwo(ListNode current) {
        // Base case
        if( current == null || current.next == null) {
            return current;
        }

        // Make recursion do the work of the reversing current.next node to the end
        ListNode newHead = reverseListRecursiveTwo(current.next);

        // Backtrack (My work : 1 case me solve)
        current.next.next = current;
        current.next = null;

        return newHead;
        
    }

    public ListNode reverseList(ListNode head) {
        // return reverseListIterative(head);
        // return reverseListRecursive(head, null);
        return reverseListRecursiveTwo(head);
    }
}