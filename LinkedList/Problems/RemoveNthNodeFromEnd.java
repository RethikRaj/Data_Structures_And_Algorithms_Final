public class RemoveNthNodeFromEnd {
    
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
    // recursive way : TC : O(n) but only one pass , SC : O(n) 
    static class MyInteger {
        int num;
        MyInteger(int num) {
            this.num = num;
        }
    }

    public void f(ListNode prev, ListNode curr, MyInteger n) {
        // Base case 
        if(curr == null) return;

        // Recursion
        f(curr, curr.next, n);
        n.num = n.num - 1;
        if(n.num == 0) {
            // delete it 
            ListNode succ = curr.next;
            prev.next = curr.next;
            curr.next = null;
        }
    }

    // iterative way : Two Pass
    public int getLength(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while(curr != null) {
            length += 1;
            curr = curr.next;
        }
        return length;
    }

    // index = indexFromStart
    public void deleteAtIndex(ListNode sentinelHead, int index) {
        ListNode prev = sentinelHead;
        ListNode curr = sentinelHead.next;

        for(int i = 0; i < index; i++) {
            prev = curr;
            curr = curr.next;
        }

        ListNode succ = curr.next;
        prev.next = succ;
        curr.next = null;
    }

    public ListNode iterativeWay(ListNode head, int n) {
        ListNode sentinelHead = new ListNode(-1);
        sentinelHead.next = head;

        // First pass: count total nodes
        int length = getLength(head);

        int startIndex = length - n;

        // Second pass :
        deleteAtIndex(sentinelHead, startIndex);

        return sentinelHead.next;
    }

    // !Iterative way : One Pass (Two Pointer)
    // If we place two pointers n nodes apart and then advance both at the same speed, when the lead pointer reaches the end, the trailing pointer will be exactly at the node before the one we want to remove.
    // If sentinel is used , then we need to keep them n+1 nodes apart.
    public ListNode OnePassIterative(ListNode head, int n) {
        ListNode sentinelHead = new ListNode(-1, head);

        ListNode slow = sentinelHead;
        ListNode fast = sentinelHead;

        // Advance fast by n + 1 steps to create the gap
        for(int i = 0 ;  i < n + 1; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow is now the node before the target, skip or delete(isolation) the target
        slow.next = slow.next.next; // Here i didn't do isolation, just to show you we can do it without isolation also but it is a best practice to do isolation

        return sentinelHead.next;
    }


    

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // return iterativeWay(head, n);

        return OnePassIterative(head, n);

        // Recursive way
        // ListNode sentinelHead = new ListNode(-1);
        // sentinelHead.next = head;

        // f(sentinelHead, sentinelHead.next, new MyInteger(n));
        // return sentinelHead.next;

    }
}
