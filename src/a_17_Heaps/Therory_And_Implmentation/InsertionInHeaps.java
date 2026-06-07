package a_17_Heaps.Therory_And_Implmentation;

import java.util.Arrays;

/**
 * Insertion in a Heap (Min-Heap and Max-Heap).
 * <p>
 * Idea: Place new element at the end, then bubble it UP
 * by swapping with its parent until the heap property is restored.
 * <p>
 * parent index = (i - 1) / 2
 * <p>
 * Time  : O(log N) — height of tree.
 * Space : O(1)     — in-place.
 */
public class InsertionInHeaps {

    /*
     * Mental Model (Min-Heap insert):
     *
     *   place val at end (index = size)
     *   while i > 0:
     *       parent = (i-1)/2
     *       if heap[parent] > heap[i]  → swap, move i up
     *       else                       → heap property restored, stop
     *
     * Max-Heap: same, but swap when heap[parent] < heap[i]
     */

    // ─────────────────────────────────────────────────────────────
    // Min-Heap insertion
    // ─────────────────────────────────────────────────────────────
    void insertMin(int[] heap, int size, int val) {
        heap[size] = val;       // place at end
        int i = size;

        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[parent] > heap[i]) {   // min-heap violation → bubble up
                swap(heap, parent, i);
                i = parent;
            } else {
                break;                      // heap property restored
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Max-Heap insertion  ← only diff: violation condition flipped
    // ─────────────────────────────────────────────────────────────
    void insertMax(int[] heap, int size, int val) {
        heap[size] = val;       // place at end
        int i = size;

        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[parent] < heap[i]) {   // max-heap violation → bubble up
                swap(heap, parent, i);
                i = parent;
            } else {
                break;                      // heap property restored
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
        InsertionInHeaps obj = new InsertionInHeaps();

        // TC1: Min-heap — insert 1 into [2, 3, 5, 4, 6, _]
        // Expected: 1 bubbles up to root → [1, 3, 2, 4, 6, 5]
        int[] minHeap1 = {2, 3, 5, 4, 6, -1};
        obj.insertMin(minHeap1, 5, 1);
        System.out.println("TC1 Min insert 1 : " + Arrays.toString(minHeap1)); // [1, 3, 2, 4, 6, 5]

        // TC2: Min-heap — insert 10 into [2, 3, 5, 4, 6, _]
        // Expected: 10 stays at end (no violations) → [2, 3, 5, 4, 6, 10]
        int[] minHeap2 = {2, 3, 5, 4, 6, -1};
        obj.insertMin(minHeap2, 5, 10);
        System.out.println("TC2 Min insert 10: " + Arrays.toString(minHeap2)); // [2, 3, 5, 4, 6, 10]

        // TC3: Max-heap — insert 9 into [6, 5, 4, 3, 2, _]
        // Expected: 9 bubbles up to root → [9, 5, 6, 3, 2, 4]
        int[] maxHeap1 = {6, 5, 4, 3, 2, -1};
        obj.insertMax(maxHeap1, 5, 9);
        System.out.println("TC3 Max insert 9 : " + Arrays.toString(maxHeap1)); // [9, 5, 6, 3, 2, 4]

        // TC4: Max-heap — insert 1 into [6, 5, 4, 3, 2, _]
        // Expected: 1 stays at end (no violations) → [6, 5, 4, 3, 2, 1]
        int[] maxHeap2 = {6, 5, 4, 3, 2, -1};
        obj.insertMax(maxHeap2, 5, 1);
        System.out.println("TC4 Max insert 1 : " + Arrays.toString(maxHeap2)); // [6, 5, 4, 3, 2, 1]

        // TC5: Single element heap — insert into empty
        int[] single = {-1};
        obj.insertMin(single, 0, 42);
        System.out.println("TC5 Min insert 42: " + Arrays.toString(single));   // [42]
    }
}
