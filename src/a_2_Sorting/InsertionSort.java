package a_2_Sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(insertionSort(arr)));
    }
    public static int[] insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;

            // Shift elements until we find correct spot for temp
            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j--;
            }

            // Place temp at the found position
            nums[j + 1] = temp;
        }
        return nums;
    }
}

/*
🔹 Patterns/Insights

 - Divide into sorted & unsorted parts: left part is sorted, right part is unsorted.
 - Shifting pattern: Instead of swapping repeatedly (like Bubble Sort), Insertion Sort shifts elements to make space.
 - Best case: already sorted → only O(n) (just one comparison per element).
 - Worst case: reverse sorted → O(n²).
 - Space complexity: O(1) (in-place).

🔹 Interview Tips

 - Interviewers rarely ask you to code Insertion Sort. Instead, they might ask:
“Why is Insertion Sort good for small or nearly sorted arrays?”
“Which sorting algorithm would you choose for linked lists?” (Answer: Insertion Sort works well since shifting is cheap in linked lists).
 - Be ready to compare: Bubble vs Insertion vs Selection (time complexity, number of swaps, best-case behavior).
* */
