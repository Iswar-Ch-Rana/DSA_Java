package a_4_LogicBuilding;

import java.util.Arrays;

public class MoveZeroes {
    public static void main(String[] args) {
        int[] arr = {0, 1, 4, 0, 5, 2};
        int[] arr1 = {0, 0, 0, 1, 3, -2};
        moveZeroes(arr);
        moveZeroes(arr1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
    }

    public static void moveZeroes(int[] nums) {
        int pos = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[pos++] = num;
            }
        }
        for (int i = pos ; i < nums.length ; i++) {
            nums[i] = 0;
        }
    }
}
