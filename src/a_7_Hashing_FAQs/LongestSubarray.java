package a_7_Hashing_FAQs;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarray {
    public static void main(String[] args) {
        LongestSubarray obj = new LongestSubarray();

        System.out.println(obj.longestSubarray(new int[]{10, 5, 2, 7, 1, 9}, 15));
        System.out.println(obj.longestSubarray(new int[]{-3, 2, 1}, 6));
        System.out.println(obj.longestSubarray(new int[]{-1, 1, 1}, 1));
    }

    public int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0, maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // Case 1: subarray starts from index 0
            if (prefixSum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // Case 2: subarray found with sum k
            if (map.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - map.get(prefixSum - k));
            }

            // Store prefixSum only if it’s not already present (earliest index)
            map.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }
}
