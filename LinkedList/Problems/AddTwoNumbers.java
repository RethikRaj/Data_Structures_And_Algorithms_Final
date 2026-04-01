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
    // iterative solution
    public ListNode solutionOne(ListNode l1, ListNode l2) {
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        int carry = 0;
        ListNode sentinelHead = new ListNode(-1, null);
        ListNode curr = sentinelHead;

        while(ptr1 != null && ptr2 != null) {
            int sum = ptr1.val + ptr2.val + carry;
            carry = sum / 10;
            int lastDigit = sum % 10;

            curr.next = new ListNode(lastDigit, null);
            curr = curr.next;

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        while(ptr1 != null) {
            int sum = ptr1.val + carry;
            carry = sum / 10;
            int lastDigit = sum % 10;
            curr.next = new ListNode(lastDigit, null);
            curr = curr.next;
            ptr1 = ptr1.next;
        }

        while(ptr2 != null) {
            int sum = ptr2.val + carry;
            carry = sum / 10;
            int lastDigit = sum % 10;
            curr.next = new ListNode(lastDigit, null);
            curr = curr.next;
            ptr2 = ptr2.next;
        }


        // !At last carry check . {9,9} + {9,9}
        if(carry != 0) {
            curr.next = new ListNode(carry, null);
            curr = curr.next;
        }

        return sentinelHead.next;
    }
    
    // The same above code can be written in a cleaner(shorter) way like this : (Both will have same TC/SC so dont worry)
    public ListNode solutionTwo(ListNode l1, ListNode l2) {
        ListNode sentinelHead = new ListNode(-1);
        ListNode current = sentinelHead;
        int carry = 0;
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        while (ptr1 != null || ptr2 != null || carry != 0) {
            int val1 = (ptr1 != null) ? ptr1.val : 0;
            int val2 = (ptr2 != null) ? ptr2.val : 0;
            int sum = val1 + val2 + carry;

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;

            if (ptr1 != null) ptr1 = ptr1.next;
            if (ptr2 != null) ptr2 = ptr2.next;
        }

        return sentinelHead.next;
    }

    // Recursive solution
    public void f(ListNode ptr1, ListNode ptr2, ListNode curr, int carry) {
        // Base case
        if(ptr1 == null && ptr2 == null && carry == 0) return;

        // 1 case me solve
        int val1 = (ptr1 != null) ? ptr1.val : 0;
        int val2 = (ptr2 != null) ? ptr2.val : 0;
        int sum = val1 + val2 + carry;

        curr.next = new ListNode(sum % 10);
        // Remaining resursion will handle
        ptr1 = 
        ptr2 = 
        f(
            (ptr1 != null) ? ptr1.next : ptr1, 
            (ptr2 != null) ? ptr2.next : ptr2, 
            curr.next, 
            sum / 10
        );
    }



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // return solutionOne(l1, l2);
        // return solutionTwo(l1, l2);

        // Recursive way
        ListNode sentinelHead = new ListNode(-1);
        f(l1, l2, sentinelHead, 0);
        return sentinelHead.next;
    }
}