import java.util.*;

class Solution {
    // Better : TC : O(n), SC : O(n)
    public int[] better(int[] nums) {
        int n = nums.length;
        
        // Create an array double the size of nums and copy nums two times
        int[] temp = new int[n * 2];
        for(int i = 0; i < n * 2; i++) {
            temp[i] = nums[i % nums.length];
        }

        // Find NGE of all elements in temp
        int[] ans = new int[n * 2];
        Arrays.fill(ans, -1);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < n * 2 ; i++) {
            while(!stack.isEmpty() && temp[i] > temp[stack.peek()]) {
                ans[stack.peek()] = temp[i];
                stack.pop();
            }
            stack.push(i);
        }

        // Return NGE of first `n` elements of temp
        int[] finalAns = new int[n];

        for(int i = 0 ; i < nums.length; i++) {
            finalAns[i] = ans[i];
        }

        return finalAns;
    }

    // Optimal
    // Optimal code 1 : TC : O(n) , SC : O(1)
    public int[] optimalOne(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i = 0 ; i < 2 * n ; i++) {
            int j = i % n; // ! IMPPP
            while(!stack.isEmpty() && nums[j] > nums[stack.peek()]) {
                ans[stack.peek()] = nums[j];
                stack.pop();
            }

            // stack.push(j); // This works but below check is good
            
            // Avoid pushing duplicates in second pass
            if(i < n) {
                stack.push(currIndex);
            }
        }

        return ans;
    }

    // Optimal Code 2 ( I just did dry run ) : Two iteration but at second iteration don't push into stack
    // TC : O(n) , SC : O(1) 
    public int[] optimalTwo(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        // -------------------------------
        // First Pass (Normal NGE logic)
        // -------------------------------
        // Find next greater element on right side
        for(int i = 0 ; i < nums.length ; i++) {
            // If current element is greater than stack top element then it is the next greater for stack top
            while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ans[stack.peek()] = nums[i];
                stack.pop();
            }

            // Push index into stack
            stack.push(i);
        }

        // -----------------------------------
        // Second Pass (Handle Circular Array)
        // -----------------------------------
        // Try to resolve remaining elements in stack by checking from beginning of array
        for(int i = 0 ; i < nums.length ; i++) {
            // Same logic as first pass
            while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ans[stack.peek()] = nums[i];
                stack.pop();
            }

            // Don't push again.
        }

        // Remaining elements in stack have no NGE (remain -1)
        return ans;
    }

    public int[] nextGreaterElements(int[] nums) {
        // return better(nums);
        return optimalOne(nums);
        // return optimalTwo(nums);
    }
}
