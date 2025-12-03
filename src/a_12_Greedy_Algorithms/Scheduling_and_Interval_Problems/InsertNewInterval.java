package a_12_Greedy_Algorithms.Scheduling_and_Interval_Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertNewInterval {
    public static void main(String[] args) {
        int[][] ans1 = insertNewInterval(
                new int[][]{{1, 3}, {6, 9}},
                new int[]{2, 5});

        for (int[] arr : ans1) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();

        int[][] ans2 = insertNewInterval(
                new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                new int[]{4, 8});
        for (int[] arr : ans2) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static int[][] insertNewInterval(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        int start = newInterval[0], end = newInterval[1];

        // 1) Add all intervals that end before newInterval starts (no overlap)
        while (i < n && intervals[i][1] < start) {
            result.add(intervals[i++]);
        }

        // 2) Merge all overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        // add the merged/new interval
        result.add(new int[]{start, end});

        // 3) Add the remaining intervals (start after newInterval ends)
        while (i < n) {
            result.add(intervals[i++]);
        }

        return result.toArray(new int[result.size()][]);
    }
}
