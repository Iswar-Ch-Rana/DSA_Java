package a_9_Recursion.FAQs;

import java.util.ArrayList;
import java.util.List;

/**
 * 🧩 Problem: Find all valid combinations of k numbers (1-9) that sum to n.
 * <p>
 * Given two integers k and n, return all possible combinations of k numbers
 * chosen from the range [1, 9] that sum to n. Each number can be used at most once.
 * <p>
 * ⏱️ Time: O(9^k) in worst case - at most k levels, 9 choices per level
 * <br>
 * Space: O(k) for recursion stack depth
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Generate all possible combinations of k numbers from 1-9 (C(9,k))</li>
 *   <li>Check each combination to see if it sums to n</li>
 *   <li>Time: O(C(9,k) × k) - need to sum each combination</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Backtracking with Running Sum & Pruning):
 * <ul>
 *   <li>Use backtracking with pick/not-pick pattern starting from 1 to 9</li>
 *   <li>Track running sum (currentSum) to avoid recalculating sum repeatedly</li>
 *   <li>Base case success: when combination size == k AND currentSum == target</li>
 *   <li>Early pruning: return if index > 9, currentSum > target, or size > k</li>
 *   <li>Include: Add number to temp, recurse with index+1 and updated sum</li>
 *   <li>Exclude: Remove number (backtrack), recurse with index+1 and same sum</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Backtracking, Running Sum Optimization, Early Pruning, Pick/Not-Pick Pattern
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain running sum optimization: avoids O(k) sum calculation at each leaf node</li>
 *   <li>Mention three pruning conditions: index out of range, sum exceeds target, size exceeds k</li>
 *   <li>Each number used at most once: move to index+1 in both include and exclude cases</li>
 *   <li>Follow-up: Can optimize further with for-loop approach instead of pick/not-pick</li>
 * </ul>
 */
public class CombinationSumIII {

    public static void main(String[] args) {
        CombinationSumIII obj = new CombinationSumIII();

        System.out.println(obj.combinationSum3(8, 9)); // [[1,2,4]]
        System.out.println(obj.combinationSum3(3, 9)); // [[1,2,6],[1,3,5],[2,3,4]]
        System.out.println(obj.combinationSum3(3, 8)); // [[1,2,5],[1,3,4]]
    }

    /**
     * Finds all valid combinations of k numbers from [1,9] that sum to n.
     * <p>
     * Uses backtracking with running sum optimization to efficiently
     * generate all valid combinations with early pruning.
     *
     * @param k number of elements required in each combination
     * @param n target sum to achieve
     * @return list of all valid k-number combinations that sum to n
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(1, k, n, 0, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * Recursive backtracking helper with running sum tracking and early pruning.
     * <p>
     * Decision Tree Approach:
     * <ul>
     *   <li>Base case (success): If combination has k elements AND currentSum equals target,
     *       add to result</li>
     *   <li>Base case (prune): If index > 9, sum exceeds target, or size exceeds k,
     *       return immediately</li>
     *   <li>Choice 1: Include current number - add to temp, recurse with updated sum</li>
     *   <li>Choice 2: Exclude current number - backtrack (remove), recurse with same sum</li>
     * </ul>
     * <p>
     * Key optimization: Track currentSum incrementally instead of recalculating sum
     * at each step, reducing overhead from O(k) to O(1) per operation.
     *
     * @param index current number being considered (1-9)
     * @param k target combination size
     * @param target target sum to achieve
     * @param currentSum running sum of elements in temp so far
     * @param temp current combination being built
     * @param ans result list storing all valid combinations
     */
    private void solve(int index, int k, int target, int currentSum, List<Integer> temp, List<List<Integer>> ans) {
        // ✅ Base case (success): valid combination found
        if (temp.size() == k && currentSum == target) {
            ans.add(new ArrayList<>(temp));  // Create copy to preserve result
            return;
        }

        // ❌ Base case (prune): invalid path - terminate early
        if (index > 9 || currentSum > target || temp.size() > k)
            return;

        // ✅ Choice 1: Include current number
        temp.add(index);
        solve(index + 1, k, target, currentSum + index, temp, ans);

        // ✅ Choice 2: Exclude current number (backtrack)
        temp.remove(temp.size() - 1);
        solve(index + 1, k, target, currentSum, temp, ans);
    }
}
