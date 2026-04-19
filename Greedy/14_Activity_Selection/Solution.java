class Solution {
    public int activitySelection(int[] start, int[] finish) {
        int n = start.length;
        
        int[][] intervals = new int[n][2];
        for(int i = 0 ; i < n ; i++) {
            intervals[i] = new int[2];
            intervals[i][0] = start[i];
            intervals[i][1] = finish[i];
        }
        
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // sort by end time
        
        int removals = 0;
            
        int last_end = intervals[0][1];
        
        for(int i = 1 ; i < n ; i++) {
            if(intervals[i][0] <= last_end) {
                // overlap => remove intervals[i]
                removals += 1;
            }else {
                last_end = intervals[i][1];
            } 
        }
        
        return n - removals;
    }
}