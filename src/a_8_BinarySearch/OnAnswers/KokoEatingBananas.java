package a_8_BinarySearch.OnAnswers;

import java.util.Arrays;

/**
 * 🧩 Problem: Koko loves bananas and can eat at a certain rate (bananas/hour).
 * Given piles of bananas and total hours 'h', find the minimum integer eating rate
 * such that Koko can finish all piles within 'h' hours.
 *
 * ⏱️ Time Complexity: O(N log M), where M = max(piles)
 * 💾 Space Complexity: O(1)
 *
 * 🐢 Brute Force: Try all possible eating rates from 1 to max(piles) and
 * calculate total hours each time — O(N*M)
 *
 * ⚡ Optimized: Use Binary Search on rate range [1..max(piles)].
 * For each mid-rate, compute total hours via ceil(pile / rate) and
 * adjust the search space accordingly.
 *
 * 🧠 Key Concepts: Binary Search on Answer, Monotonic Function, Ceiling Division
 *
 * 🎯 Interview Takeaways:
 * - Increasing eating rate reduces required hours (monotonic property).
 * - Explain integer vs floating-point ceil handling.
 * - Follow-up: Modify for multiple workers or parallel eating.
 */
public class KokoEatingBananas {

    public static void main(String[] args) {
        KokoEatingBananas obj = new KokoEatingBananas();
        int[] arr1 = {7, 15, 6, 3};
        int[] arr2 = {25, 12, 8, 14, 19};
        int[] arr3 = {3, 7, 6, 11};

        System.out.println(obj.minimumRateToEatBananas(arr1, 8));
        System.out.println(obj.minimumRateToEatBananas(arr2, 5));
        System.out.println(obj.minimumRateToEatBananas(arr3, 8));
    }

    /**
     * Finds the minimum eating rate (bananas/hour) such that
     * Koko can finish all piles within 'h' hours.
     *
     * @param nums array representing piles of bananas
     * @param h    maximum allowed hours
     * @return minimum integer rate to eat all bananas
     */
    public int minimumRateToEatBananas(int[] nums, int h) {
        int low = 1, high = Arrays.stream(nums).max().getAsInt();
        int res = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int totalHours = numberOfHoursToFinishBanans(nums, mid);

            if (totalHours <= h) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    /**
     * Computes the total hours required to finish all banana piles
     * at a given eating rate.
     *
     * @param totalBananaArray array of banana piles
     * @param bananasPerHour   eating rate
     * @return total hours needed to finish all piles
     */
    public int numberOfHoursToFinishBanans(int[] totalBananaArray, int bananasPerHour) {
        int totalHours = 0;
        for (int banana : totalBananaArray) {
            double result = (double) banana / bananasPerHour;
            totalHours += Math.ceil(result);
        }
        return totalHours;
    }
}
