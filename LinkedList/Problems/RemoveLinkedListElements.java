public class RemoveLinkedListElements {
    
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
    // Normal way 
    public ListNode normal(ListNode head, int val) {
        // Edge cases
        if(head == null) return head;

        // Handle head.val == val => Don't handle here because we might have a case where we repeatedly need to change head.
        // For example : LL = 6->6->6->1->2->3  ; val = 6

        ListNode prev = null;
        ListNode current = head;
        while(current != null) {
            if(current.val == val) {
                ListNode succ = current.next;
                // Edge case 
                if(current == head) { // meaning head.val = val
                    current.next = null;
                    head = succ;
                    current = succ;
                }else {
                    prev.next = succ;
                    current.next = null;
                    current = succ;
                }
            } else {
                prev = current;
                current = current.next;
            }
        }
        return head;
    }
    
    // Sentinel way
    public ListNode sentinel(ListNode head, int val) {
        if(head == null) return null;

        ListNode sentinelHead = new ListNode(-1);
        sentinelHead.next = head;

        ListNode prev = sentinelHead;
        ListNode current = sentinelHead.next;
        while(current != null) {
            if(current.val == val) {
                ListNode succ = current.next;
                prev.next = succ;
                current.next = null; // isolation
                current = succ;
            } else {
                prev = current;
                current = current.next;
            }
        }

        return sentinelHead.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        // return normal(head, val);
        return sentinel(head, val);
    }
}