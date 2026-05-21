package a_3_Arrays;

import java.util.Arrays;

/**
 * Rotate Array by k positions (left rotation).
 * <p>
 * Method 1 (Brute) : rotate by one, k times.       Time O(N·K)  Space O(1)
 * Method 2 (Optimal): reverse trick — 3 reverses.  Time O(N)    Space O(1)
 * <p>
 * Reverse trick for LEFT rotation by k:
 * Step 1 → reverse [0, k-1]
 * Step 2 → reverse [k, n-1]
 * Step 3 → reverse [0, n-1]
 */
public class RotateArray {

    // ─────────────────────────────────────────────────────────────
    // Method 1: Brute — rotate by one, k times
    // ─────────────────────────────────────────────────────────────

    /*
     * Mental Model:
     *
     *   k %= n  (avoid full cycles)
     *   repeat k times:
     *       save first element
     *       shift everyone left by 1
     *       place saved element at end
     */
    public static void rotateLeft(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return;
        k %= n;
        for (int i = 0; i < k; i++) {
            rotateByOne(nums);
        }
    }

    private static void rotateByOne(int[] nums) {
        int first = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }
        nums[nums.length - 1] = first;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Optimal — reverse trick (O(N), O(1))
    // ─────────────────────────────────────────────────────────────

    /*
     * Mental Model (left rotate by k):
     *
     *   reverse [0, k-1]     ← flip the part that moves to back
     *   reverse [k, n-1]     ← flip the part that stays in front
     *   reverse [0, n-1]     ← flip the whole array to fix order
     *
     *   e.g. [1,2,3,4,5,6,7], k=3
     *   after step 1: [3,2,1, 4,5,6,7]
     *   after step 2: [3,2,1, 7,6,5,4]
     *   after step 3: [4,5,6,7, 1,2,3]  ✓
     */
    public static void rotateLeftOptimal(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return;
        k %= n;
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // TC1: standard left rotation by 3
        // [1,2,3,4,5,6,7] → [4,5,6,7,1,2,3]
        int[] arr1a = {1, 2, 3, 4, 5, 6, 7};
        int[] arr1b = {1, 2, 3, 4, 5, 6, 7};
        rotateLeft(arr1a, 3);
        rotateLeftOptimal(arr1b, 3);
        System.out.println("TC1 Brute   : " + Arrays.toString(arr1a)); // [4,5,6,7,1,2,3]
        System.out.println("TC1 Optimal : " + Arrays.toString(arr1b)); // [4,5,6,7,1,2,3]

        // TC2: k = 0 → no change
        int[] arr2a = {1, 2, 3};
        int[] arr2b = {1, 2, 3};
        rotateLeft(arr2a, 0);
        rotateLeftOptimal(arr2b, 0);
        System.out.println("TC2 Brute   : " + Arrays.toString(arr2a)); // [1,2,3]
        System.out.println("TC2 Optimal : " + Arrays.toString(arr2b)); // [1,2,3]

        // TC3: k = n → full cycle, no change
        int[] arr3a = {1, 2, 3, 4};
        int[] arr3b = {1, 2, 3, 4};
        rotateLeft(arr3a, 4);
        rotateLeftOptimal(arr3b, 4);
        System.out.println("TC3 Brute   : " + Arrays.toString(arr3a)); // [1,2,3,4]
        System.out.println("TC3 Optimal : " + Arrays.toString(arr3b)); // [1,2,3,4]

        // TC4: k > n → k %= n
        int[] arr4a = {1, 2, 3, 4, 5};
        int[] arr4b = {1, 2, 3, 4, 5};
        rotateLeft(arr4a, 7); // 7 % 5 = 2
        rotateLeftOptimal(arr4b, 7);
        System.out.println("TC4 Brute   : " + Arrays.toString(arr4a)); // [3,4,5,1,2]
        System.out.println("TC4 Optimal : " + Arrays.toString(arr4b)); // [3,4,5,1,2]

        // TC5: single element
        int[] arr5a = {42};
        int[] arr5b = {42};
        rotateLeft(arr5a, 5);
        rotateLeftOptimal(arr5b, 5);
        System.out.println("TC5 Brute   : " + Arrays.toString(arr5a)); // [42]
        System.out.println("TC5 Optimal : " + Arrays.toString(arr5b)); // [42]
    }
}
