package a_3_Arrays;

import java.util.Arrays;

public class RotateArrayByOne {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        rotateArrayByOne(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void rotateArrayByOne(int[] nums) {
        int size = nums.length;
        int firstEle = nums[0];
        for (int i = 1 ; i <= size-1 ; i++) {
            nums[i-1] = nums[i];
        }
        nums[size-1] = firstEle;
    }
}
