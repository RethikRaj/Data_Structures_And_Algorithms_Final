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

    private boolean[][] multiSourceBFS(int[][] heights, String ocean) {
        int m = heights.length;
        int n = heights[0].length;

        Queue<Pair> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        // Initilaize mutiple sources
        if(ocean.equals("Pacific")) {
            for(int col = 0; col < n; col++) {
                q.offer(new Pair(0, col));
                visited[0][col] = true;
            }

            for(int row = 1; row < m; row++) {
                q.offer(new Pair(row, 0));
                visited[row][0] = true;
            }
        }else {
            for(int col = 0; col < n; col++) {
                q.offer(new Pair(m-1, col));
                visited[m-1][col] = true;
            }

            for(int row = m - 2; row >= 0; row--) {
                q.offer(new Pair(row, n-1));
                visited[row][n-1] = true;
            }
        }
        

        // 
        while(!q.isEmpty()) {
            Pair front = q.poll();

            for(int i = 0; i < DELTA_ROW.length; i++) {
                int adjRow = front.row + DELTA_ROW[i];
                int adjCol = front.col + DELTA_COL[i];

                if(adjRow >= 0 && adjRow < m && adjCol >= 0 && adjCol < n
                     && heights[adjRow][adjCol] >= heights[front.row][front.col]
                      && !visited[adjRow][adjCol]) {
                    q.offer(new Pair(adjRow, adjCol));
                    visited[adjRow][adjCol] = true;
                }
            }
        }

        return visited;
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        boolean[][] pacific = multiSourceBFS(heights, "Pacific");
        boolean[][] atlantic = multiSourceBFS(heights, "Atlantic");

        // Find common visited cells between both
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < heights.length; i++) {
            for(int j = 0; j < heights[0].length; j++) {
                if(pacific[i][j] == true && atlantic[i][j] == true) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }
}
