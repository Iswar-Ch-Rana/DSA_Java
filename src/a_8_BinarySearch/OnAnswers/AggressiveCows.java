package a_8_BinarySearch.OnAnswers;

import java.util.Arrays;

/**
 * 🧩 Problem: Place 'k' cows in 'n' stalls (given by positions array)
 * such that the minimum distance between any two cows is maximized.
 *
 * ⏱️ Time Complexity: O(N log(maxDistance))
 * 💾 Space Complexity: O(1)
 *
 * 🐢 Brute Force: Try all possible minimum distances and check feasibility — O(N * maxDistance)
 * ⚡ Optimized: Binary Search on Answer — find largest feasible minimum distance using greedy placement.
 *
 * 🧠 Key Concepts: Binary Search on Answer, Greedy Placement, Sorting
 * 🎯 Interview Takeaways:
 * - Increasing minimum distance makes placement harder (monotonic property).
 * - Core idea: maximize the minimum feasible distance.
 * - Follow-up: Similar to “Painters Partition” / “Book Allocation”.
 */
public class AggressiveCows {

    public static void main(String[] args) {
        AggressiveCows obj = new AggressiveCows();
        int[] stalls = {0, 3, 4, 7, 10, 9};
        int k = 4;

        System.out.println("Max minimum distance: " + obj.aggressiveCows(stalls, k));
    }

    /**
     * Finds the largest minimum distance possible between cows placed in stalls.
     *
     * @param nums positions of stalls
     * @param k    number of cows to place
     * @return maximum possible minimum distance
     */
    public int aggressiveCows(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 1;
        int end = nums[nums.length - 1] - nums[0];
        int ans = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (canPlaceCows(nums, mid, k)) {
                ans = mid;
                start = mid + 1; // try for a larger distance
            } else {
                end = mid - 1;   // try for a smaller distance
            }
        }
        return ans;
    }

    /**
     * Checks if it’s possible to place 'k' cows in stalls such that
     * each cow is at least 'minDistance' apart.
     *
     * @param stalls       sorted array of stall positions
     * @param minDistance  minimum required distance between cows
     * @param k            number of cows to place
     * @return true if placement is possible, otherwise false
     */
    boolean canPlaceCows(int[] stalls, int minDistance, int k) {
        int lastPlaced = stalls[0];
        k--; // first cow placed

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPlaced >= minDistance) {
                lastPlaced = stalls[i];
                k--;
                if (k == 0) return true; // all cows placed
            }
        }
        return false;
    }
}
