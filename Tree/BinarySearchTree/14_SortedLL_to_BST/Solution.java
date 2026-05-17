class Solution {
    // ─── Method One: Slow/Fast Pointer to find mid ───────────────────────────
    // TC: O(n log n) — findMid O(n) called at each level, O(log n) levels
    // SC: O(log n)   — recursion stack depth (balanced BST height)
    private TreeNode f(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        // Slow/fast pointer: slow lands on mid, prev trails one behind
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        ListNode mid = slow;
        prev.next = null;           // Cut list: [head, mid) | [mid.next, end]

        TreeNode root = new TreeNode(mid.val);
        root.left  = f(head);       // Build left subtree from left half
        root.right = f(mid.next);   // Build right subtree from right half
        return root;
    }

    // ─── Method Two: Convert sorted LL → sorted array → BST ─────────────────
    // TC: O(n)      — O(n) to build array + O(n) to build BST
    // SC: O(n)      — array storage + O(log n) recursion stack → O(n) dominates
    private TreeNode methodTwo(ListNode head) {
        // Step 1: Flatten LL into array
        List<Integer> arr = new ArrayList<>();
        ListNode temp = head;
        while(temp != null) {
            arr.add(temp.val);
            temp = temp.next;
        }

        // Step 2: Build balanced BST from sorted array using mid as root
        return buildBST(arr, 0, arr.size() - 1);
    }

    private TreeNode buildBST(List<Integer> arr, int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(arr.get(mid));
        root.left  = buildBST(arr, start, mid - 1);
        root.right = buildBST(arr, mid + 1, end);
        return root;
    }


    // ─── Method Three: In-order pointer walk ─────────────────────────────────
    // TC: O(n)      — each node visited exactly once via ptr advancement
    // SC: O(log n)  — recursion stack depth (balanced BST height)

    private ListNode ptr;   // Shared pointer; advances in-order (left→root→right)

    private int getLength(ListNode head) {
        int length = 0;
        for (ListNode p = head; p != null; p = p.next) length++;
        return length;
    }

    // Builds BST for `count` nodes using current ptr position
    private TreeNode g(int count) {
        if (count == 0 || ptr == null) return null;

        TreeNode root = new TreeNode();

        // Floor → left gets fewer nodes when count-1 is odd
        root.left = g((int) Math.floor((count - 1) / 2.0));

        // Assign current list value after left subtree is fully built
        root.val = ptr.val;
        ptr = ptr.next;

        root.right = g((int) Math.ceil((count - 1) / 2.0));
        return root;
    }

    private TreeNode methodThree(ListNode head) {
        ptr = head;
        return g(getLength(head));
    }

    public TreeNode sortedListToBST(ListNode head) {
        // return f(head);             // Method One   — O(n log n) TC, O(log n) SC
        // return methodTwo(head);  // Method Two   — O(n)       TC, O(n)      SC
        return methodThree(head);// Method Three — O(n)       TC, O(log n)  SC
    }
}