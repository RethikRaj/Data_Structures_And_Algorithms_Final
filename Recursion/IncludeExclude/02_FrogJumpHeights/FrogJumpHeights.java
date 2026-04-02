public class FrogJumpHeights {

    /**
     * Approach 1: (forward direction)
     * f(N, heights, currStone) -> returns the minimum cost to reach Nth stone from last stone.
     */
    public static int f(int N, int[] heights, int currStone) {
        // Base case: frog has reached the destination, no more cost
        if (currStone == N) return 0;

        // Option 1: Jump 1 step forward
        int path1Cost = f(N, heights, currStone + 1) + Math.abs(heights[currStone] - heights[currStone + 1]);

        // Option 2: Jump 2 steps forward (only if the target index exists)
        int path2Cost = Integer.MAX_VALUE; // !Why Integer.MAX_VALUE ? Because we are taking minimum , so an impossible case shld return Integer.Max_VALUE.
        if (currStone + 2 < heights.length) {
            path2Cost = f(N, heights, currStone + 2)+ Math.abs(heights[currStone] - heights[currStone + 2]);
        }

        // Return the minimum cost among both options
        return Math.min(path1Cost, path2Cost);
    }

    /**
     * Approach 2: (backward direction)
     * Starts from stone N and recurses backward toward stone 1.
     * f(N, heights) -> returns the minimum cost to reach Nth stone from 1st stone.
     */
    public static int f(int N, int[] heights) {
        // Base case: frog is already at stone 1, no cost needed
        if (N == 1) return 0;

        // Option 1: Frog came from stone N-1 (1-step jump)
        int path1Cost = f(N - 1, heights) + Math.abs(heights[N] - heights[N - 1]);

        // Option 2: Frog came from stone N-2 (2-step jump), only valid if N > 2
        //           N == 2 would mean coming from stone 0, which doesn't exist
        int path2Cost = Integer.MAX_VALUE; // !Why Integer.MAX_VALUE ? Because we are taking minimum , so an impossible case shld return Integer.Max_VALUE.
        if (N > 2) {
            path2Cost = f(N - 2, heights) + Math.abs(heights[N] - heights[N - 2]);
        }

        // Return the minimum cost among both options
        return Math.min(path1Cost, path2Cost);
    }

    public static void main(String[] args) {
        int N = 4;
        // Heights are 1-indexed; index 0 is unused/ignored
        int[] heights = {-1, 10, 30, 40, 20};

        // Approach 1: forward recursion starting from stone 1
        System.out.println(f(N, heights, 1));

        // Approach 2: backward recursion starting from stone N
        // System.out.println(f(N, heights));
    }
}