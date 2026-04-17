/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    // Method 2 : Use MEETING_ROOMS_II question and if required rooms == 1 , then true , else false

    // Method 1 
    public boolean canAttendMeetings(List<Interval> intervals) {
        // For a person to attend all meetings, none of the meeting must be overlapped.
        
        // Sort the intervals based on starting time.
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        // Each meeting start time must be less than or equal to the last meet end time, so that the person can attend all meetings
        int lastMeetEndTime = 0;
        for(Interval meeting : intervals) {
            if(meeting.start >= lastMeetEndTime) {
                // can attend this meet
                lastMeetEndTime = meeting.end;
            }else {
                return false;
            }
        }

        return true;
    }
}
