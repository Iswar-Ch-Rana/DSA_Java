package a_5_FAQ_Medium;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation obj = new NextPermutation();
        int[] nums = {1, 2, 3, 6, 5, 4};
        obj.nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // Output: [1, 2, 4, 3, 5, 6]
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length; // Size of the given array

        // To store the index of the first smaller element from right
        int ind = -1;

        // Find the first index from the end where nums[i] < nums[i+1]
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i;
                break;
            }
        }

        /* If no such index exists, array is in descending order
           So, reverse it to get the smallest permutation */
        if (ind == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Find the element just greater than nums[ind] from the end
        for (int i = n - 1; i > ind; i--) {
            if (nums[i] > nums[ind]) {
                swap(nums, i, ind); // Swap with nums[ind]
                break;
            }
        }

        // Reverse the right half to get the next smallest permutation
        reverse(nums, ind + 1, n - 1);
        return;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}

