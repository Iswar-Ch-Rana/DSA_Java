package a_17_Heaps.Therory_And_Implmentation;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Kth Largest Element in an Array.
 * <p>
 * Optimal : Min-Heap of size k — root is always the kth largest seen so far.
 * Brute   : Sort descending, return index k-1.
 * <p>
 * Time  : O(N log k) optimal  |  O(N log N) brute
 * Space : O(k) optimal        |  O(1) brute (in-place sort)
 */
public class KthLargestElementInAnArray {

    /*
     * Mental Model (Optimal):
     *
     *   Maintain a min-heap of size k.
     *   For each element:
     *       if heap.size < k       → add freely
     *       else if elem > heap.top → pop min, add elem  (a larger element arrived)
     *
     *   After all elements: heap.top = kth largest
     *   (heap holds the k largest elements; smallest of them = kth largest)
     */

    // ─────────────────────────────────────────────────────────────
    // Optimal: Min-Heap of size k — O(N log k)
    // ─────────────────────────────────────────────────────────────

    public int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // min-heap (default)

        for (int num : arr) {
            if (minHeap.size() < k) {
                minHeap.add(num);                    // fill heap until size k
            } else if (num > minHeap.peek()) {
                minHeap.poll();                      // remove current kth largest
                minHeap.offer(num);                  // replace with larger element
            }
        }

        return minHeap.isEmpty() ? -1 : minHeap.peek(); // root = kth largest
    }

    // ─────────────────────────────────────────────────────────────
    // Brute: sort descending, pick index k-1 — O(N log N)
    // ─────────────────────────────────────────────────────────────

    public int kthLargestBrute(int[] arr, int k) {
        Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(boxed, Collections.reverseOrder()); // descending sort
        return boxed[k - 1];
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        KthLargestElementInAnArray sol = new KthLargestElementInAnArray();

        // TC1: standard case — k=2, expected: 5
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        System.out.println("TC1 Optimal: " + sol.kthLargest(arr1, 2));      // 5
        System.out.println("TC1 Brute  : " + sol.kthLargestBrute(arr1, 2)); // 5

        // TC2: k=4, expected: 4
        int[] arr2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("TC2 Optimal: " + sol.kthLargest(arr2, 4));      // 4
        System.out.println("TC2 Brute  : " + sol.kthLargestBrute(arr2, 4)); // 4

        // TC3: k=1 → largest element, expected: 9
        int[] arr3 = {1, 5, 9, 3, 7};
        System.out.println("TC3 Optimal: " + sol.kthLargest(arr3, 1));      // 9
        System.out.println("TC3 Brute  : " + sol.kthLargestBrute(arr3, 1)); // 9

        // TC4: k=n → smallest element, expected: 1
        int[] arr4 = {1, 5, 9, 3, 7};
        System.out.println("TC4 Optimal: " + sol.kthLargest(arr4, 5));      // 1
        System.out.println("TC4 Brute  : " + sol.kthLargestBrute(arr4, 5)); // 1

        // TC5: duplicates — k=2, expected: 2
        int[] arr5 = {2, 2, 2, 2};
        System.out.println("TC5 Optimal: " + sol.kthLargest(arr5, 2));      // 2
        System.out.println("TC5 Brute  : " + sol.kthLargestBrute(arr5, 2)); // 2
    }
}
