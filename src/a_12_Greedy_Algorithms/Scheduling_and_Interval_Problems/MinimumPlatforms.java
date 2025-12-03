package a_12_Greedy_Algorithms.Scheduling_and_Interval_Problems;

import java.util.Arrays;

public class MinimumPlatforms {
    public static void main(String[] args) {
        System.out.println(findPlatform(new int[]{900, 940, 950, 1100, 1500, 1800}, new int[]{910, 1200, 1120, 1130, 1900, 2000}));
        System.out.println(findPlatform(new int[]{900, 1100, 1235}, new int[]{1000, 1200, 1240}));
    }

    public static int findPlatform(int[] Arrival, int[] Departure) {
        int n = Arrival.length;

        // Sort both arrival and departure arrays
        Arrays.sort(Arrival);
        Arrays.sort(Departure);

        int ans = 1;
        int count = 1;
        int i = 1, j = 0;

        // Iterate through the arrays
        while (i < n && j < n) {
            if (Arrival[i] <= Departure[j]) {
                // Increment count
                count++;
                i++;
            } else {
                // Decrement count
                count--;
                j++;
            }
            // Find maximum
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
