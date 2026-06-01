import java.util.*;

/*
BFS - Set<Pair> as visited and using modified BFS 

TLE - TIme Limit Exceeded
 */

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;

        if(o.getClass() != this.getClass()) return false;

        return this.x == ((Pair) o).x && this.y == ((Pair) o).y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}

class Solution {
    private static final int[][] deltaMoves = { {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2} };

    public static int minimumKnightMoves(int x, int y) {
        Queue<Pair> q = new ArrayDeque<>();
        Set<Pair> visited = new HashSet<>();

        Pair p = new Pair(0, 0);
        q.offer(p);
        visited.add(p);

        int level = 0;

        while(!q.isEmpty()) {
            int levelSize = q.size();

            for(int i = 0;i < levelSize; i++) {
                Pair front = q.poll();

                if(front.x == x && front.y == y) {
                    return level;
                }

                for(int j = 0; j < 8; j++) {
                    int nX = front.x + deltaMoves[j][0];
                    int nY = front.y + deltaMoves[j][1];

                    Pair temp = new Pair(nX, nY);
                    if(!visited.contains(temp)) {
                        q.offer(temp);
                        visited.add(temp);
                    }
                }
            }

            level += 1;
        }

        return level;
    }   
}

// private static int f(int currX, int currY, int destX, int destY) {
    //     // Base cases 
    //     if(currX == destX && currY == destY) return 0;
    //     if(currX >= destX + 3 || currX <= destX - 3 || currY >= destY + 3 || currY <= destY - 3) return Integer.MAX_VALUE;

    //     int[][] deltaMoves = { {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2} };

    //     int minimumMoves = Integer.MAX_VALUE;
    //     for(int i = 0; i < 8; i++) {
    //         int newX = currX + deltaMoves[i][0];
    //         int newY = currY + deltaMoves[i][1];
            
    //         minimumMoves = Math.min(minimumMoves, f(newX, newY, destX, destY));
    //     }

    //     return 1 + minimumMoves;
    // }

    // private static int f_bu(int destX, int destY) {
    //     int[][] dp = new int[210][210];  // To access coordinate (x,y) -> (x + 100, y + 100)

    //     // Step 2 : Base case 
    //     if(destX == 0 && destY == 0) return 0;
    //     dp[destX + 100][destY + 100] = 0;

    //     // Step 3 : Direction

    // }
