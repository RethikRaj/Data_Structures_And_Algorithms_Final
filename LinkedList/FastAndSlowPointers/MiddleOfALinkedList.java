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
    // Brute 
    // Step 1 : Traverse the linked list, storing each node in an array.
    // Step 2 : Find the middle index: mid = array.length / 2.
    // Step 3 : Return the node at array[mid].

    // Better
    // Step 1 : Get length = n
    // Step 2 : Walk to the (n/2)th node and return 

    // Best (Slow fast pointer)
    // For even lists , the below function returns the second middle node.
    // If we want first middle node , track slow's previous node.
    public ListNode better(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Move until fast reaches the end
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move slow by 1
            fast = fast.next.next;     // Move fast by 2
        }

        return slow;  // Slow is at the middle
    }

    public ListNode middleNode(ListNode head) {
        return better(head);
    }
}