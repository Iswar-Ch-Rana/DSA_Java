package a_8_BinarySearch.OnAnswers;

/**
 * 🧩 Problem: Find the smallest positive divisor such that
 * sum(ceil(nums[i] / divisor)) ≤ limit.
 * <p>
 * ⏱️ Time Complexity: O(N log M), where M = max(nums)
 * 💾 Space Complexity: O(1)
 * <p>
 * 🐢 Brute Force: Try every divisor from 1 to max(nums) and compute total sum — O(N*M)
 * ⚡ Optimized: Binary Search on divisor range [1..max(nums)] using integer ceil (num + d - 1)/d
 * <p>
 * 🧠 Key Concepts: Binary Search on Answer, Monotonic Behavior, Integer Math
 * 🎯 Interview Takeaways:
 * - Increasing divisor decreases the sum (monotonic property)
 * - Early exit when sum > limit improves efficiency
 * - Follow-up: Handle big inputs or floating-point precision
 */
public class FindTheSmallestDivisor {

    public static void main(String[] args) {
        FindTheSmallestDivisor obj = new FindTheSmallestDivisor();
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {8, 4, 2, 3};
        int[] arr3 = {8, 4, 2, 3};

        System.out.println(obj.smallestDivisor(arr1, 8));
        System.out.println(obj.smallestDivisor(arr2, 10));
        System.out.println(obj.smallestDivisor(arr3, 4));
    }

    /**
     * Finds the smallest divisor such that the sum of ceil divisions
     * of all elements is less than or equal to the given limit.
     *
     * @param nums  input array of integers
     * @param limit threshold value for the sum
     * @return the smallest valid divisor
     */
    public int smallestDivisor(int[] nums, int limit) {
        int low = 1, high = this.findMax(nums);
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = sumOfDivisors(nums, mid, limit);
            if (sum <= limit) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    /**
     * Computes the sum of ceil divisions for all elements in the array
     * given a specific divisor.
     *
     * @param arr     input array
     * @param divisor divisor to apply
     * @param limit   threshold for early exit
     * @return computed sum of ceil divisions
     */
    public int sumOfDivisors(int[] arr, int divisor, int limit) {
        long sum = 0;
        for (int ele : arr) {
            // integer ceil version: ceil(a/b) = (a + b - 1) / b
            sum += (ele + divisor - 1) / divisor;
            if (sum > limit) return (int) sum; // early termination
        }
        return (int) sum;
    }

    /**
     * Finds the maximum element in the given array.
     *
     * @param arr input array
     * @return maximum value in the array
     */
    public int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
}
