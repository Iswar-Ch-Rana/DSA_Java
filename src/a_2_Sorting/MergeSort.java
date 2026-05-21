package a_2_Sorting;

import java.util.Arrays;

/**
 * Merge Sort — Divide and Conquer, stable sort.
 * <p>
 * Mental Model:
 * split array in half recursively until size 1
 * merge two sorted halves back together (two-pointer)
 * repeat until fully merged
 * <p>
 * Time  : O(N log N) — log N levels, O(N) merge at each level.
 * Space : O(N)       — temporary array used during merge.
 */
public class MergeSort {

    // ─────────────────────────────────────────────────────────────
    // Merge Sort (in-place on original array)
    // ─────────────────────────────────────────────────────────────
    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return; // base case: single element

        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid);       // sort left half
        mergeSort(nums, mid + 1, right);  // sort right half
        mergeSorted(nums, left, mid, right);  // merge both halves
    }

    // Merge two sorted sub-arrays: nums[left..mid] and nums[mid+1..right]
    private static void mergeSorted(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;       // pointer for left half
        int j = mid + 1;    // pointer for right half
        int k = 0;          // pointer for temp

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // Copy remaining elements
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];

        // Write back to original array
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    // ─────────────────────────────────────────────────────────────
    // Merge Two Sorted Arrays → new sorted array
    // ─────────────────────────────────────────────────────────────

    /*
     * Mental Model:
     *   two pointers i, j on each array
     *   pick smaller → advance that pointer
     *   drain leftover
     */
    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            result[k++] = (a[i] <= b[j]) ? a[i++] : b[j++];
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // TC1: reverse sorted
        int[] tc1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        mergeSort(tc1, 0, tc1.length - 1);
        System.out.println("TC1: " + Arrays.toString(tc1)); // [1,2,3,4,5,6,7,8,9]

        // TC2: already sorted
        int[] tc2 = {1, 2, 3, 4, 5};
        mergeSort(tc2, 0, tc2.length - 1);
        System.out.println("TC2: " + Arrays.toString(tc2)); // [1,2,3,4,5]

        // TC3: duplicates
        int[] tc3 = {3, 1, 4, 1, 5, 9, 2, 6};
        mergeSort(tc3, 0, tc3.length - 1);
        System.out.println("TC3: " + Arrays.toString(tc3)); // [1,1,2,3,4,5,6,9]

        // TC4: single element
        int[] tc4 = {42};
        mergeSort(tc4, 0, tc4.length - 1);
        System.out.println("TC4: " + Arrays.toString(tc4)); // [42]

        // TC5: merge two sorted arrays
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        System.out.println("TC5: " + Arrays.toString(mergeTwoSortedArrays(a, b))); // [1,2,3,4,5,6,7,8]

        // TC6: merge with unequal sizes
        int[] c = {1, 2, 3, 4, 6, 7};
        int[] d = {2, 5, 6};
        System.out.println("TC6: " + Arrays.toString(mergeTwoSortedArrays(c, d))); // [1,2,2,3,4,5,6,6,7]
    }
}

