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
    // Brute : using hash table
    public ListNode brute(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while(current != null) {
            // Check if current node is already present
            if(visited.contains(current)) {
                return current;
            }
            visited.add(current);
            current = current.next;
        }
        return null;
    }

    // best : slow and fast pointer
    public ListNode best(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect cycle and find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Phase 2: Find cycle start
                ListNode pointer = head;
                while (pointer != slow) {
                    pointer = pointer.next;
                    slow = slow.next;
                }
                return slow;  // Cycle start
            }
        }
        
        // Fast reached end of LL => No cycle.
        return null;  
    }

    public ListNode detectCycle(ListNode head) {
        // return brute(head);
        return best(head);
    }
}
