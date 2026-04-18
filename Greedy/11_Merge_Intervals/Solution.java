class Solution {
    // Method 1 : Sort by start time , TC : O(nlogn), SC:O(sort)
    // For clean code refer method 2 . I can code the same way for method 1 . I just kept like below becuase i coded it that way.
    public int[][] methodOne(int[][] intervals) {
        List<List<Integer>> ans = new ArrayList<>();

        // Sort intervals by start time so overlapping intervals are adjacent
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int i = 0;
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            // Expand end as long as the next interval overlaps(Merge)
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= end) {
                end = Math.max(end, intervals[j][1]);
                j++;
            }

            ans.add(List.of(start, end));
            i = j; // Skip all merged intervals
        }

        // Convert result to int[][]
        int[][] finalAns = new int[ans.size()][2];
        for (int z = 0; z < ans.size(); z++) {
            finalAns[z][0] = ans.get(z).get(0);
            finalAns[z][1] = ans.get(z).get(1);
        }

        return finalAns;
    }

    // Method 2 : Sort by end time
    public int[][] methodTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // Sort by ending time

        List<int[]> ans = new ArrayList<>();

        ans.add(intervals[intervals.length - 1]);

        for(int i = intervals.length - 2; i >= 0 ; i--) {
            
            int[] lastAddedInterval = ans.get(ans.size() - 1);

            if(intervals[i][1] >= lastAddedInterval[0]) {
                // overlapping : Find minimum of start of last added interval and currentInterval
                lastAddedInterval[0] = Math.min(lastAddedInterval[0], intervals[i][0]);
            }else {
                // no overlap
                ans.add(intervals[i]);
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }

    public int[][] merge(int[][] intervals) {
        return methodOne(intervals);
    }
}