// Number of islands = number of connected components

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};

    // Method 1 : dfs using explicit visited
    private void dfs(char[][] grid, int row, int col, boolean[][] visited) {
        visited[row][col] = true; // Marking it as visited

        // Explore adjacents
        for (int i = 0; i < DELTA_ROW.length; i++) {
            int adjRow = row + DELTA_ROW[i];
            int adjCol = col + DELTA_COL[i];

            if (adjRow >= 0 && adjRow < grid.length && adjCol >= 0 && adjCol < grid[0].length && !visited[adjRow][adjCol] && grid[adjRow][adjCol] == '1') {
                dfs(grid, adjRow, adjCol, visited);
            }
        }
    }

    // Method 2 : dfs without using visited
    private void dfs_2(char[][] grid, int row, int col) {
        grid[row][col] = '2'; // Marking it as visited

        // Explore adjacents
        for (int i = 0; i < DELTA_ROW.length; i++) {
            int adjRow = row + DELTA_ROW[i];
            int adjCol = col + DELTA_COL[i];

            if (adjRow >= 0 && adjRow < grid.length && adjCol >= 0 && adjCol < grid[0].length && grid[adjRow][adjCol] == '1') {
                dfs_2(grid, adjRow, adjCol);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Each (row, col) denotes a vertex
        int count = 0;

        // Method 1 : dfs using visited
        // boolean[][] visited = new boolean[m][n];
        // for(int i = 0; i < m; i++) {
        //     for(int j = 0; j < n; j++) {
        //         if(grid[i][j] == '1' && !visited[i][j]) { 
        //             count += 1;
        //             dfs(grid, i, j, visited);
        //         }
        //     }
        // }

        // Method 2 : dfs without using visited
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1' && grid[i][j] != '2') { // grid[i][j] != '2' is nothing but visited or not check
                    count += 1;
                    dfs_2(grid, i, j);
                }
            }
        }

        return count;
    }
}