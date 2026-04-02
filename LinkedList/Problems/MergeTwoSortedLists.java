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
    // Iterative way 
    public ListNode iterative(ListNode list1, ListNode list2) {
        // Dummy node eliminates special-case handling for the first node
        ListNode sentinelHead = new ListNode(-1, null);
        ListNode prev = sentinelHead;

        ListNode ptr1 = list1;
        ListNode ptr2 = list2;

        while(ptr1 != null && ptr2 != null) {
            if(ptr1.val < ptr2.val) {
                prev.next = ptr1;
                ptr1 = ptr1.next;
            }else{
                prev.next = ptr2;
                ptr2 = ptr2.next;
            }

            prev = prev.next;
        }

        if(ptr1 != null) { // No need of while as in case of arrays, because we can just link
            prev.next = ptr1;
        }
        if(ptr2 != null) {
            prev.next = ptr2;
        }

        return sentinelHead.next;
    }

    // Recursive way 1 : Just iterative converted to recursive
    public void f(ListNode ptr1, ListNode ptr2, ListNode prev) {
        // Base case
        if(ptr1 != null && ptr2 == null) {
            prev.next = ptr1; 
            return;
        }
        if(ptr1 == null && ptr2 != null){
            prev.next = ptr2;
            return;
        }
        if(ptr1 == null && ptr2 == null) {
            prev.next = null;
            return;
        }

        if (ptr1.val < ptr2.val) {
            prev.next = ptr1;
            f(ptr1.next, ptr2, prev.next);
            
        } else { 
            prev.next = ptr2;
            f(ptr1, ptr2.next, prev.next);
        }
    }

    // Recursive way 2 : (Important to learn this also) -> Purely recursive thinking (VIMPPP)
    public ListNode f(ListNode ptr1, ListNode ptr2) {
        // Base case
        if(ptr1 != null && ptr2 == null) return ptr1;
        if(ptr1 == null && ptr2 != null) return ptr2;
        if(ptr1 == null && ptr2 == null) return null;

        // Recursion 
        ListNode recAnsHead = null;
        if (ptr1.val < ptr2.val) {
            recAnsHead = f(ptr1.next, ptr2);
            ptr1.next = recAnsHead;
            return ptr1;
        } else { 
            recAnsHead = f(ptr1, ptr2.next);
            ptr2.next = recAnsHead;
            return ptr2;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // ListNode sentinelHead = new ListNode(-1);
        // f(list1, list2, sentinelHead);
        // return sentinelHead.next;

        // Recursive way 2
        return f(list1, list2);
    }
}