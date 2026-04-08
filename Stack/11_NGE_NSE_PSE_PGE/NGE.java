import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    // Brute Force : Two loops

    /**
     * Better 1: Right-to-Left traversal using a Monotonic Stack
     *
     * Intuition:
     *   Traverse from the last element to the first. For each element,
     *   maintain a stack of indices whose elements are "candidates" for
     *   being the next greater element of future (leftward) indices.
     *   Pop elements from the stack that are <= current element since
     *   they can never be the next greater element for anything to the left.
     *
     * Time  : O(n)  — each element is pushed and popped at most once
     * Space : O(n)  — stack + answer list
     */
    public ArrayList<Integer> better(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            ans.add(-1); // default: no greater element found
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>(); // stores indices, not values

        int i = arr.length - 1;
        while (i >= 0) {
            // Pop all indices whose values are <= arr[i]:
            // they cannot be the next greater element for index i
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }

            // Top of stack (if any) is the nearest greater element to the right
            if (!stack.isEmpty()) {
                ans.set(i, arr[stack.peek()]);
            }
            // else: ans[i] remains -1 (no greater element exists)

            stack.push(i); // push current index for elements further left
            i--;
        }

        return ans;
    }

    /**
     * Better 2: Left-to-Right traversal using a Monotonic Stack
     *
     * Intuition:
     *   Traverse left to right. The stack holds indices of elements that
     *   !are still "waiting" for their next greater element*.
     *   Whenever arr[i] is greater than the element at the stack's top,
     *   arr[i] IS the next greater element for that index — record it and pop.
     *   Repeat until the stack top is >= arr[i] or the stack is empty,
     *   then push the current index as a new waiting candidate.
     *
     * Time  : O(n)  — each element is pushed and popped at most once
     * Space : O(n)  — stack + answer list
     */
    public ArrayList<Integer> betterTwo(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            ans.add(-1); // default: no greater element found
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>(); // stores indices of "pending" elements

        for (int i = 0; i < arr.length; i++) {
            // arr[i] is greater than elements at pending indices — resolve them
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                ans.set(stack.peek(), arr[i]); // arr[i] is the next greater element
                stack.pop();                   // this index is no longer pending
            }

            // arr[i] is <= stack top (or stack is empty): push as a new pending candidate
            // its next greater element will be found in a future iteration
            stack.push(i);
        }

        // Any indices still in the stack have no greater element → ans remains -1

        return ans;
    }

    public ArrayList<Integer> nextLargerElement(int[] arr) {
        // return better(arr);    // R→L approach
        return betterTwo(arr);    // L→R approach
    }
}