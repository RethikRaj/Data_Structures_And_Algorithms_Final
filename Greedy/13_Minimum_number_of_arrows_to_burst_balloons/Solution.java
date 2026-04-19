class Solution {

    /**
     * Strategy: Sort by start time.
     * Greedily track the tightest overlapping end (min of ends).
     * When a balloon starts beyond that end, a new arrow is needed.
     * TC: O(n log n), SC: O(1)
     */
    public int methodOne(int[][] points) {
        // a[0] - b[0] risks overflow for large negatives; Integer.compare is safe
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int arrows = 1; // arrow fired at end of first balloon
        int overlapEnd = points[0][1]; // tightest end within current overlapping group

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= overlapEnd) {
                // still overlapping — tighten the window to the earliest end
                overlapEnd = Math.min(overlapEnd, points[i][1]);
            } else {
                // gap found — this balloon needs a fresh arrow
                arrows++;
                overlapEnd = points[i][1];
            }
        }

        return arrows;
    }

    /**
     * Strategy: Sort by end time.
     * Each arrow is fired at the current balloon's end, bursting all that overlap it.
     * Skip overlapping balloons; fire a new arrow only when one falls outside the last shot.
     * TC: O(n log n), SC: O(1)
     */
    public int methodTwo(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int lastShot = points[0][1]; // arrow fired at end of first balloon

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= lastShot) {
                // balloon burst by the last arrow — skip
            } else {
                // starts after last shot — needs a new arrow at its end
                arrows++;
                lastShot = points[i][1];
            }
        }

        return arrows;
    }

    public int findMinArrowShots(int[][] points) {
        return methodTwo(points);
    }
}