class Solution {
    public int[] findNSEIndexes(int[] heights) {
        int n = heights.length;

        int[] nseIndexes = new int[n];
        Arrays.fill(nseIndexes, n);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < n; i++) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                nseIndexes[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        return nseIndexes;
    }

    public int[] findPSEIndexes(int[] heights) {
        int n = heights.length;

        int[] pseIndexes = new int[n];
        Arrays.fill(pseIndexes, -1);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = n - 1; i >= 0 ; i--) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                pseIndexes[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        return pseIndexes;
    }

    public int largestRectangleArea(int[] heights) {
        // Find index of NSE for all elements
        int[] nseIndexes = findNSEIndexes(heights);

        // Find index of PSE for all elements
        int[] pseIndexes = findPSEIndexes(heights);

        // Find maximum area
        int maxArea = 0;
        for(int i = 0 ; i < heights.length; i++) {
            int width = nseIndexes[i] - pseIndexes[i] - 1;
            int currArea = heights[i] * width;

            maxArea = Math.max(currArea, maxArea);
        }

        return maxArea;
    }
}
