package a_5_FAQ_Medium;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        int[] arr = {2, -2, 0, 3, -3, 5};
        List<List<Integer>> result = obj.threeSum(arr);

        System.out.println("Solution (Optimized Two Pointers) = ");
        for (List<Integer> res : result) {
            System.out.println(res);
        }

        result = obj.threeSumBruteForce(arr);

        System.out.println("Solution (Brute Force) = ");
        for (List<Integer> res : result) {
            System.out.println(res);
        }
    }

    // Solution - 1 Brute Force : O(n^3)
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // sort triplet to avoid duplicates like [2,-2,0] and [-2,0,2]
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet);
                        set.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    // ✅ Optimized Solution : O(N^2)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];
            twoSum(nums, i + 1, n - 1, target, result);
        }
        return result;
    }

    private void twoSum(int[] nums, int start, int end, int target, List<List<Integer>> result) {
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum < target) {
                start++;
            } else if (sum > target) {
                end--;
            } else {
                result.add(Arrays.asList(-target, nums[start], nums[end]));

                // Skip duplicates safely
                while (start < end && nums[start] == nums[start + 1]) start++;
                while (start < end && nums[end] == nums[end - 1]) end--;

                start++;
                end--;
            }
        }
    }
}
