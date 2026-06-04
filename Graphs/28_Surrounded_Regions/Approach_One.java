// Approach: BFS/DFS from every unvisited 'O' cell on the board.
// Each BFS/DFS explores one connected component and checks whether any cell in the current BFS/DFS path touches the border. 
// If none do, the whole component is surrounded and gets flipped to 'X'.

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

    private List<Pair> bfs(char[][] board, Pair src, boolean[][] visited, boolean[] canCapture) {
        int m = board.length;
        int n = board[0].length;

        List<Pair> result = new ArrayList<>();

        Queue<Pair> q = new ArrayDeque<>();

        q.offer(src);
        visited[src.row][src.col] = true;

        while(!q.isEmpty()) {
            Pair front = q.poll();

            result.add(front);

            if(front.row == 0 || front.row == m - 1 || front.col == 0 || front.col == n - 1) {
                // border => can't capture
                canCapture[0] = false;
            }

            for(int i = 0; i < 4; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                if(adjRow >= 0 && adjRow < m && adjCol >= 0 && adjCol < n
                    && !visited[adjRow][adjCol]
                    && board[adjRow][adjCol] == 'O') {
                    q.offer(new Pair(adjRow, adjCol));
                    visited[adjRow][adjCol] = true;
                }
            }
        }
        return result;
    }

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i = 0 ; i < m ; i++) {
            for(int j = 0; j < n ; j++) {
                if(!visited[i][j] && board[i][j] == 'O') {
                    boolean[] canCapture = {true};
                    List<Pair> vertices = bfs(board, new Pair(i, j), visited, canCapture);

                    if(canCapture[0]) {
                        // Flip 
                        for(Pair p : vertices) {
                            board[p.row][p.col] = 'X';
                        }
                    }
                }
            }
        }
    }
}