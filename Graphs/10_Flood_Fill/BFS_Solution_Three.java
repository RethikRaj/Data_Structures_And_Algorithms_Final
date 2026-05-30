// BFS + (using given matrix itself as visited)
 /*Key Insight: No separate visited array needed!
 *   → We recolor each cell as soon as we dequeue it.
 *   → Recolored cells no longer match originalColor, so they'll never be enqueued again — the matrix itself acts as visited.
 *   → Thus checking image[adjRow][adjCol] == originalColor inherently also checks visited[ajRow][adjCol] == false.
 *
 * Why early exit (originalColor == color) is MANDATORY:
 *   → If originalColor == newColor, recoloring changes nothing,
 *     so the "visited" trick breaks — cells keep matching and
 *     get enqueued forever → infinite loop.
 */

class Pair {
    int row;
    int col;
    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = { 0, 0, -1, 1};

    private void bfs(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;

        int originalColor = image[sr][sc];
        if(originalColor == color) return; // Early exit (MUST)

        Queue<Pair> q = new ArrayDeque<>();

        Pair src = new Pair(sr, sc);
        q.offer(src);

        while(!q.isEmpty()) {
            Pair front = q.poll();

            image[front.row][front.col] = color;

            // Explore adjacents            
            for(int i = 0; i < DELTA_ROW.length;i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                Pair temp = new Pair(adjRow, adjCol);

                if(adjRow < m && adjCol < n && adjRow >= 0 && adjCol >= 0 && image[adjRow][adjCol] == originalColor) {
                    q.offer(temp);
                }
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        bfs(image, sr, sc, color);
        return image;
    }
}
