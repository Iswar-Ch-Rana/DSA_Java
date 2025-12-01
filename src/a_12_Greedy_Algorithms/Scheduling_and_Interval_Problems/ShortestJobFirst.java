package a_12_Greedy_Algorithms.Scheduling_and_Interval_Problems;

import java.util.Arrays;

public class ShortestJobFirst {
    public static void main(String[] args) {
        System.out.println(solve(new int[]{4, 1, 3, 7, 2}));
        System.out.println(solve(new int[]{1, 2, 3, 4}));
        System.out.println(solve(new int[]{9, 3, 1, 8, 2}));
    }

    public static long solve(int[] bt) {
        //your code goes here
        Arrays.sort(bt);  // SJF rule

        int n = bt.length;
        long totalWait = 0;
        long wait = 0;

        for (int i = 1; i < n; i++) {
            wait += bt[i - 1];  // previous burst
            totalWait += wait;  // add to total
        }

        return totalWait / n; // floor
    }
}
