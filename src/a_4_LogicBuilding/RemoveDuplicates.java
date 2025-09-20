package a_4_LogicBuilding;

import java.util.Arrays;

class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int numberOfDuplicate = removeDuplicates(arr);
        System.out.println("Output: " + numberOfDuplicate);
        System.out.println("arr: " + Arrays.toString(arr));
    }
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return nums.length;
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[++i] = nums[j];
            }
        }
        return i+1;
    }
}