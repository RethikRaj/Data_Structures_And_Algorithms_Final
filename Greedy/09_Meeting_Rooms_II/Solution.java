import java.util.Arrays;
import java.util.List;

class Interval {
    int start;
    int end;
    Interval(int start, int end) { 
        this.start = start; 
        this.end = end; 
    }
}


class Solution {

    // Better | TC: O(n log n)  SC: O(n)
    public int better(List<Interval> intervals) {
        /*
         * Approach: Min-Heap (Priority Queue)
         *   - Process meetings sorted by start time.
         *   - Maintain a min-heap of end times of currently occupied rooms.
         *   - For each meeting: if the earliest-ending room frees up before this meeting starts → reuse it.
         *                       otherwise → open a new room.
         */

        // Sort meetings by start time
        Collections.sort(intervals, (a,b) -> a.start - b.start);

        int numberOfRooms = 0;
        PriorityQueue<Integer> endTimes = new PriorityQueue<>(); // tracks end times of active rooms

        for (Interval meeting : intervals) {
            // Is there any room with endtime <= top.start
            // Therfore we need to check if minimum of end times of all active rooms <= top.start
            if (!endTimes.isEmpty() && meeting.start >= endTimes.peek()) {
                endTimes.poll();        // room freed → reuse it
                endTimes.offer(meeting.end);
            } else {
                numberOfRooms++;        // no room available → open new one
                endTimes.offer(meeting.end);
            }
        }

        return numberOfRooms;
    }

    // Best | TC: O(n log n)  SC: O(n)
    public int best(List<Interval> intervals) {
        /*
         * Approach: Two-Pointer on Sorted Start & End arrays
         *
         * Key Insight:
         *   - Sort starts and ends independently.
         *   - Use two pointers: i → next meeting starting, j → next meeting ending.
         *   - Treat it like a timeline sweep:
         *       start[i] < end[j]  → a new meeting begins before any room frees  → need extra room
         *       start[i] > end[j]  → a room frees before next meeting starts      → vacate room
         *       start[i] == end[j] → simultaneous free + occupy                   → net zero change
         */

        int n = intervals.size();
        int[] start = new int[n];
        int[] end   = new int[n];

        for (int k = 0; k < n; k++) {
            start[k] = intervals.get(k).start;
            end[k]   = intervals.get(k).end;
        }

        // Sort the start and end arrays
        Arrays.sort(start);
        Arrays.sort(end);

        int ans = 0, currActiveRooms = 0;
        int i = 0, j = 0;

        while (i < n && j < n) {
            if (start[i] < end[j]) {
                currActiveRooms++;                      // new meeting, needs a room
                ans = Math.max(ans, currActiveRooms);   // track maximum rooms used
                i++;
            } else if (start[i] > end[j]) {
                currActiveRooms--;                      // a meeting ended, room freed
                j++;
            } else {
                i++; 
                j++;                               // one ends, one starts → no change in room count
            }
        }

        return ans;
    }

    public int minMeetingRooms(List<Interval> intervals) {
        // return better(intervals);
        return best(intervals);
    }
}
