package a_17_Heaps.FAQs;

import java.util.PriorityQueue;

/**
 * Kth Largest Element in a Stream.
 * <p>
 * Idea: Maintain a Min-Heap of size k.
 * The root (min of the heap) is always the kth largest seen so far.
 * <p>
 * Time  : O(N log k) — each add() does at most one poll + offer = O(log k).
 * Space : O(k)       — heap holds exactly k elements.
 */

/*
 * Mental Model:
 *
 *   heap = min-heap of size k  (holds k largest seen so far)
 *   root = smallest of those k = kth largest
 *
 *   addToHeap(val):
 *       if size < k        → add directly
 *       if val > root      → pop root, add val  (new val is larger than kth)
 *       else               → ignore             (val not in top k)
 *
 *   add(val) → addToHeap(val), return heap.peek()
 */
public class KthLargestInStream {

    private final PriorityQueue<Integer> minHeap;
    private final int k;

    // ─────────────────────────────────────────────────────────────
    // Constructor: seed the heap with the initial array.
    // ─────────────────────────────────────────────────────────────
    KthLargestInStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int num : nums) {
            addToHeap(num);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // add: insert new stream element, return current kth largest.
    // ─────────────────────────────────────────────────────────────
    public int add(int val) {
        addToHeap(val);
        return minHeap.peek(); // root = kth largest
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helper: maintain heap of exactly k largest elements.
    // ─────────────────────────────────────────────────────────────
    private void addToHeap(int val) {
        if (minHeap.size() < k) {
            minHeap.offer(val);                   // heap not full yet
        } else if (val > minHeap.peek()) {
            minHeap.poll();                       // evict smallest of top-k
            minHeap.offer(val);                   // insert new larger value
        }
        // else: val <= kth largest → not in top k, ignore
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // TC1: LeetCode example — k=3, nums=[4,5,8,2], stream adds: 3,5,10,9,4
        //      after init:      heap = [4,5,8]   → kth = 4
        //      add(3)  → ignore                  → kth = 4
        //      add(5)  → evict 4, add 5          → kth = 5
        //      add(10) → evict 5, add 10         → kth = 5
        //      add(9)  → evict 5, add 9          → kth = 8
        //      add(4)  → ignore                  → kth = 8
        KthLargestInStream obj1 = new KthLargestInStream(3, new int[]{4, 5, 8, 2});
        System.out.println("TC1:");
        System.out.println("  add(3)  → " + obj1.add(3));   // 4
        System.out.println("  add(5)  → " + obj1.add(5));   // 5
        System.out.println("  add(10) → " + obj1.add(10));  // 5
        System.out.println("  add(9)  → " + obj1.add(9));   // 8
        System.out.println("  add(4)  → " + obj1.add(4));   // 8

        // TC2: k=1 — always return the max seen so far
        KthLargestInStream obj2 = new KthLargestInStream(1, new int[]{2});
        System.out.println("\nTC2 (k=1):");
        System.out.println("  add(3)  → " + obj2.add(3));   // 3
        System.out.println("  add(1)  → " + obj2.add(1));   // 3
        System.out.println("  add(5)  → " + obj2.add(5));   // 5

        // TC3: all duplicates
        KthLargestInStream obj3 = new KthLargestInStream(2, new int[]{5, 5, 5});
        System.out.println("\nTC3 (duplicates, k=2):");
        System.out.println("  add(5)  → " + obj3.add(5));   // 5
        System.out.println("  add(3)  → " + obj3.add(3));   // 5

        // TC4: initial array larger than k
        KthLargestInStream obj4 = new KthLargestInStream(2, new int[]{10, 1, 3, 7, 5});
        System.out.println("\nTC4 (init larger than k=2):");
        System.out.println("  add(6)  → " + obj4.add(6));   // 7
        System.out.println("  add(11) → " + obj4.add(11));  // 10

        // TC5: empty initial array
        KthLargestInStream obj5 = new KthLargestInStream(1, new int[]{});
        System.out.println("\nTC5 (empty init, k=1):");
        System.out.println("  add(7)  → " + obj5.add(7));   // 7
        System.out.println("  add(2)  → " + obj5.add(2));   // 7
    }
}
