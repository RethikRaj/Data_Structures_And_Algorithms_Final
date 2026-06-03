

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};
    private boolean hasCycleDFS(int currRow, int currCol, int parentRow, int parentCol, char[][] grid, boolean[][] visited) {
        visited[currRow][currCol] = true;

        for(int i = 0; i < 4; i++) {
            int adjRow = currRow + DELTA_ROW[i];
            int adjCol = currCol + DELTA_COL[i];

            if(adjRow < 0 || adjRow >= grid.length || adjCol < 0 || adjCol >= grid[0].length) continue;

            if(grid[adjRow][adjCol] != grid[currRow][currCol]) continue;

            // Now only in (grid as graph) edge will exist
            if(visited[adjRow][adjCol]){
                if(adjRow == parentRow && adjCol == parentCol) continue;
                else return true; // cycle present
            }else {
                boolean hasCycle = hasCycleDFS(adjRow, adjCol, currRow, currCol, grid, visited);
                if(hasCycle) return true;
            }
        }

        return false;
    }

    public boolean containsCycle(char[][] grid) {
        boolean[][] visited= new boolean[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(!visited[i][j]) {
                    boolean hasCycle = hasCycleDFS(i, j, -1, -1, grid, visited);
                    if(hasCycle) return true;
                }
            }
        }

        return false;
    }
}