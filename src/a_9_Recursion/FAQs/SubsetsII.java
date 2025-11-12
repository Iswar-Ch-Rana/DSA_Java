package a_9_Recursion.FAQs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🧩 Problem: Generate all unique subsets (power set) from array with duplicates.
 * <p>
 * Given an array of integers that may contain duplicates, return all possible
 * subsets (the power set). The solution set must not contain duplicate subsets.
 * <p>
 * ⏱️ Time: O(N × 2^N) where N = array length
 * <br>
 * Space: O(N) for recursion stack
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Generate all 2^N subsets (including duplicates)</li>
 *   <li>Use HashSet to store subsets as sorted strings/lists to eliminate duplicates</li>
 *   <li>Time: O(N × 2^N × log N) - additional cost for sorting and hashing</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Backtracking with Smart Duplicate Skipping):
 * <ul>
 *   <li>Sort array first to group duplicate elements together</li>
 *   <li>Use pick/not-pick pattern with special duplicate handling in "exclude" branch</li>
 *   <li>Include: Add current element, recurse with index+1</li>
 *   <li>Exclude: Remove element, then skip ALL consecutive duplicates using while loop</li>
 *   <li>Key insight: When excluding an element, skip all its duplicates at once to avoid
 *       generating same subset through different paths</li>
 *   <li>No need for HashSet - duplicates prevented during generation itself</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Backtracking, Duplicate Skipping, Pick/Not-Pick Pattern, Sorting Optimization
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain why sorting helps: groups duplicates together for efficient skipping</li>
 *   <li>Critical insight: Skip duplicates only in "exclude" branch, not "include" branch</li>
 *   <li>Why? Include branch explores all ways to use duplicates; exclude branch skips all at once</li>
 *   <li>Example: [1,2,2] - if excluding first 2, skip second 2 too to avoid duplicate subsets</li>
 *   <li>Follow-up: Compare with for-loop approach (Combination Sum II style)</li>
 * </ul>
 */
public class SubsetsII {

    public static void main(String[] args) {
        SubsetsII obj = new SubsetsII();

        System.out.println(obj.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(obj.subsetsWithDup(new int[]{1, 2}));
        System.out.println(obj.subsetsWithDup(new int[]{1, 3, 3}));
        System.out.println(obj.subsetsWithDup(new int[]{1, 3, 3, 1}));
    }

    /**
     * Generates all unique subsets from array that may contain duplicates.
     * <p>
     * Uses backtracking with intelligent duplicate skipping to avoid
     * generating duplicate subsets during exploration itself.
     *
     * @param nums array of integers (may contain duplicates)
     * @return list of all unique subsets (power set)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // Sort to group duplicates together
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Recursive backtracking with smart duplicate skipping in exclude branch.
     * <p>
     * Decision Tree Approach:
     * <ul>
     *   <li>Base case: When index == array length, add current subset to result</li>
     *   <li>Choice 1 (Include): Add nums[start] to current, recurse with start+1</li>
     *   <li>Choice 2 (Exclude): Remove nums[start], then skip ALL consecutive duplicates,
     *       recurse with updated start+1</li>
     * </ul>
     * <p>
     * Key duplicate handling logic:
     * <pre>
     * {@code
     * while (start < nums.length - 1 && nums[start] == nums[start + 1])
     *     start++;
     * }
     * </pre>
     * This ensures when we exclude an element, we skip all its duplicates at once.
     * <p>
     * Why this works: If we've decided NOT to include a duplicate element at this
     * decision point, including any subsequent duplicate would create a subset we've
     * already generated through a different path (the include branch).
     * <p>
     * Example with [1,2,2]:
     * <ul>
     *   <li>Include first 2: explores [2], [2,2], etc.</li>
     *   <li>Exclude first 2: skip second 2 entirely to avoid duplicating [] path</li>
     * </ul>
     *
     * @param nums sorted input array
     * @param start current index being processed
     * @param current current subset being built
     * @param result list storing all unique subsets
     */
    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Base case: reached end of array, add current subset
        if (start == nums.length) {
            result.add(new ArrayList<>(current));  // Create copy to preserve result
            return;
        }

        // Choice 1: Include current element
        current.add(nums[start]);
        backtrack(nums, start + 1, current, result);

        // Backtrack: Remove element for exclude branch
        current.remove(current.size() - 1);

        // Choice 2: Exclude current element AND all its consecutive duplicates
        // This is the key optimization that prevents duplicate subsets
        while (start < nums.length - 1 && nums[start] == nums[start + 1])
            start++;

        backtrack(nums, start + 1, current, result);
    }
}
