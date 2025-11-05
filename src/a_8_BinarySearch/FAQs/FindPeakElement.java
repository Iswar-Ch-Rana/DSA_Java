package a_8_BinarySearch.FAQs;

public class FindPeakElement {
    public static void main(String[] args) {
        FindPeakElement obj = new FindPeakElement();
        System.out.println(obj.findPeakElement(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 5, 1}));
        System.out.println(obj.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(obj.findPeakElement(new int[]{-2, -1, 3, 4, 5}));
    }

    /**
     * 🧩 Problem: Find any peak element in array where peak is strictly greater than neighbors.
     *    Array boundaries are treated as -∞, multiple peaks may exist, return index of any.
     *
     * ⏱️ Time: O(log N), Space: O(1)
     *
     * 🐢 Brute Force: Linear scan comparing each element with neighbors. O(N)
     *
     * ⚡ Optimized: Binary search by checking mid with mid+1. If arr[mid] < arr[mid+1],
     *    peak must be on right (climbing slope), else peak is on left or at mid itself.
     *
     * 🧠 Key Concepts: Binary Search on Array, Peak Finding, Slope Analysis
     *
     * 🎯 Interview Tips:
     *    - Explain why binary search works: always moving toward higher neighbor guarantees peak
     *    - Mention edge case: boundaries treated as -∞ (neighbors always smaller)
     *    - Follow-ups: 2D peak finding, find all peaks, mountain array variations
     *
     * @param arr input array with distinct adjacent elements
     * @return index of any peak element
     */
    public int findPeakElement(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < arr[mid + 1]) {
                // Peak is on the right side
                low = mid + 1;
            } else {
                // Peak is on the left side or mid itself
                high = mid;
            }
        }

        // When low == high, we found a peak
        return low;
    }
}
