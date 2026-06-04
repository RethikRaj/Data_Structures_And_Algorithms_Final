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
    private static final int[] DELTA_COL = {0, 0, -1, 1};

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Create and initialize a distance matrix
        int[][] distance = new int[m][n];
        for(int[] row : distance) Arrays.fill(row, 0);

        // Initialize multi source bfs
        Queue<Pair> q = new ArrayDeque<>();
        // Let's mark visited cell with -1 so that we won't need explicit visited array.

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 0) {
                    q.offer(new Pair(i, j));
                    mat[i][j] = -1; // marking as visited.

                    distance[i][j] = 0;
                }
            }
        }

        while(!q.isEmpty()) {
            Pair front = q.poll();

            // Adjacents
            for(int j = 0; j < 4; j++) {
                int adjRow = front.row + DELTA_ROW[j];
                int adjCol = front.col + DELTA_COL[j];

                if(adjRow >= 0 && adjRow < m && adjCol >= 0 && adjCol < n
                    && mat[adjRow][adjCol] != -1 && mat[adjRow][adjCol] != 0) {
                    mat[adjRow][adjCol] = -1;
                    distance[adjRow][adjCol] = distance[front.row][front.col] + 1;
                    q.offer(new Pair(adjRow, adjCol));
                }
            }
        }

        return distance;
    }
}