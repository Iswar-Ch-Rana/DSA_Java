package a_3_Arrays;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        rotateArray(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    public static void rotateArray(int[] nums, int k) {
        k %= nums.length;
        while (k-- != 0) {
            rotateArrayByOne(nums);
        }

    }

    public static void rotateArrayByOne(int[] nums) {
        int first = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }
        nums[nums.length - 1] = first;
    }
}

/* 3
 * 0 1 2 3 4 5
 * 3 4 5 0 1 2
 * 1,2,3,4,5,6,7
 * 4 3 2 1 (0, 7-3-1)
 * 7 6 5 (7-3, 7-1)
 * 5 6 7 1 2 3 4
 * */
