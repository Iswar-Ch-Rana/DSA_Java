package a_11_BitManipulation.Problems;

import java.util.ArrayList;
import java.util.List;

public class powerSetBitManipulation {
    public static void main(String[] args) {
        System.out.println(powerSet(new int[]{1, 2, 3}));
        System.out.println(powerSet(new int[]{1, 2}));
        System.out.println(powerSet(new int[]{}));
        System.out.println(powerSet(new int[]{0}));
    }

    // TIME - O(2^N*N) SPACE - O(2^N*N)
    public static List<List<Integer>> powerSet(int[] nums) {

        // Variable to store size of array
        int n = nums.length;

        // To store the answer
        List<List<Integer>> ans = new ArrayList<>();

        /* Variable to store the
        count of total subsets */
        int count = (1 << n);

        // Traverse for every value
        for (int val = 0; val < count; val++) {

            // To store the current subset
            List<Integer> subset = new ArrayList<>();

            // Traverse on n bits
            for (int i = 0; i < n; i++) {
                if ((val & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }

            /* Add the current subset
            to final answer */
            ans.add(subset);
        }

        // Return stored answer
        return ans;
    }

    public static List<List<Integer>> powerSet1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(nums, 0, ans, new ArrayList<>());
        return ans;
    }

    private static void solve(int[] nums, int index, List<List<Integer>> ans, List<Integer> temp) {
        if (index >= nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        // include
        temp.add(nums[index]);
        solve(nums, index + 1, ans, temp);
        // exclude
        temp.remove(temp.size() - 1);
        solve(nums, index + 1, ans, temp);
    }
}
