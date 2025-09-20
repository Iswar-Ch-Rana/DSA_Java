package a_5_FAQ_Medium;

import java.util.ArrayList;
import java.util.Arrays;

public class RearrangeArray {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, -1, -3, -4};
        int[] ans = rearrangeArray(arr);
        System.out.println("Output = " + Arrays.toString(ans));
    }

    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int posIndex = 0, negIndex = 1;

        for (int num : nums) {
            if (num > 0) {
                ans[posIndex] = num;
                posIndex += 2;
            } else {
                ans[negIndex] = num;
                negIndex += 2;
            }
        }
        return ans;
    }
}
