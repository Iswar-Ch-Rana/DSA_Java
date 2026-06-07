package a_17_Heaps.Therory_And_Implmentation;

/**
 * Check if an array represents a valid Min-Heap (or Max-Heap).
 * <p>
 * Idea: Every non-leaf node must satisfy the heap property with its children.
 * Start from the last non-leaf (n/2 - 1) and check down to index 0.
 * <p>
 * Time  : O(N) — checks all non-leaf nodes.
 * Space : O(1) — no extra space.
 */
public class CheckIfAnArrayRepresentsMinHeap {

    /*
     * Mental Model:
     *
     *   for i from (n/2 - 1) down to 0:
     *       left  = 2i+1,  right = 2i+2
     *       Min-Heap: if any child < parent  → false
     *       Max-Heap: if any child > parent  → false
     *   return true
     */

    // ─────────────────────────────────────────────────────────────
    // Min-Heap check: every parent must be <= its children
    // ─────────────────────────────────────────────────────────────
    public boolean isMinHeap(int[] nums) {
        int n = nums.length;

        for (int i = (n / 2) - 1; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && nums[left] < nums[i]) return false; // child < parent = violation
            if (right < n && nums[right] < nums[i]) return false;
        }

        return true;
    }

    // ─────────────────────────────────────────────────────────────
    // Max-Heap check  ← only diff: violation is child > parent
    // ─────────────────────────────────────────────────────────────
    public boolean isMaxHeap(int[] nums) {
        int n = nums.length;

        for (int i = (n / 2) - 1; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && nums[left] > nums[i]) return false; // child > parent = violation
            if (right < n && nums[right] > nums[i]) return false;
        }

        return true;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        CheckIfAnArrayRepresentsMinHeap obj = new CheckIfAnArrayRepresentsMinHeap();

        // TC1: Valid min-heap
        // [1, 3, 2, 4, 6, 5]
        //        1
        //       / \
        //      3   2
        //     / \ /
        //    4  6 5
        System.out.println("TC1 isMinHeap [1,3,2,4,6,5]  : " + obj.isMinHeap(new int[]{1, 3, 2, 4, 6, 5}));  // true
        System.out.println("TC1 isMaxHeap [1,3,2,4,6,5]  : " + obj.isMaxHeap(new int[]{1, 3, 2, 4, 6, 5}));  // false

        // TC2: Valid max-heap
        // [9, 5, 6, 3, 2, 4]
        System.out.println("TC2 isMaxHeap [9,5,6,3,2,4]  : " + obj.isMaxHeap(new int[]{9, 5, 6, 3, 2, 4}));  // true
        System.out.println("TC2 isMinHeap [9,5,6,3,2,4]  : " + obj.isMinHeap(new int[]{9, 5, 6, 3, 2, 4}));  // false

        // TC3: Invalid — parent > child (min-heap violation)
        System.out.println("TC3 isMinHeap [5,3,2,4,6,1]  : " + obj.isMinHeap(new int[]{5, 3, 2, 4, 6, 1}));  // false

        // TC4: Single element — trivially valid
        System.out.println("TC4 isMinHeap [42]           : " + obj.isMinHeap(new int[]{42}));                 // true
        System.out.println("TC4 isMaxHeap [42]           : " + obj.isMaxHeap(new int[]{42}));                 // true

        // TC5: Two elements
        System.out.println("TC5 isMinHeap [1,2]          : " + obj.isMinHeap(new int[]{1, 2}));               // true
        System.out.println("TC5 isMinHeap [2,1]          : " + obj.isMinHeap(new int[]{2, 1}));               // false
    }
}
