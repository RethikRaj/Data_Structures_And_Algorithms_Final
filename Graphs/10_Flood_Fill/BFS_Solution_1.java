// BFS + Set<Pair> visited -> Need to define equals and hashCode() for Pair class so that set works fine.

class Pair {
    int row;
    int col;
    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;

        if(o.getClass() != this.getClass()) return false;

        return this.row == ((Pair) o).row && this.col == ((Pair) o).col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = { 0, 0, -1, 1};

    private void bfs(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor == color) return; // Early exit;

        Queue<Pair> q = new ArrayDeque<>();
        Set<Pair> visited = new HashSet<>();

        Pair src = new Pair(sr, sc);
        q.offer(src);
        visited.add(src);

        while (!q.isEmpty()) {
            Pair front = q.poll(); // 1.Pop

            // 2. Process front node
            image[front.row][front.col] = color;

            // 3. Explore adjacents
            for (int i = 0; i < DELTA_ROW.length; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];
                Pair temp = new Pair(adjRow, adjCol);

                if (adjRow >= 0 && adjRow < image.length
                        && adjCol >= 0 && adjCol < image[0].length
                        && !visited.contains(temp)
                        && image[adjRow][adjCol] == originalColor) {
                    q.offer(temp);
                    visited.add(temp);
                }
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        bfs(image, sr, sc, color);
        return image;
    }
}