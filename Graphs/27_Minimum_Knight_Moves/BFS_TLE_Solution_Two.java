import java.util.*;

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    

    private static final int[][] deltaMoves = { {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2} };

    public static int minimumKnightMoves(int x, int y) {
        Queue<Pair> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[300][300];
        int[][] distance = new int[300][300];

        Pair p = new Pair(0, 0);
        q.offer(p);
        visited[0 + 100][0 + 100] = true;
        distance[0 + 100][0 + 100] = 0;
        

        while(!q.isEmpty()) {
            Pair front = q.poll();

            if(front.x == x && front.y == y) {
                return distance[front.x + 100][front.y + 100];
            }

            for(int j = 0; j < 8; j++) {
                int nX = front.x + deltaMoves[j][0];
                int nY = front.y + deltaMoves[j][1];

                if(!visited[nX + 100][nY + 100]) {
                    q.offer(new Pair(nX, nY));
                    visited[nX + 100][nY + 100] = true;
                    distance[nX + 100][nY + 100] = distance[front.x + 100][front.y + 100] + 1;
                }
            }
        }

        return -1;
    }   
}