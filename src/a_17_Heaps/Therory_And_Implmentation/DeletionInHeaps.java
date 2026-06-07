package a_17_Heaps.Therory_And_Implmentation;

import java.util.Arrays;

/**
 * Deletion from a Heap (always deletes the ROOT — min or max).
 * <p>
 * Idea: Replace root with last element, shrink size by 1,
 * then bubble DOWN by swapping with the smaller (min-heap) or
 * larger (max-heap) child until heap property is restored.
 * <p>
 * children indices: left = 2i+1,  right = 2i+2
 * <p>
 * Time  : O(log N) — height of tree.
 * Space : O(1)     — in-place.
 */
public class DeletionInHeaps {

    /*
     * Mental Model (Min-Heap delete root):
     *
     *   heap[0] = heap[size-1]   → overwrite root with last element
     *   size--                   → shrink heap
     *   i = 0
     *   while true:
     *       smallest = i, check left, check right
     *       if smallest != i → swap, move i down
     *       else             → heap property restored, stop
     *
     * Max-Heap: same, but track 'largest' instead of 'smallest'
     */

    // ─────────────────────────────────────────────────────────────
    // Min-Heap deletion (removes minimum / root)
    // ─────────────────────────────────────────────────────────────
    int deleteMin(int[] heap, int size) {
        if (size == 0) return -1;
        int removed = heap[0];

        heap[0] = heap[size - 1]; // replace root with last
        size--;                   // shrink heap

        int i = 0;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;

            if (smallest != i) {
                swap(heap, i, smallest); // violation → bubble down
                i = smallest;
            } else {
                break;                   // heap property restored
            }
        }
        return removed;
    }

    // ─────────────────────────────────────────────────────────────
    // Max-Heap deletion  ← only diff: track 'largest' instead of 'smallest'
    // ─────────────────────────────────────────────────────────────
    int deleteMax(int[] heap, int size) {
        if (size == 0) return -1;
        int removed = heap[0];

        heap[0] = heap[size - 1]; // replace root with last
        size--;                   // shrink heap

        int i = 0;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < size && heap[left] > heap[largest]) largest = left;
            if (right < size && heap[right] > heap[largest]) largest = right;

            if (largest != i) {
                swap(heap, i, largest); // violation → bubble down
                i = largest;
            } else {
                break;                  // heap property restored
            }
        }
        return removed;
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
        DeletionInHeaps obj = new DeletionInHeaps();

        // TC1: Min-heap — delete root (1) from [1, 3, 2, 4, 6, 5]
        // Expected: removes 1, heap becomes [2, 3, 5, 4, 6]
        int[] minHeap1 = {1, 3, 2, 4, 6, 5};
        int removed1 = obj.deleteMin(minHeap1, 6);
        System.out.println("TC1 removed : " + removed1);                             // 1
        System.out.println("TC1 heap    : " + Arrays.toString(Arrays.copyOf(minHeap1, 5))); // [2, 3, 5, 4, 6]

        // TC2: Min-heap — delete root (2) from [2, 3, 5, 4, 6]
        // Expected: removes 2, heap becomes [3, 4, 5, 6]
        int[] minHeap2 = {2, 3, 5, 4, 6};
        int removed2 = obj.deleteMin(minHeap2, 5);
        System.out.println("TC2 removed : " + removed2);                             // 2
        System.out.println("TC2 heap    : " + Arrays.toString(Arrays.copyOf(minHeap2, 4))); // [3, 4, 5, 6]

        // TC3: Max-heap — delete root (9) from [9, 5, 6, 3, 2, 4]
        // Expected: removes 9, heap becomes [6, 5, 4, 3, 2]
        int[] maxHeap1 = {9, 5, 6, 3, 2, 4};
        int removed3 = obj.deleteMax(maxHeap1, 6);
        System.out.println("TC3 removed : " + removed3);                             // 9
        System.out.println("TC3 heap    : " + Arrays.toString(Arrays.copyOf(maxHeap1, 5))); // [6, 5, 4, 3, 2]

        // TC4: Single element
        int[] single = {42};
        int removed4 = obj.deleteMin(single, 1);
        System.out.println("TC4 removed : " + removed4);                             // 42

        // TC5: Empty heap
        int[] empty = {};
        System.out.println("TC5 removed : " + obj.deleteMin(empty, 0));              // -1
    }
}
