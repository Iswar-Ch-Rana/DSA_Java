package a_12_Greedy_Algorithms.Scheduling_and_Interval_Problems;

import java.util.Arrays;

public class JobScheduling {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(jobScheduling(
                new int[][]{{1, 4, 20}, {2, 1, 10}, {3, 1, 40}, {4, 1, 30}}))); // Output : 2 60
        System.out.println(Arrays.toString(jobScheduling(
                new int[][]{{1, 2, 100}, {2, 1, 19}, {3, 2, 27}, {4, 1, 25}, {5, 1, 15}}))); // Output : 2 127
        System.out.println(Arrays.toString(jobScheduling(
                new int[][]{{1, 1, 100}, {2, 1, 19}, {3, 2, 27}, {4, 1, 25}, {5, 1, 15}}))); // Output: 4 1000
    }

    public static int[] jobScheduling(int[][] Jobs) {
        int n = Jobs.length;

        // Step 1: Sort jobs by PROFIT in descending order
        Arrays.sort(Jobs, (a, b) -> b[2] - a[2]);

        // Step 2: Find maximum deadline
        int maxDeadline = 0;
        for (int[] job : Jobs) {
            maxDeadline = Math.max(maxDeadline, job[1]);
        }

        // Step 3: Create a time slot array
        int[] slot = new int[maxDeadline + 1];   // slot[time] = jobID
        Arrays.fill(slot, -1);

        int jobCount = 0;
        int totalProfit = 0;

        // Step 4: Try to schedule each job
        for (int[] job : Jobs) {
            int id = job[0];
            int deadline = job[1];
            int profit = job[2];

            // Try to put this job at latest possible free slot ≤ deadline
            for (int t = deadline; t > 0; t--) {
                if (slot[t] == -1) {
                    slot[t] = id;
                    jobCount++;
                    totalProfit += profit;
                    break;
                }
            }
        }

        return new int[]{jobCount, totalProfit};

    }
}
