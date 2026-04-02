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
    public ListNode iterative(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode sentinelHead = new ListNode(-1, head);
        ListNode prev = sentinelHead;
        ListNode curr = sentinelHead.next;

        while(curr != null && curr.next != null) {
            ListNode temp = curr.next.next;
            prev.next = curr.next;
            curr.next.next = curr;
            curr.next = temp;

            // Updation
            prev = curr;
            curr = curr.next;
        }

        return sentinelHead.next;
    }

    // Recursive way
    public ListNode f(ListNode curr) {
        // Base case : fewer than 2 nodes, nothing to swap
        if(curr == null || curr.next == null) return curr;

        ListNode first = curr;
        ListNode second = curr.next;

        // Recursively swap the rest of the list
        ListNode recHead = swapPairs(second.next);
        first.next = recHead;
        second.next = first;

        // second is now the head of this swapped pair
        return second;
    }

    public ListNode swapPairs(ListNode head) {
        // return iterative(head);

        // Recursive way

        return f(head);
    }
}