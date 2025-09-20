package a_2_Sorting;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }

    public static int[] bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
        return nums;
    }
}
/*
🔹 Time & Space Complexity
    1. Time: Worst/O(n²), Average/O(n²), Best/O(n) with the isSorted flag.
    2. Space: O(1) (in-place).

🔹 Interview Tips

 - Rarely will they ask you to implement Bubble Sort. Instead, they might ask:
 “Why is Bubble Sort inefficient compared to Quick Sort?”
 “What’s the best/worst case complexity?”
 “Is it stable? Is it in-place?”
 - Be ready to compare sorting algorithms on time, space, stability, and use cases.
 - They might give a follow-up: “If the array is almost sorted, which algorithm would you choose?” → Answer: Insertion Sort.
Don’t just code — be able to talk about trade-offs.
* */
