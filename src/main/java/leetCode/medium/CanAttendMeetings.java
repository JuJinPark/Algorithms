package leetCode.medium;

import java.util.Arrays;

public class CanAttendMeetings {
    public static void main(String[] args) {
        boolean result = canAttendMeetings(new int[][]{{1, 5}, {3, 9}, {6, 8}});
    }

    public static boolean canAttendMeetings(int[][] intervals) {
        // sc -n log n , tc - o(1)
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] previous = intervals[0];
        for(int i =1; i<intervals.length; i++) {
            int[] current = intervals[i];
            if(current[0]<previous[1]) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
