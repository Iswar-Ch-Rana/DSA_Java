package a_17_Heaps.Therory_And_Implmentation;

import java.util.Arrays;

/**
 * Build a Heap from an arbitrary array (Heapify / Floyd's Algorithm).
 * <p>
 * Idea: Start from the last non-leaf node (n/2 - 1) and heapify-down
 * each node toward index 0. Leaves need no work — they're already valid heaps.
 * <p>
 * Why start at (n/2 - 1)?
 * Nodes at index >= n/2 are all leaf nodes — no children to violate order.
 * <p>
 * Time  : O(N) — counterintuitively faster than inserting one-by-one O(N log N).
 * Space : O(1) — in-place.
 */
public class HeapifyAlgorithm {

    /*
     * Mental Model (Min-Heap build):
     *
     *   start at i = (n/2) - 1   ← last non-leaf
     *   heapify-down each i down to 0
     *
     *   heapify-down(i):
     *       smallest = i, check left (2i+1), check right (2i+2)
     *       if smallest != i → swap, continue down
     *       else             → stop
     *
     * Max-Heap: same, but find 'largest' child instead of 'smallest'
     */

    // ─────────────────────────────────────────────────────────────
    // Min-Heap build
    // ─────────────────────────────────────────────────────────────
    void buildMinHeap(int[] arr) {
        int n = arr.length;
        // start from last non-leaf, go up to root
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDownMin(arr, n, i);
        }
    }

    private void heapifyDownMin(int[] heap, int size, int i) {
        while (true) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;

            if (smallest != i) {
                swap(heap, i, smallest); // violation → bubble down
                i = smallest;
            } else {
                break;                   // subtree is a valid min-heap
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Max-Heap build  ← only diff: find 'largest' child
    // ─────────────────────────────────────────────────────────────
    void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDownMax(arr, n, i);
        }
    }

    private void heapifyDownMax(int[] heap, int size, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && heap[left] > heap[largest]) largest = left;
            if (right < size && heap[right] > heap[largest]) largest = right;

            if (largest != i) {
                swap(heap, i, largest); // violation → bubble down
                i = largest;
            } else {
                break;                  // subtree is a valid max-heap
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
        HeapifyAlgorithm obj = new HeapifyAlgorithm();

        // TC1: Min-heap build from reverse-sorted array
        // [9, 8, 7, 6, 5, 4, 3, 2, 1] → min-heap, root must be 1
        int[] arr1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        obj.buildMinHeap(arr1);
        System.out.println("TC1 Min-Heap: " + Arrays.toString(arr1)); // root = arr1[0] = 1

        // TC2: Max-heap build from sorted array
        // [1, 2, 3, 4, 5, 6, 7, 8, 9] → max-heap, root must be 9
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        obj.buildMaxHeap(arr2);
        System.out.println("TC2 Max-Heap: " + Arrays.toString(arr2)); // root = arr2[0] = 9

        // TC3: Min-heap from arbitrary array
        int[] arr3 = {3, 1, 6, 5, 2, 4};
        obj.buildMinHeap(arr3);
        System.out.println("TC3 Min-Heap: " + Arrays.toString(arr3)); // root = arr3[0] = 1

        // TC4: Single element — already a valid heap
        int[] arr4 = {42};
        obj.buildMinHeap(arr4);
        System.out.println("TC4 Single  : " + Arrays.toString(arr4)); // [42]

        // TC5: Two elements
        int[] arr5 = {5, 1};
        obj.buildMinHeap(arr5);
        System.out.println("TC5 Two     : " + Arrays.toString(arr5)); // [1, 5]
    }
}
