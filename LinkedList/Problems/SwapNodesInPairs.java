

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
    public ListNode swapPairs(ListNode head) {
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
}
