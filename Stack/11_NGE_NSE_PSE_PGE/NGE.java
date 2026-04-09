import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    // Brute Force : Two loops

    /**
     * Better 1: Left-to-Right traversal using a Monotonic Stack
     *
     * Intuition:
     *   Traverse left to right. The stack holds indices of elements that
     *   !are still "waiting" for their next greater element*. (elements whose NGE is yet to be found)
     *   At each arr[i] , we ask a question can arr[i] be the NGE of stack's top ?
     *          - Yes (arr[i] > arr[stack' top]) => We found NGE for stack's top => ans[stack's top] = arr[i] and then pop.
     *          - No  => Push the current index(i) as a new waiting candidate.
     * Time  : O(n)  — each element is pushed and popped at most once
     * Space : O(n)  — stack + answer list
     */
    public ArrayList<Integer> better(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            ans.add(-1); // default: no greater element found
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>(); // stores indices of "pending" elements

        for (int i = 0; i < arr.length; i++) {
            // arr[i] is greater than elements at pending indices(stack's top)
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                ans.set(stack.peek(), arr[i]); // arr[i] is the next greater element
                stack.pop();                   // this index is no longer pending
            }

            // stack is empty or arr[i] is <= stack top : push as a new pending candidate
            stack.push(i);
        }

        // Any indices still in the stack have no greater element → ans remains -1
        return ans;
    }

    /**
     * Better 2: Right-to-Left traversal using a Monotonic Stack
     *
     * Intuition:
     *   Traverse from the right to left. 
     *   !The stack holds indices of elements that can be a possible NGE of upcoming elements.
     *   At each arr[i], we ask a question can arr[stack's top] be the NGE of arr[i] ?
     *          - Yes(arr[stack' top] > arr[i]) - We found the NGE of arr[i] => ans[i] = arr[stack' top] and then push arr[i] because it can be a possible NGE also.
     *          - No => pop till we get `yes` and then push arr[i]
     *
     * Time  : O(n)  — each element is pushed and popped at most once
     * Space : O(n)  — stack + answer list
     */
    public ArrayList<Integer> betterTwo(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            ans.add(-1); // default: no greater element found
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>(); // stores indices, not values

        int i = arr.length - 1;
        while (i >= 0) {
            // Pop all indices whose values are <= arr[i]: they cannot be the next greater element for index i
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

    

    public ArrayList<Integer> nextLargerElement(int[] arr) {
        // return better(arr);    // L->R approach
        return betterTwo(arr);    // R->L approach
    }
}