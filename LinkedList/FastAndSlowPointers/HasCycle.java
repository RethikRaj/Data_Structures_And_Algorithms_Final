import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {
    // Brute Approach (Using hash table)
    public boolean brute(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while(current != null) {
            // Check if current node is already present
            if(visited.contains(current)) {
                return true;
            }
            visited.add(current);
            current = current.next;
        }
        return false;
    }

    // Best Approach (Slow and fast Pointers)
    public boolean best(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If they meet, there's a cycle
            if (slow == fast) {
                return true;
            }
        }

        // Fast reached the end, no cycle
        return false;
    }

    public boolean hasCycle(ListNode head) {
        return brute(head);
        // return best(head);
    }
}