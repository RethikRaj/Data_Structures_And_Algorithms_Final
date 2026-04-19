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
                }
                // else : discard intervals[i] => no need to update lastInterval

                ans += 1;
            }else {
                // non overlapping
                lastInterval = intervals[i];
            }
        }

        return ans;
    }

    // Solution One Simplified code :
    public int methodOneSimplified(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]); // sort by start time

        int ans = 0; // ans denotes number of removals
        int last_end_time = intervals[0][1];

        for(int i = 1; i < intervals.length ;i++) {
            if(intervals[i][0] < last_end_time) {
                // Overlapping => Therefore we need to discard / remove any one of two intervals
                // For minimum removals : remove the interval that ends later 
                last_end_time = Math.min(last_end_time, intervals[i][1]);
                ans += 1;
            }else {
                last_end_time = intervals[i][1];
            }
        }

        return ans;
    }

    // Solution Two : Sort by end time ( R to L , tough - not intuituve)
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

    // SolutionTwo Simplified (L to R)
    public int methodTwoSimplified(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1]-b[1]); // sort by end time

        int prev_end_time = intervals[0][1];
        int ans = 0;

        for(int i = 1 ; i < intervals.length ; i++) {
            if(intervals[i][0] < prev_end_time) {
                // overlap => remove any one => here intervals[i][1] will always be >= prev_end_time => Remove intervals[i][1] => No need to update prev_end_time
                ans += 1;
            }else {
                // No overlap 
                prev_end_time = intervals[i][1];
            }
        }

        return ans;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        // return methodOne(intervals);
        // return methodOneSimplified(intervals);

        // return methodTwo(intervals);
        return methodTwoSimplified(intervals);
    }
}
