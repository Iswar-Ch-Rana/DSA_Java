package a_17_Heaps.Therory_And_Implmentation;

import java.util.Arrays;

/**
 * Heap Sort — in-place, comparison-based sort using a Max-Heap.
 * <p>
 * Two phases:
 * 1. Build Max-Heap  : Floyd's algorithm, start from (n/2)-1 down to 0 → O(N)
 * 2. Extract in order: swap root (max) with last, shrink heap, heapify-down → O(N log N)
 * <p>
 * Time  : O(N log N) — build O(N) + N extractions × O(log N)
 * Space : O(1)       — in-place, no extra array
 * Stable: No
 */
public class HeapSort {

    /*
     * Mental Model:
     *
     *   Phase 1 — Build Max-Heap:
     *       heapify-down from (n/2)-1 → 0
     *       after this: arr[0] = max
     *
     *   Phase 2 — Sort:
     *       for i = n-1 down to 1:
     *           swap(arr[0], arr[i])   → place max at end
     *           heapify-down(0..i-1)   → restore heap on shrunk range
     *
     *   Result: ascending order (max elements sink to the back)
     */

    // ─────────────────────────────────────────────────────────────
    // Public API
    // ─────────────────────────────────────────────────────────────

    public void heapSort(int[] arr) {
        int n = arr.length;

        // Phase 1: build max-heap in-place — O(N)
        buildMaxHeap(arr);

        // Phase 2: repeatedly extract max to end — O(N log N)
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);            // move current max to sorted end
            heapifyDown(arr, i, 0);     // restore heap property on shrunk range [0..i-1]
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Build Max-Heap: Floyd's algorithm — O(N)
    // ─────────────────────────────────────────────────────────────

    private void buildMaxHeap(int[] arr) {
        int n = arr.length;
        // Start at last internal node; leaves are already trivial heaps.
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Heapify-Down (Max-Heap) — O(log N)
    // ─────────────────────────────────────────────────────────────

    private void heapifyDown(int[] heap, int size, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && heap[left] > heap[largest]) largest = left;
            if (right < size && heap[right] > heap[largest]) largest = right;

            if (largest != i) {
                swap(heap, i, largest);
                i = largest;            // continue bubbling down
            } else {
                break;                  // heap property restored
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
        HeapSort obj = new HeapSort();

        // TC1: unsorted array
        int[] arr1 = {7, 4, 1, 5, 3};
        obj.heapSort(arr1);
        System.out.println("TC1: " + Arrays.toString(arr1)); // [1, 3, 4, 5, 7]

        // TC2: already sorted
        int[] arr2 = {1, 2, 3, 4, 5};
        obj.heapSort(arr2);
        System.out.println("TC2: " + Arrays.toString(arr2)); // [1, 2, 3, 4, 5]

        // TC3: reverse sorted
        int[] arr3 = {5, 4, 3, 2, 1};
        obj.heapSort(arr3);
        System.out.println("TC3: " + Arrays.toString(arr3)); // [1, 2, 3, 4, 5]

        // TC4: duplicates
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6};
        obj.heapSort(arr4);
        System.out.println("TC4: " + Arrays.toString(arr4)); // [1, 1, 2, 3, 4, 5, 6, 9]

        // TC5: single element
        int[] arr5 = {42};
        obj.heapSort(arr5);
        System.out.println("TC5: " + Arrays.toString(arr5)); // [42]
    }
}
