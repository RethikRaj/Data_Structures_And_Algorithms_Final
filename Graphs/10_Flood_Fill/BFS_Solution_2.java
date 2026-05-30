// BFS + boolean[][] visited (that is 2D matrix as visited), nothing else is changed

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
        if(originalColor == color) return; // Early exit

        Queue<Pair> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        
        for(boolean[] row : visited) Arrays.fill(row, false);

        Pair src = new Pair(sr, sc);
        q.offer(src);
        visited[sr][sc] = true;

        while(!q.isEmpty()) {
            Pair front = q.poll();

            image[front.row][front.col] = color;

            // Explore adjacents            
            for(int i = 0; i < DELTA_ROW.length;i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                Pair temp = new Pair(adjRow, adjCol);

                if(adjRow < m && adjCol < n && adjRow >= 0 && adjCol >= 0 && visited[adjRow][adjCol] == false && image[adjRow][adjCol] == originalColor) {
                    q.offer(temp);
                    visited[adjRow][adjCol] = true;
                }
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        bfs(image, sr, sc, color);
        return image;
    }
}