package a_17_Heaps.Therory_And_Implmentation;

import java.util.Arrays;

/**
 * Convert a Min-Heap to a Max-Heap (and vice versa) in-place.
 * <p>
 * Idea: Simply run Floyd's build-heap algorithm with the opposite comparator.
 * The existing heap structure is irrelevant — treat the array as unsorted
 * and rebuild from scratch using heapify-down from (n/2 - 1) to 0.
 * <p>
 * Why not just negate all values?
 * That works for integers but fails for custom objects. Floyd's is universal.
 * <p>
 * Time  : O(N) — Floyd's build-heap.
 * Space : O(1) — in-place.
 */
public class ConvertMinHeapToMaxHeap {

    /*
     * Mental Model:
     *
     *   Ignore the old heap structure entirely.
     *   Run buildHeap with the opposite comparator:
     *
     *   for i from (n/2 - 1) down to 0:
     *       heapifyDown(arr, n, i)   ← using max (or min) comparator
     *
     *   This is identical to BuildHeap — only the comparison flips.
     */

    // ─────────────────────────────────────────────────────────────
    // Min → Max
    // ─────────────────────────────────────────────────────────────
    public int[] minToMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDownMax(arr, n, i);
        }
        return arr;
    }

    // ─────────────────────────────────────────────────────────────
    // Max → Min  ← only diff: find 'smallest' child
    // ─────────────────────────────────────────────────────────────
    public int[] maxToMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDownMin(arr, n, i);
        }
        return arr;
    }

    // ─────────────────────────────────────────────────────────────
    // Heapify helpers
    // ─────────────────────────────────────────────────────────────
    private void heapifyDownMax(int[] heap, int n, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && heap[left] > heap[largest]) largest = left;
            if (right < n && heap[right] > heap[largest]) largest = right;

            if (largest != i) {
                swap(heap, i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    private void heapifyDownMin(int[] heap, int n, int i) {
        while (true) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && heap[left] < heap[smallest]) smallest = left;
            if (right < n && heap[right] < heap[smallest]) smallest = right;

            if (smallest != i) {
                swap(heap, i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helper
    // ─────────────────────────────────────────────────────────────
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        ConvertMinHeapToMaxHeap obj = new ConvertMinHeapToMaxHeap();

        // TC1: Min → Max
        // [1, 3, 2, 4, 6, 5] is a valid min-heap, root must become 6
        int[] arr1 = {1, 3, 2, 4, 6, 5};
        System.out.println("TC1 before  : " + Arrays.toString(arr1));
        obj.minToMaxHeap(arr1);
        System.out.println("TC1 after   : " + Arrays.toString(arr1)); // root = 6

        // TC2: Max → Min
        // [9, 5, 6, 3, 2, 4] is a valid max-heap, root must become 2
        int[] arr2 = {9, 5, 6, 3, 2, 4};
        System.out.println("TC2 before  : " + Arrays.toString(arr2));
        obj.maxToMinHeap(arr2);
        System.out.println("TC2 after   : " + Arrays.toString(arr2)); // root = 2

        // TC3: Round-trip min → max → min, root must return to original min
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        obj.minToMaxHeap(arr3);
        System.out.println("TC3 max root: " + arr3[0]); // 7
        obj.maxToMinHeap(arr3);
        System.out.println("TC3 min root: " + arr3[0]); // 1

        // TC4: Single element — no-op
        int[] arr4 = {42};
        obj.minToMaxHeap(arr4);
        System.out.println("TC4 single  : " + Arrays.toString(arr4)); // [42]

        // TC5: Two elements
        int[] arr5 = {1, 5};
        obj.minToMaxHeap(arr5);
        System.out.println("TC5 after   : " + Arrays.toString(arr5)); // [5, 1]
    }
}
