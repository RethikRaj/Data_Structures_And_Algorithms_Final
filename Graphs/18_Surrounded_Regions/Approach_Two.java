// Approach:
//  Instead of checking every component, start BFS/DFS only from 'O' cells on the four borders.
// Mark all reachable 'O's as 'V' (visited) -> meaning can't capture.
// Final sweep: remaining 'O's can be captured → flip to 'X';
//              'V' cells cannot be captured → restore to 'O'.

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

    private void bfs(char[][] board, Pair src) {
        int m = board.length;
        int n = board[0].length;

        Queue<Pair> q = new ArrayDeque<>();

        q.offer(src);
        board[src.row][src.col] = 'V';

        while(!q.isEmpty()) {
            Pair front = q.poll();

            for(int i = 0; i < 4; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                if(adjRow >= 0 && adjRow < m && adjCol >= 0 && adjCol < n
                    && board[adjRow][adjCol] == 'O') {
                    q.offer(new Pair(adjRow, adjCol));
                    board[adjRow][adjCol] = 'V';
                }
            }
        }
    }

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // For all border cells containing value 'O' apply bfs

        for(int col = 0; col < n; col++) {
            // First row
            if(board[0][col] == 'O') bfs(board, new Pair(0, col));

            // Last row
            if(board[m-1][col] == 'O') bfs(board, new Pair(m-1, col));
        }

        for(int row = 1; row < m - 1; row++) {
            // First col
            if(board[row][0] == 'O') bfs(board, new Pair(row, 0));

            // Last col
            if(board[row][n-1] == 'O') bfs(board, new Pair(row, n-1));
        }

        // Traverse the board
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'X') continue;
                else if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == 'V') board[i][j] = 'O';
            }
        }
    }
}