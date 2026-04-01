public class FrogJumpHeights {
    public static int f(int N,  int[] heights, int currStone) {
        if (currStone == N-1) return 0;

        int path1Cost = currStone + 1 < N ?
            f(N, heights, currStone + 1) + Math.abs(heights[currStone] - heights[currStone + 1]) : 0;
        int path2Cost = currStone + 2 < N ? 
            f(N, heights, currStone + 2) + Math.abs(heights[currStone] - heights[currStone + 2]) : 0; 
        return Math.min(path1Cost, path2Cost);
    }

    public static void main(String[] args) {
        int N = 4;
        int[] heights = {10, 30, 40, 20}; // len(heights) == 1

        System.out.println(f(N, heights, 0));
    }
}
