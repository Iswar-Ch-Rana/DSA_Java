package a_5_FAQ_Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeArray {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, -1, -3, -4};
        int[] ans = rearrangeArray(arr);
        System.out.println("Output = " + Arrays.toString(ans));
    }

    /** Solution - 2 TIME - O(n) Space - O(n) */
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

    /** Solution - 1 TIME -  O(N+N/2) Space - O(N) */
    public int[] rearrangeArray1(int[] nums) {
        int n = nums.length;

        /* Define 2 vectors, one for storing positive
        and other for negative elements of the array.*/
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        // Segregate the array into positives and negatives.
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) pos.add(nums[i]);
            else neg.add(nums[i]);
        }

        // Positives on even indices, negatives on odd.
        for (int i = 0; i < n / 2; i++) {
            nums[2 * i] = pos.get(i);
            nums[2 * i + 1] = neg.get(i);
        }

        // Return the result
        return nums;
    }
}
