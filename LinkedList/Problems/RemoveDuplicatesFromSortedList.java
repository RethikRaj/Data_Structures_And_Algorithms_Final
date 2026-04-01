class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Empty or single node in LL => No duplicates
        if (head == null || head.next == null) return head;

        // Now, len(list) >= 2
        ListNode prev = head;
        ListNode curr = head.next;

        while(curr != null) {
            if(prev.val == curr.val) {
                ListNode succ = curr.next;
                prev.next = succ;
                curr.next = null; // isolate
                curr = succ;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        return head;
    }
}