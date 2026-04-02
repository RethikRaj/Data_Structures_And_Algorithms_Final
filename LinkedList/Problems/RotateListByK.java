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
    // Brute : Rotate right by one , k times : TC:O(k*n), SC:O(1)
    public ListNode rotateRightByOne(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        
        curr.next = head;
        head = curr;
        prev.next = null;
        return head;
    }

    public ListNode brute(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        for(int i = 0 ; i < k ; i++) {
            head = rotateRightByOne(head, k);
        }
        return head;
    }

    // Approach 2 : (Reversing Approach) (Ugly code)
    public int getLength(ListNode head) {
        int count = 0;
        ListNode curr = head;
        while(curr != null) {
            count += 1;
            curr = curr.next;
        }
        return count;
    }
    
    static class Ans {
        ListNode prev;
        ListNode curr;
        Ans(ListNode prev, ListNode curr) {
            this.prev = prev;
            this.curr = curr;
        }
    }

    public Ans reverse(ListNode start, int numberOfNodesToReverse) {
        ListNode prev = null;
        ListNode curr = start;
        
        for(int i = 0 ; i < numberOfNodesToReverse ; i++) {
            ListNode succ = curr.next;
            curr.next = prev;
            prev = curr;
            curr = succ;
        }
        return new Ans(prev, curr);
    } 

    public ListNode ApproachTwoUgly(ListNode head, int k) {
        int n = getLength(head);
        k = k % n;
        // Step 1: reverse first n - k nodes
        Ans a1 = reverse(head, n-k);
        ListNode head1 = a1.prev;
        // Step 2 : reverse last k nodes
        Ans a2 = reverse(a1.curr, k);
        ListNode head2 = a2.prev;

        // Step 3 : connect them
        head.next = head2;

        // Step 4 : reverse whole list
        ListNode finalHead = reverse(head1, n).prev;

        return finalHead;
    }
    
    // Approach 3 : (Finding split point & stitch approach)
    public ListNode splitAndStitch(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        // 1. Find length and tail
        int length = 0;
        ListNode tail = head;
        while(tail.next != null) {
            length += 1;
            tail = tail.next;
        }
        length += 1; // Why += 1 ? See condition i did tail.next != null to find tail

        // Compute effective rotation
        k = k % length;
        if (k == 0) return head;
        
        // 2. Find split point and split list
        ListNode ptr = head;
        // For Split point we need move n-k-1 times
        for(int i = 0 ; i < length-k-1 ;  i++) {
            ptr = ptr.next;
        }

        ListNode finalHead = ptr.next;
        ptr.next = null; // split it

        // 3. Stitch
        tail.next = head;

        return finalHead;

    }

    public ListNode rotateRight(ListNode head, int k) {
        // return brute(head, k);

        // Approach 2:
        // return ApproachTwoUgly(head, k);

        // Approach 3 :
        return splitAndStitch(head, k);

    }
}
