class Solution {
    // Solution One : Sort by starting time
    public int methodOne(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[] lastInterval = intervals[0];
        int ans = 0;

        for(int i = 1; i < intervals.length ; i++ ) {
            if(intervals[i][0] < lastInterval[1]) {
                // overlapping 
                if(intervals[i][1] < lastInterval[1]) {
                    // Discard lastInterval
                    // Therfore new value lastInterval becomes interval[i]
                    lastInterval = intervals[i];
                }else if(intervals[i][1] > lastInterval[1]) {
                    // Discard intervals[i]
                }else {
                    // Keep whichever you like but discard other
                }

                ans += 1;
            }else {
                // non overlapping
                lastInterval = intervals[i];
            }
        }

        return ans;
    }

    // Solution Two : Sort by end time
    public int methodTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // sort by end time

        int[] lastInterval = intervals[intervals.length - 1];
        int ans = 0;

        for(int i = intervals.length - 2; i >= 0 ; i-- ) {
            if(intervals[i][1] > lastInterval[0]) {
                // overlapping 
                if(intervals[i][0] > lastInterval[0]) {
                    // Discard lastInterval
                    // Therfore new value lastInterval becomes interval[i]
                    lastInterval = intervals[i];
                }else if(intervals[i][0] <  lastInterval[0]) {
                    // Discard intervals[i]
                }else {
                    // Keep whichever you like but discard other
                }

                ans += 1;
            }else {
                // non overlapping
                lastInterval = intervals[i];
            }
        }

        return ans;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        // return methodOne(intervals);
        return methodTwo(intervals);
    }
}
