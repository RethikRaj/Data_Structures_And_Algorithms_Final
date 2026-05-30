// DFS + (using given_matrix itslef as visited) , recolored means it is visited.

class Solution {
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = { 0, 0, -1, 1};

    private void dfs(int[][] image, int currRow, int currCol, int originalColor, int newColor) {
        // Step 1 + Step 2 : 
        image[currRow][currCol] = newColor; // Also acts as visited.

        // Step 3 : Explore adjacents
        for(int i = 0; i < DELTA_ROW.length; i++) {
            int adjRow = currRow + DELTA_ROW[i];
            int adjCol = currCol + DELTA_COL[i];

            if(adjRow >= 0 && adjRow < image.length && adjCol >= 0 && adjCol < image[0].length && image[adjRow][adjCol] == originalColor) {
                dfs(image, adjRow, adjCol, originalColor, newColor);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if(originalColor == color) return image;
        dfs(image, sr, sc, originalColor, color);

        return image;
    }
}