package a_9_Recursion.SubsequencePatternProblems;

import java.util.ArrayList;
import java.util.List;

/**
 * 🧩 Problem: Find all unique combinations of candidates where the sum equals target.
 * <p>
 * Given an array of distinct positive integers (candidates) and a target integer,
 * return all unique combinations where candidates sum to target. The same number
 * may be chosen from candidates an unlimited number of times.
 * <p>
 * ⏱️ Time: O(N^(T/M)) where N = candidates length, T = target, M = min candidate value
 * <br>
 * Space: O(T/M) for recursion stack depth
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Generate all possible combinations with repetition allowed</li>
 *   <li>Check each combination if sum equals target</li>
 *   <li>Highly inefficient with exponential combinations</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Backtracking with Pruning):
 * <ul>
 *   <li>For each candidate, make two choices: include (stay at same index) or exclude (move next)</li>
 *   <li>Include: Add to path and recurse with same index (allows reuse)</li>
 *   <li>Exclude: Remove from path (backtrack) and move to next candidate</li>
 *   <li>Prune branches early: stop if target becomes 0 (found solution) or negative (invalid path)</li>
 *   <li>Stay at same index when including allows unlimited repetition of same element</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Backtracking, Recursion, Decision Tree, Pruning, Unlimited Repetition
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain why we stay at same index when including: allows unlimited reuse of element</li>
 *   <li>Emphasize pruning: return early when target < 0 or target == 0</li>
 *   <li>Difference from Subsets: here we can reuse elements, so index doesn't increment on include</li>
 *   <li>Follow-up: Combination Sum II (each element used once), or with duplicate candidates</li>
 * </ul>
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();

        System.out.println(obj.combinationSum(new int[]{2, 3, 5, 4}, 7));
        System.out.println(obj.combinationSum(new int[]{2}, 1));
        System.out.println(obj.combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(obj.combinationSum(new int[]{2, 3, 5}, 8));
    }

    /**
     * Finds all unique combinations where candidates sum to target.
     * <p>
     * Each number in candidates may be used unlimited times.
     * Uses backtracking to explore all valid combinations.
     *
     * @param candidates array of distinct positive integers
     * @param target target sum to achieve
     * @return list of all unique combinations that sum to target
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * Recursive backtracking helper to explore all valid combinations.
     * <p>
     * Decision Tree Approach:
     * <ul>
     *   <li>Base case 1: If target == 0, valid combination found, add to result</li>
     *   <li>Base case 2: If target < 0 or index out of bounds, invalid path, return</li>
     *   <li>Choice 1: Include current element - add to path, recurse with SAME index (allows reuse)</li>
     *   <li>Choice 2: Exclude current element - backtrack (remove), recurse with NEXT index</li>
     * </ul>
     *
     * @param arr candidates array
     * @param index current position in array
     * @param target remaining target sum needed
     * @param path current combination being built
     * @param ans result list storing all valid combinations
     */
    private void backtrack(int[] arr, int index, int target, List<Integer> path, List<List<Integer>> ans) {
        // Base case: target reached - valid combination found
        if (target == 0) {
            ans.add(new ArrayList<>(path));  // Create copy to preserve result
            return;
        }

        // Base case: invalid path - out of bounds or target exceeded
        if (index == arr.length || target < 0)
            return;

        // Choice 1: Include current element (stay at same index for unlimited reuse)
        path.add(arr[index]);
        backtrack(arr, index, target - arr[index], path, ans);  // Same index allows repetition

        // Backtrack: Remove last element to explore other branches
        path.remove(path.size() - 1);

        // Choice 2: Exclude current element (move to next index)
        backtrack(arr, index + 1, target, path, ans);
    }
}
