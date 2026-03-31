
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {
    // Brute : Using hash table/set
    // Step 1 : Loop through the first LL and store its address in set.
    // Step 2 : Loop through the second LL and check at which point the address is already present in set.

    // Better :
    // Step 1 : Find the length of both the LL.
    // Step 2 : Give headstart for the longer length LL.
    // Step 3. Now traverse both and find their intersection point

    public int getLength(ListNode head){
        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length += 1;
            curr = curr.next;
        }
        return length;
    }

    public ListNode better(ListNode headA, ListNode headB) {
        // 1. Find lengths
        int len1 = getLength(headA);
        int len2 = getLength(headB);
        
        ListNode ptr1 = headA;
        ListNode ptr2 = headB;
        // 2. Give headstart for the longer length LL.
        if(len1 < len2) {
            int diff = len2 - len1;
            for(int i = 0; i < diff; i++) {
                ptr2 = ptr2.next;
            }
        } else {
            int diff = len1 - len2;
            for(int i = 0; i < diff; i++) {
                ptr1 = ptr1.next;
            }
        }
        // 3. Now traverse both and find their intersection point
        while(ptr1 != null && ptr2 != null) {
            if(ptr1 == ptr2) {
                return ptr1;
            }
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return null; // No intersection point
    }

    // !Best : Make each pointer upon reaching its end ontinue from the head of the other list.
    public ListNode best(ListNode headA, ListNode headB) {
        ListNode ptr1 = headA;
        ListNode ptr2 = headB;

        // Each pointer traverses both lists, meeting at intersection or null
        while (ptr1 != ptr2) {
            ptr1 = (ptr1 != null) ? ptr1.next : headB;
            ptr2 = (ptr2 != null) ? ptr2.next : headA;
        }

        return ptr1; // returns null if no intersection else the intersecting node.
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // return better(headA, headB);
        return best(headA, headB);
    }
}