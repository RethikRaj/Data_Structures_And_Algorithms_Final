class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // Observation : For each index we need the Next Greater element , and then we can calculate the number of day between currIndex and its NGE.

        int[] ans = new int[temperatures.length]; // filled with zero by default

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < temperatures.length; i++) {

            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // The NGE of index = stack.peek(),  is index = i;
                int numberOfWaitingDaysForWarmerTemp = i - stack.peek();
                ans[stack.peek()] = numberOfWaitingDaysForWarmerTemp;
                stack.pop();
            }

            stack.push(i);
        }

        return ans;
    }
}
