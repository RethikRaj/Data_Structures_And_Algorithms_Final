// BFS + (using given matrix itself as visited)
 /*
 * Key Insight: No separate visited array needed!
 *   → We recolor each cell as soon as we dequeue it.
 *   → Recolored cells no longer match originalColor, so they'll never be enqueued again — the matrix itself acts as visited.
 *   → Thus checking image[adjRow][adjCol] == originalColor inherently also checks visited[ajRow][adjCol] == false.
 *
 * Why early exit (originalColor == color) is MANDATORY:
 *   → If originalColor == newColor, recoloring changes nothing,
 *     so the "visited" trick breaks — cells keep matching and
 *     get enqueued forever → infinite loop.
 */

class Vertex {
    int row;
    int col;

    Vertex(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};

    private void bfs(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;

        int originalColor = image[sr][sc];
        if (originalColor == color) return; // Early exit (MUST)

        Queue<Vertex> q = new ArrayDeque<>();
        q.offer(new Vertex(sr, sc));

        while (!q.isEmpty()) {
            Vertex front = q.poll();

            image[front.row][front.col] = color;

            // Explore adjacent cells
            for (int i = 0; i < 4; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                // !Used early continue instead of deeply nested and conditions
                if (adjRow < 0 || adjCol < 0 || adjRow >= m || adjCol >= n)
                    continue;

                if (image[adjRow][adjCol] != originalColor)
                    continue;

                q.offer(new Vertex(adjRow, adjCol));
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        bfs(image, sr, sc, color);
        return image;
    }
}