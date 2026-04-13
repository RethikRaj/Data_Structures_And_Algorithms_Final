/**
 * Celebrity Problem
 *
 * A celebrity is someone who:
 *   1. Does NOT know anyone else (except themselves)
 *   2. Is known by EVERYONE else
 *
 * Matrix interpretation:
 *   mat[i][j] = 1 → person i knows person j
 *   mat[i][j] = 0 → person i does NOT know person j
 *   mat[i][i] = 1 always (everyone knows themselves)
 */

class Solution {
    /**
     * -------------------------------------------------------------------------
     * BRUTE FORCE
     * TC: O(n²) — two passes over the matrix
     * SC: O(1)  — no extra space
     * -------------------------------------------------------------------------
     * Key Observations:
     *  - Celebrity's ROW  → only one 1 (mat[celeb][celeb] = 1, rest are 0)
     *  - Celebrity's COL  → all 1s (everyone knows the celebrity)
     * -------------------------------------------------------------------------
     */
    public int brute(int mat[][]) {
        int n = mat.length;
        
        // Step 1: Find a potential celebrity candidate
        // A celebrity does not know anyone but knows himself/herself, so their row should only conatain one occurence of 1.
        int ansRow = -1;
        
        for(int row = 0 ; row < n ; row++) {
            int count = 0;
            
            // Count number of people this person knows
            for(int col = 0 ; col < n ; col++) {
                if(mat[row][col] == 1) {
                    count += 1;
                }
            }
            
            // If count is 1 -> possible celebrity
            if(count == 1) {
                ansRow = row;
                break;
            }
        }
        
        // If no such row found, then no celebrity exists
        if(ansRow == -1) {
            return -1;
        }
        
        // Step 2: Verify the candidate
        // A celebrity must be known by everyone else
        // So the candidate's column should contain all 1's 
        int col = ansRow;
        
        for(int row = 0 ; row < n ; row++) {
            // If someone does not know the candidate -> not a celebrity
            if(mat[row][col] != 1) {
                return -1;
            }
        }
        
        // Candidate satisfies both conditions
        return ansRow;
    }

    // Better 
    /**
     * -------------------------------------------------------------------------
     * Better — Stack-based Elimination
     * TC: O(n) — each person is pushed/popped at most once + one linear verify
     * SC: O(n) — stack holds all n candidates initially
     * -------------------------------------------------------------------------
     * Core Idea: "Eliminate non-celebrities"
     *
     * For any two people A and B, check mat[A][B] and mat[B][A]:
     *
     *   A knows B     (mat[A][B]=1) → A CANNOT be celebrity (celebrity knows no one)
     *   B knows A     (mat[B][A]=1) → B CANNOT be celebrity
     *   A knows B     → eliminate A, keep B as candidate
     *   B knows A     → eliminate B, keep A as candidate
     *   Neither knows → both eliminated (neither can be celebrity)
     *   Both know     → both eliminated (celebrity can't know anyone)
     *
     * After all eliminations, at most ONE candidate survives → verify them.
     * -------------------------------------------------------------------------
     */ 
    public int better(int mat[][]) {
        int n = mat.length;
        // Step 0: Load all people into the stack (order doesn't matter)
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            stack.push(i);
        }

        // Step 1: Elimination — reduce candidates to at most one
        while(stack.size() >= 2) {
            int temp1 = stack.pop();
            int temp2 = stack.pop();

            if(mat[temp1][temp2] == 0 && mat[temp2][temp1] == 0) {
                continue;
            }else if(mat[temp1][temp2] == 0 && mat[temp2][temp1] == 1) {
                // temp1 may be celebrity
                stack.push(temp1);
            }else if(mat[temp1][temp2] == 1 && mat[temp2][temp1] == 0) {
                // temp2 may be celebrity
                stack.push(temp2);
            }else if(mat[temp1][temp2] == 1 && mat[temp2][temp1] == 1) {
                continue;
            }
        }

        // Step 2: Verification — confirm the last surviving candidate
        // (Elimination only guarantees "if a celebrity exists, it's this person"
        //  but doesn't confirm one actually exists → must verify)
        if(stack.isEmpty()) return -1;
        int possibleCelebrity = stack.pop();

        for(int i = 0 ; i < n ; i++) {
            // Condition One : Check whether celebrity knows no one except himself ?
            if(i != possibleCelebrity && mat[possibleCelebrity][i] == 1) {
                return -1;
            }
            // Condition Two : Check whether everyone knows celebrity ?
            if(mat[i][possibleCelebrity] != 1) {
                return -1;
            }
        }

        return possibleCelebrity;
    }

    // Optimal : Two pointers elimination instead of using stack
    public int optimal(int mat[][]) {
        int n = mat.length;

        // Step 1: Two-pointer elimination
        int low = 0, high = n - 1;

        while (low < high) {
            if(mat[low][high] == 0 && mat[high][low] == 1) {
                // Low can be celebrity but high cannot be 
                high -= 1;
            }else if(mat[low][high] == 1 && mat[high][low] == 0) {
                // high can be celebrity but low cannot be
                low += 1;
            } else {
                // Both low and high cannot be celebrity 
                high -= 1;
                low += 1;
            }
        }

        if(low > high) {
            return -1;
        }

        // low == high => one possible candidate
        int candidate = low;

        // Step 2: Verification
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;

            if (mat[candidate][i] == 1) return -1;  // candidate knows someone
            if (mat[i][candidate] != 1) return -1;  // someone doesn't know candidate
        }

        return candidate;
    }

    public int celebrity(int mat[][]) {
        // return brute(mat);
        // return better(mat);
        return optimal(mat);
    }
}
