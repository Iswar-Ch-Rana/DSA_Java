package a_2_Sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {7, 4, 1, 5, 3};
        System.out.println(Arrays.toString(selectionSort(arr)));
    }

    public static int[] selectionSort(int[] numbs) {
        for (int i = 0; i < numbs.length - 1; i++) { // no need last iteration
            int smallestNumIndex = i;
            for (int j = i + 1; j < numbs.length; j++) {
                if (numbs[j] < numbs[smallestNumIndex]) {
                    smallestNumIndex = j;
                }
            }
            swap(numbs, i, smallestNumIndex);
        }
        return numbs;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/* IMP
🔹 Patterns / Insights

    1. Sorting in-place: no extra array (O(1) space).
    2. Deterministic comparisons: always n² comparisons, even if array is sorted.
    3. Used when memory is tight but speed isn’t critical.
    4. Typical pattern: two nested loops (outer = position to fill, inner = find min).

🔹 Complexity

    1. Time: O(n²) — always, even for sorted arrays.
    2. Space: O(1) — in-place.
    3. Stable? No (order of equal elements may change).

🔹 Interview Tips

    1. Interviewers often ask:
    Why is Selection Sort rarely used? Answer: Because it’s always O(n²), whereas Insertion Sort is O(n) on nearly sorted input.
    2. Edge case: single-element or empty array → your loop should handle it safely (and it does).
    3. They might ask: How to make it stable? → One way is to insert instead of swapping.
 */
