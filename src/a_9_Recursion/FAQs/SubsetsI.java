package a_9_Recursion.FAQs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 🧩 Problem: Generate all possible subset sums from a given array.
 * <p>
 * Given an array of integers, return a list containing the sum of all possible
 * subsets (including the empty subset with sum 0). Each subset contributes one sum.
 * <p>
 * ⏱️ Time: O(2^N) where N = array length
 * <br>
 * Space: O(2^N) for storing all subset sums + O(N) recursion stack
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Generate all 2^N subsets explicitly (using bit masking or recursion)</li>
 *   <li>Calculate sum for each subset and store in result list</li>
 *   <li>Time: O(N × 2^N) - need to traverse each subset to calculate sum</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Recursion with Running Sum):
 * <ul>
 *   <li>Use recursion with two choices for each element: include or exclude</li>
 *   <li>Maintain running sum (currentSum) instead of building explicit subsets</li>
 *   <li>Include: Add current element to sum, recurse with index + 1</li>
 *   <li>Exclude: Keep sum unchanged, recurse with index + 1</li>
 *   <li>Base case: When index reaches array length, add currentSum to result</li>
 *   <li>Generates all 2^N subset sums efficiently without building actual subsets</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Recursion, Decision Tree, Pick/Not-Pick Pattern, Running Sum
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain optimization: tracking running sum avoids building and summing subsets separately</li>
 *   <li>Total subset sums = 2^N (each element can be included or excluded)</li>
 *   <li>Empty subset always contributes sum = 0</li>
 *   <li>Follow-up: Find subset with target sum (Subset Sum Problem), or count subsets with given sum</li>
 * </ul>
 */
public class SubsetsI {

    public static void main(String[] args) {
        SubsetsI obj = new SubsetsI();

        System.out.println(obj.subsetSums(new int[]{2, 3}));      // [0, 2, 3, 5]
        System.out.println(obj.subsetSums(new int[]{5, 2, 1}));   // [0, 1, 2, 3, 5, 6, 7, 8]
        System.out.println(obj.subsetSums(new int[]{1}));         // [0, 1]
    }

    /**
     * Generates all possible subset sums from the given array.
     * <p>
     * Uses recursion to explore all 2^N subsets efficiently by maintaining
     * a running sum instead of building explicit subsets.
     *
     * @param nums array of integers
     * @return sorted list of all possible subset sums
     */
    public List<Integer> subsetSums(int[] nums) {
        List<Integer> result = new ArrayList<>();
        generateSubsetSums(nums, 0, 0, result);
        Collections.sort(result);  // Optional: for ordered output
        return result;
    }

    /**
     * Recursive helper to generate all subset sums using pick/not-pick pattern.
     * <p>
     * Decision Tree Approach:
     * <ul>
     *   <li>Base case: When index == nums.length, add currentSum to result</li>
     *   <li>Choice 1: Include nums[index] - add to currentSum and recurse</li>
     *   <li>Choice 2: Exclude nums[index] - keep currentSum unchanged and recurse</li>
     * </ul>
     * <p>
     * Key optimization: Instead of building actual subsets and calculating their sums,
     * we maintain a running sum (currentSum) throughout the recursion.
     *
     * @param nums input array
     * @param index current position in array
     * @param currentSum running sum of elements included so far
     * @param result list storing all subset sums
     */
    private void generateSubsetSums(int[] nums, int index, int currentSum, List<Integer> result) {
        // Base case: reached end of array, record current subset sum
        if (index == nums.length) {
            result.add(currentSum);
            return;
        }

        // Choice 1: Include current element (add to sum)
        generateSubsetSums(nums, index + 1, currentSum + nums[index], result);

        // Choice 2: Exclude current element (sum remains same)
        generateSubsetSums(nums, index + 1, currentSum, result);
    }
}
