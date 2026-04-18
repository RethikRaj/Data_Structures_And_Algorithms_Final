import java.util.HashMap;
import java.util.Map;

class Solution {
    // Strategy : Greedy Grouping
     /* Core Insight:
        - If a rabbit says "X", it means X+1 rabbits (including itself) share the same color.
        - Since we need minimum , we will be optimisitic : Rabbits saying the same number CAN belong to the same color group.
        - But a group can hold at most (X+1) rabbits saying "X".
        - If more than (X+1) rabbits say "X", they must form a NEW separate group.
    */

    // Better solution : TC : O(n logn) , SC : O(sort)
    public int better(int[] answers) {
        /*
         * Approach:
         *   1. Sort the array so identical answers are adjacent.
         *   2. Process each group greedily:
         *        - Take the first rabbit saying X → it starts a group of size (X+1).
         *        - Absorb up to X more rabbits saying X into this group (they share the color).
         *        - Any remaining rabbits saying X start a fresh group → add (X+1) again.
         *   3. Always count a full group of (X+1), even if not all slots are filled
         *      (unfilled slots = rabbits that didn't answer the survey).
         */

        int n = answers.length;
        Arrays.sort(answers); // Group identical answers together

        int i = 0;
        int ans = 0;

        while (i < n) {
            int slotsRemaining = answers[i]; // Can absorb this many MORE rabbits into same color group

            // Greedily pack adjacent rabbits with the same answer into one group.
            // Move j forward as long as:
            //   (a) there are still open slots in the current group, AND
            //   (b) the next rabbit gives the same answer.
            int j = i + 1;
            while (slotsRemaining != 0 && j < n && answers[i] == answers[j]) {
                slotsRemaining--;  // Fill one slot in the current color group
                j++;
            }

            // Always credit a FULL group of (answers[i] + 1):
            // - Filled slots  → those rabbits confirmed the same color.
            // - Unfilled slots → rabbits of that color who didn't answer the survey.
            ans += (answers[i] + 1);

            // Jump to the next unprocessed rabbit
            i = j;
        }

        return ans;
    }

    // Best Solution : TC : O(n), SC : O(n)
    public int best(int[] answers) {
        /*
        * Approach: 
        *   - Instead of sorting, track how many rabbits have already joined each group size.
        *   - Map stores: { groupSize → howManyRabbitsAlreadyPlacedInCurrentGroup }
        *   - When we see a rabbit saying X → groupSize = X+1
        *       CASE 1: groupSize not in map (or was removed)
        *               → Start a NEW group, immediately credit (X+1) to answer. Because minimium X + 1 rabits will be present.
        *               → Set map[groupSize] = 1  (1 rabbit placed so far)
        *       CASE 2: groupSize in map AND count < groupSize
        *               → Slot still available → absorb rabbit into existing group.
        *               → Increment map[groupSize] (no extra cost to answer)
        *       CASE 3: groupSize in map AND count == groupSize
        *               → Current group is FULL → force a new group.
        *               → Credit another (groupSize) to answer, reset map[groupSize] = 1.
        */

        int ans = 0;

        // groupSize → count of rabbits already placed in the current active group
        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < answers.length; i++) {
            int groupSize = answers[i] + 1; // this rabbit belongs to a color shared by groupSize rabbits

            if (mp.containsKey(groupSize)) {
                int count = mp.get(groupSize);

                if (count == groupSize) {
                    // CASE 3: Active group is completely full
                    // → Cannot absorb this rabbit → open a brand new group
                    mp.put(groupSize, 1);   // reset: 1 rabbit placed in new group
                    ans += groupSize;       // credit the full new group to ans
                } else {
                    // CASE 2: Active group still has open slots
                    // → Absorb this rabbit (no new group needed => not needed to add to answer)
                    mp.put(groupSize, count + 1);
                }
            } else {
                // CASE 1: No active group exists for this groupSize
                mp.put(groupSize, 1);  // 1 rabbit placed so far
                ans += groupSize;      // credit the full group upfront to ans
            }
        }

        return ans;
    }


    public int numRabbits(int[] answers) {
        // return better(answers);
        return best(answers);
    }
}