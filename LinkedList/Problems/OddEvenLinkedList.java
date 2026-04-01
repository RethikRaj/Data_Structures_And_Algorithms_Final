public class OddEvenLinkedList {
    
}

class Solution {
    // Solution : Just did dry run and tried to implement
    public ListNode solutionOne(ListNode head) {
        // len = 0 , 1, 2 => Simply return head
        if(head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // Now, length >= 3
        int length = 1;
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode evenHead = head.next;

        while(curr != null && curr.next != null) {
            prev.next = curr.next;
            length += 1;
            prev = curr;
            curr = curr.next;
        }
        length += 1;
        if(length % 2 == 0) {
            prev.next = evenHead;
        }else{
            prev.next = null;
            curr.next = evenHead;
        }

        return head;
    }

    // Cleaner version : TC: O(n/2)=> O(n) , SC : O(1)
    public ListNode solutionTwo(ListNode head) {
        if (head == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        // odd pointer will always be behind the even pointer . Therfore checking for null is done on even pointer, because if even is not null , then sure odd is not null.
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        return solutionOne(head);
    }
}
