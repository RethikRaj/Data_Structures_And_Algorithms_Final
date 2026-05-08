public class FrogJumpHeights {

    /**
     * Forward recursion: min cost to reach the last stone from index i.
     * At each step, try jumping +1 or +2; pick the cheaper path.
     * TC: O(2^n) — overlapping subproblems, no memoization yet.
     */
    public static int f(int i, int[] heights) {
        if (i == heights.length - 1) return 0; // already at destination

        // jump +1 always valid
        int path1 = f(i + 1, heights) + Math.abs(heights[i] - heights[i + 1]);

        // jump +2 only if within bounds
        int path2 = Integer.MAX_VALUE; // sentinel: treat out-of-bounds as infinitely costly
        if (i + 2 < heights.length) {
            path2 = f(i + 2, heights) + Math.abs(heights[i] - heights[i + 2]);
        }

        return Math.min(path1, path2);
    }

    /**
     * Backward recursion: min cost to reach stone i from stone 0.
     * Same logic as f(), but builds the answer from the destination back to the source.
     * TC: O(2^n) — overlapping subproblems, no memoization yet.
     */
    public static int g(int i, int[] heights) {
        if (i == 0) return 0; // cost to reach start from start is zero

        // came from i-1 (always valid)
        int path1 = g(i - 1, heights) + Math.abs(heights[i] - heights[i - 1]);

        // came from i-2 (only if it exists)
        int path2 = Integer.MAX_VALUE;
        if (i - 2 >= 0) {
            path2 = g(i - 2, heights) + Math.abs(heights[i] - heights[i - 2]);
        }

        return Math.min(path1, path2);
    }

    public static void main(String[] args) {
        int[] heights = {10, 30, 40, 20};

        System.out.println(f(0, heights));                  // forward:  answer = 20
        System.out.println(g(heights.length - 1, heights)); // backward: answer = 20
    }
}