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

    public int orangesRotting(int[][] grid) {
        // Inituition : Mutliple rotten oranges can be present at starting => Multiple sources
        // Explicit visited array is not needed because if rotten we can take it as already visited.

        int m = grid.length;
        int n = grid[0].length;

        // Step 1 : 
        Queue<Pair> q = new ArrayDeque<>();

        // Step 2 : Initialize source
        int freshOrangeCount = 0; // counting fresh orange;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    freshOrangeCount += 1;
                }
                if(grid[i][j] == 2) {
                    q.offer(new Pair(i, j));
                }
            }
        }

        // !Edge case : All cells are (empty or already rotten)
        if(freshOrangeCount == 0) return 0;

        // Step 3 :
        int result = -1;
        while(!q.isEmpty()) {
            int levelSize = q.size();

            for(int i = 0; i < levelSize; i++) {
                Pair front = q.poll();

                // Adjacents
                for(int j = 0; j < 4; j++) {
                    int adjRow = front.row + DELTA_ROW[j];
                    int adjCol = front.col + DELTA_COL[j];

                    if(adjRow >= 0 && adjRow < m && adjCol >= 0 && adjCol < n
                        && grid[adjRow][adjCol] == 1 // Non-rotten and non-empty check
                        ) {
                        freshOrangeCount -= 1;
                        grid[adjRow][adjCol] = 2;
                        q.offer(new Pair(adjRow, adjCol));
                    }
                }
            }

            result += 1;
        } 

        // Check if all are rotten ( why to check ? because some fresh oranges might not be reachable )
        return freshOrangeCount == 0 ? result : -1;       
    }
}