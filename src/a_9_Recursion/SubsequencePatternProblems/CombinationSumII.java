package a_9_Recursion.SubsequencePatternProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🧩 Problem: Find all unique combinations where candidates sum to target.
 * <p>
 * Given a collection of candidate numbers (with possible duplicates) and a target,
 * find all unique combinations where candidates sum to target. Each number from
 * candidates may only be used once in each combination.
 * <p>
 * ⏱️ Time: O(2^N) in worst case, where N = array length
 * <br>
 * Space: O(N) for recursion stack
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Generate all combinations allowing each element once</li>
 *   <li>Use a Set to store combinations as tuples to eliminate duplicates</li>
 *   <li>Time: O(2^N) + overhead of set operations and sorting</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Backtracking with Smart Duplicate Skipping):
 * <ul>
 *   <li>Sort the array first to group duplicate elements together</li>
 *   <li>Use for-loop based backtracking starting from 'start' index</li>
 *   <li>Skip duplicates at same recursion level: if (i > start && arr[i] == arr[i-1]) continue</li>
 *   <li>Early termination: if current element > target, break (sorted array optimization)</li>
 *   <li>Move to i+1 (not same index) to ensure each element used at most once</li>
 *   <li>No need for Set - duplicates avoided during generation itself</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Backtracking, Duplicate Skipping, Sorting for Optimization, For-Loop Recursion
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain duplicate skip condition: i > start ensures we skip only at same level, not across levels</li>
 *   <li>Why sort? Groups duplicates together and enables early termination</li>
 *   <li>Key difference from Combination Sum I: here each element used once (i+1), there unlimited use (same i)</li>
 *   <li>Follow-up: Similar pattern works for Subsets II, Permutations II (duplicate handling)</li>
 * </ul>
 */
public class CombinationSumII {

    public static void main(String[] args) {
        CombinationSumII obj = new CombinationSumII();

        System.out.println(obj.combinationSum2(new int[]{2, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(obj.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
        System.out.println(obj.combinationSum2(new int[]{2, 1, 2}, 5));
    }

    /**
     * Finds all unique combinations where candidates sum to target.
     * <p>
     * Each number in candidates may be used only once per combination.
     * Handles duplicate elements in candidates array intelligently.
     *
     * @param candidates array of candidate numbers (may contain duplicates)
     * @param target target sum to achieve
     * @return list of all unique combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);  // Sort for duplicate handling and early termination
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    /**
     * Recursive backtracking helper with intelligent duplicate skipping.
     * <p>
     * Key Algorithm Details:
     * <ul>
     *   <li>Base case: If target == 0, valid combination found, add to result</li>
     *   <li>For-loop iteration: Try each candidate from 'start' index onwards</li>
     *   <li>Skip duplicates: if (i > start && arr[i] == arr[i-1]) continue
     *       - i > start ensures we only skip duplicates at SAME recursion level
     *       - First occurrence of duplicate is always processed</li>
     *   <li>Early termination: if arr[i] > target, break (sorted array benefit)</li>
     *   <li>Include candidate: Add to current, recurse with i+1 (not i), backtrack</li>
     * </ul>
     *
     * @param arr sorted candidates array
     * @param start starting index for current recursion level
     * @param target remaining target sum needed
     * @param current current combination being built
     * @param result list storing all valid combinations
     */
    private void backtrack(int[] arr, int start, int target, List<Integer> current, List<List<Integer>> result) {
        // Base case: target reached - valid combination found
        if (target == 0) {
            result.add(new ArrayList<>(current));  // Create copy to preserve result
            return;
        }

        // Try each candidate from start index onwards
        for (int i = start; i < arr.length; i++) {
            // Skip duplicates at the same recursion level
            // i > start ensures we only skip duplicates within this level
            if (i > start && arr[i] == arr[i - 1])
                continue;

            // Early termination: no point trying larger elements (array is sorted)
            if (arr[i] > target)
                break;

            // Include current candidate
            current.add(arr[i]);

            // Recurse with next index (i+1) - each element used at most once
            backtrack(arr, i + 1, target - arr[i], current, result);

            // Backtrack: remove last element to explore other branches
            current.remove(current.size() - 1);
        }
    }
}
