package a_7_Hashing_FAQs;
import java.util.*;

public class SubarraySum {
    public static void main(String[] args) {
        SubarraySum obj = new SubarraySum();

        System.out.println(obj.subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(obj.subarraySum(new int[]{1, 2, 3}, 3));
        System.out.println(obj.subarraySum(new int[]{3, 1, 2, 4}, 6));
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // important: one way to have sum == k from start

        int prefixSum = 0, count = 0;

        for (int num : nums) {
            prefixSum += num;

            // check if there is a subarray ending here with sum k
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            // update prefixSum frequency
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
