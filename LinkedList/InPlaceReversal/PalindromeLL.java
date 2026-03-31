public class PalindromeLL {
    
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
    // Brute 1 : 
    // Step 1 : Copy LL to array 
    // Step 2 : Check palindrome using array.

    // Brute 2 : TC : O(n), SC:O(n)
    // Step 1 : Copy the original LL and then reverse the copied LL.
    // Step 2 : Now, check whether the reversedCopyLL == original LL.

    // Best approach : 
    // Step 1 : Reverse first half of LL. => Therefore , need to find middle node.
    // Step 2 : Traverse both halves simultaneously and compare corresponding nodes.

    public boolean isPalinBest(ListNode head) {
        // Single node , then return true
        if(head.next == null) {
            return true;
        }

        // 1. Find middle node
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow;

        // 2. Reverse till before middle node.
        ListNode prev = null;
        ListNode current = head;
        while(current != mid) {
            ListNode succ = current.next;
            current.next = prev;
            prev = current;
            current = succ;
        }

        // 3. Traverse both halves simultaneously and compare corresponding nodes.
        ListNode ptr1 = prev;
        ListNode ptr2 = current;
        // !I observed that while dry running if length of LL is odd then i need to move ptr2 by one node.
        if(fast != null) { // meaning that the length is odd
            ptr2 = ptr2.next;
        }

        while(ptr1 != null && ptr2 != null) {
            if(ptr1.val != ptr2.val) {
                return false;
            }
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return true;

    }

    // Best approach 2 : ( Here we won't get the odd length edge case.)
    // Step 1 : Reverse second half of LL.
    // Step 2 : Traverse both half simultaneously and compare corresponding nodes.


    public boolean isPalindrome(ListNode head) {
        return isPalinBest(head);
    }
}
