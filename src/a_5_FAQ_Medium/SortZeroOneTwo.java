package a_5_FAQ_Medium;

import java.util.Arrays;

public class SortZeroOneTwo {
    public static void main(String[] args) {
        a_5_FAQ_Medium.SortZeroOneTwo obj = new a_5_FAQ_Medium.SortZeroOneTwo();
        int[] arr1 = {1, 0, 2, 1, 0};
        int[] arr2 = {0, 0, 1, 1, 1};
        int[] arr3 = {1, 1, 2, 2, 1};
        obj.sortZeroOneTwo(arr1);
        obj.sortZeroOneTwo(arr2);
        obj.sortZeroOneTwo(arr3);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }

    // Solution 2 TIME - O(N) SPACE - O(1)
    public void sortZeroOneTwo(int[] nums) {
        int low = 0;             // position for next 0
        int mid = 0;             // current index
        int high = nums.length - 1; // position for next 2

        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap with low
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                // Swap with high
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
                // DON’T mid++ here → because swapped element at mid-needs re-check
            }
        }
    }

    // Solution 1 TIME - O(2N) SPACE - O(1)
    public void sortZeroOneTwo1(int[] nums) {
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;

        // Counting the number of 0s, 1s, and 2s in the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) cnt0++;
            else if (nums[i] == 1) cnt1++;
            else cnt2++;
        }

        /* Placing the elements in the
           original array based on counts */
        // placing 0's
        for (int i = 0; i < cnt0; i++) nums[i] = 0;

        // placing 1's
        for (int i = cnt0; i < cnt0 + cnt1; i++) nums[i] = 1;

        // placing 2's
        for (int i = cnt0 + cnt1; i < nums.length; i++) nums[i] = 2;
    }
}
