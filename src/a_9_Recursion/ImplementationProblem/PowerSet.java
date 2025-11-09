package a_9_Recursion.ImplementationProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * 🧩 Problem: Generate all possible subsets (power set) of a given array.
 * <p>
 * Given an array of distinct integers, return all possible subsets including
 * the empty subset. The solution must not contain duplicate subsets.
 * <p>
 * ⏱️ Time: O(N × 2^N) where N = array length
 * <br>
 * Space: O(N) for recursion stack (excluding output space)
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Use bit masking: iterate from 0 to 2^N - 1, each bit represents include/exclude</li>
 *   <li>Time complexity: O(N × 2^N) - same as optimal but less intuitive</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Recursion + Backtracking):
 * <ul>
 *   <li>For each element, make two recursive choices: include it or exclude it</li>
 *   <li>Base case: when index reaches array length, add current subset to result</li>
 *   <li>Include: Add element to temp, recurse, then remove (backtrack)</li>
 *   <li>Exclude: Simply recurse without adding element</li>
 *   <li>Generates all 2^N subsets using decision tree exploration</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Recursion, Backtracking, Decision Tree, Power Set, Pick/Not-Pick Pattern
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain the binary decision tree: each element has 2 choices (include/exclude)</li>
 *   <li>Mention why backtracking is needed: to reuse the temp list for all branches</li>
 *   <li>Total subsets = 2^N (each element contributes 2 choices)</li>
 *   <li>Follow-up: Handle duplicates in array (requires sorting + skipping duplicates)</li>
 * </ul>
 */
public class PowerSet {

    public static void main(String[] args) {
        PowerSet obj = new PowerSet();
        System.out.println("Input: {1, 2, 3}\nOutput: " + obj.powerSet(new int[]{1, 2, 3}));
        System.out.println("Input: {1, 2}\nOutput: " + obj.powerSet(new int[]{1, 2}));
        System.out.println("Input: {0}\nOutput: " + obj.powerSet(new int[]{0}));
    }

    /**
     * Generates all possible subsets (power set) of the given array.
     * <p>
     * Uses recursion with backtracking to explore all combinations.
     * Each element has two choices: include in current subset or exclude.
     *
     * @param nums array of distinct integers
     * @return list of all possible subsets (including empty subset)
     */
    public List<List<Integer>> powerSet(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        solve(nums, 0, ans, temp);
        return ans;
    }

    /**
     * Recursive helper function to generate all subsets using backtracking.
     * <p>
     * Decision Tree Approach:
     * <ul>
     *   <li>Base case: When index == nums.length, add current subset to result</li>
     *   <li>Choice 1: Include nums[index] - add to temp, recurse, then backtrack (remove)</li>
     *   <li>Choice 2: Exclude nums[index] - recurse without adding</li>
     * </ul>
     *
     * @param nums input array
     * @param index current position in array
     * @param ans result list storing all subsets
     * @param temp current subset being built
     */
    private void solve(int[] nums, int index, List<List<Integer>> ans, List<Integer> temp) {
        // Base case: reached end of array, add current subset
        if (index == nums.length) {
            ans.add(new ArrayList<>(temp));  // Create copy to avoid reference issues
            return;
        }

        // Choice 1: Include current element
        temp.add(nums[index]);
        solve(nums, index + 1, ans, temp);

        // Backtrack: Remove element to explore other branches
        temp.removeLast();

        // Choice 2: Exclude current element
        solve(nums, index + 1, ans, temp);
    }
}
